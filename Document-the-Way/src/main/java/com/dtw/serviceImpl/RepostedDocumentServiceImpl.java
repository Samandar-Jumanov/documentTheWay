package com.dtw.serviceImpl;


import com.dtw.dtos.RepostedDocumentDto;
import com.dtw.entity.Document;
import com.dtw.entity.RepostedDocument;
import com.dtw.entity.User;
import com.dtw.exception.ResourceNotFoundException;
import com.dtw.mapper.RepostedDocumentMapper;
import com.dtw.repo.DocumentRepo;
import com.dtw.repo.RepostedDocumentRepo;
import com.dtw.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RepostedDocumentServiceImpl {

    @Autowired
    private UserRepo  userRepo;

    @Autowired
    private DocumentRepo documentRepo;

    @Autowired
    private RepostedDocumentRepo repostedDocumentRepo;


    public RepostedDocumentDto createRepost (
           Long documentId
    ){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Document foundDocument = documentRepo.findById(documentId)
                .orElseThrow(( ) -> new ResourceNotFoundException("Document" , "Document not found" , documentId));


       RepostedDocumentDto repostDto = new RepostedDocumentDto(
               currentUser , foundDocument
       );



       RepostedDocument repostEntity = RepostedDocumentMapper.MAPPER.mapToRepost(repostDto);
       RepostedDocument repost = repostedDocumentRepo.save(repostEntity);

       return RepostedDocumentMapper.MAPPER.mapToRepostDto(repost);

    }


    public RepostedDocumentDto delete(Long id ) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        RepostedDocument foundRepost = repostedDocumentRepo.findById(id)
                .orElseThrow();

        repostedDocumentRepo.delete(foundRepost);
        return  RepostedDocumentMapper.MAPPER.mapToRepostDto(foundRepost);

    }

    public List<RepostedDocumentDto> getUsersReposts() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return repostedDocumentRepo.findByUser(currentUser)
                .map(RepostedDocumentMapper.MAPPER::mapToRepostDto)
                .collect(Collectors.toList());
    }

    public RepostedDocumentDto getSingle(Long id ){

        return  RepostedDocumentMapper.MAPPER.mapToRepostDto(repostedDocumentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repost" , "Repost not found " , id))
        );

    }
}
