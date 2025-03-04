package com.dtw.serviceImpl;

import com.dtw.dtos.responseDtos.RepostedDocumentResponseDto;
import com.dtw.entity.Document;
import com.dtw.entity.RepostedDocument;
import com.dtw.entity.User;
import com.dtw.exception.ResourceNotFoundException;
import com.dtw.mapper.CycleAvoidingMappingContext;
import com.dtw.mapper.RepostedDocumentMapper;
import com.dtw.repo.DocumentRepo;
import com.dtw.repo.RepostedDocumentRepo;
import com.dtw.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RepostedDocumentServiceImpl {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DocumentRepo documentRepo;

    @Autowired
    private RepostedDocumentRepo repostedDocumentRepo;

    @Autowired
    private RepostedDocumentMapper repostedDocumentMapper;

    @Autowired
    private CycleAvoidingMappingContext context;

    public RepostedDocumentResponseDto createRepost(Long documentId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Document foundDocument = documentRepo.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Document", "Document not found", documentId));

        // Create a new repost entity directly
        RepostedDocument repostEntity = new RepostedDocument();
        repostEntity.setUser(currentUser);
        repostEntity.setDocument(foundDocument);

        RepostedDocument savedRepost = repostedDocumentRepo.save(repostEntity);

        // Clear the context before mapping to ensure no previous mapping state affects this operation
        context.clear();
        return repostedDocumentMapper.mapToRepostDto(savedRepost);
    }

    public RepostedDocumentResponseDto delete(Long id) {
        RepostedDocument foundRepost = repostedDocumentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repost", "Repost not found", id));

        repostedDocumentRepo.delete(foundRepost);

        // Clear the context before mapping
        context.clear();
        return repostedDocumentMapper.mapToRepostDto(foundRepost);
    }

    public List<RepostedDocumentResponseDto> getUsersReposts() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<RepostedDocument> reposts = repostedDocumentRepo.findByUser(currentUser);

        return reposts.stream()
                .map(rD -> {
                    // Create a fresh context for each mapping operation
                    context.clear();
                    // Use the injected mapper instead of the static MAPPER instance
                    return repostedDocumentMapper.mapToRepostDto(rD);
                })
                .toList();
    }

    public RepostedDocumentResponseDto getSingle(Long id) {
        RepostedDocument repost = repostedDocumentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repost", "Repost not found ", id));

        // Clear the context before mapping
        context.clear();
        return repostedDocumentMapper.mapToRepostDto(repost);
    }
}