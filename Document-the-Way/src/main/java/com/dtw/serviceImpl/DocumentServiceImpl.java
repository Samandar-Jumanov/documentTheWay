package com.dtw.serviceImpl;

import com.dtw.dtos.DocumentDto;
import com.dtw.entity.Document;
import com.dtw.entity.User;
import com.dtw.exception.ResourceNotFoundException;
import com.dtw.mapper.DocumentMapper;
import com.dtw.repo.DocumentRepo;
import com.dtw.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // for transactions
public class DocumentServiceImpl {

    @Autowired
    private DocumentRepo documentRepo;

    @Autowired
    private UserRepo userRepo;

    public List<DocumentDto> getAll() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return documentRepo.findByUser(currentUser)
                .stream()
                .map(DocumentMapper.MAPPER::mapToDocumentDto)
                .collect(Collectors.toList());
    }

    public DocumentDto getSingleDocumentOfUser(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Document document = documentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document Not found", "Document" , id));

        // Verify document belongs to current user
        if (!document.getUser().getUsername().equals(username)) {
            throw new SecurityException("Access denied to document: " + id);
        }

        return DocumentMapper.MAPPER.mapToDocumentDto(document);
    }




    // To Do
    // find document in real time should be available here



    public DocumentDto createDocument(@Valid DocumentDto docDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));



        Document document = DocumentMapper.MAPPER.mapToDocument(docDto);
        document.setUser(currentUser);


        Document savedDocument = documentRepo.save(document);
        return DocumentMapper.MAPPER.mapToDocumentDto(savedDocument);

    }

    public DocumentDto delete(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Document document = documentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Document not found with id: " + id));

        // Verify document belongs to current user
        if (!document.getUser().getUsername().equals(username)) {
            throw new SecurityException("Access denied to document: " + id);
        }

        DocumentDto deletedDoc = DocumentMapper.MAPPER.mapToDocumentDto(document);
        documentRepo.delete(document);
        return deletedDoc;
    }

    public DocumentDto updateDocument(@Valid DocumentDto documentDto, Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Document existingDocument = documentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Document not found with id: " + id));

        // Verify document belongs to current user
        if (!existingDocument.getUser().getUsername().equals(username)) {
            throw new SecurityException("Access denied to document: " + id);
        }

        // Update existing document properties
        updateDocumentProperties(existingDocument, documentDto);

        Document updatedDocument = documentRepo.save(existingDocument);
        return DocumentMapper.MAPPER.mapToDocumentDto(updatedDocument);
    }


    private void updateDocumentProperties(Document existingDocument, DocumentDto dto) {
        existingDocument.setTitle(dto.getTitle());
        existingDocument.setDescription(dto.getDescription());
    }


    public void sendNotification(Long documentId , Long ownerId ){

//        Notification notification = Notification.builder()
//                .r(event.getOwnerId())
//                .type("DOCUMENT_VIEW")
//                .content(event.getViewerUsername() + " viewed your course \"" + event.getDocumentTitle() + "\"")
//                .documentId(event.getDocumentId())
//                .createdAt(event.getTimestamp())
//                .read(false)
//                .build();


        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Document foundDocument = documentRepo.findById(documentId)
                .orElseThrow(( ) ->  new ResourceNotFoundException("Document" , "Not found" , documentId));










    }
}