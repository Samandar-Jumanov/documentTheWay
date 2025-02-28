package com.dtw.serviceImpl;

import com.dtw.dtos.requestDtos.DocumentRequestDto;
import com.dtw.dtos.responseDtos.DocumentResponseDto;
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

    public List<DocumentResponseDto> getAll() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return documentRepo.findByUser(currentUser)
                .map(DocumentMapper.MAPPER::mapToDocumentResponseDto)
                .collect(Collectors.toList());
    }

    public DocumentResponseDto getSingleDocument(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Document document = documentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document Not found", "Document" , id));

        // Verify document belongs to current user
        if (!document.getUser().getUsername().equals(username)) {
            throw new SecurityException("Access denied to document: " + id);
        }
        return DocumentMapper.MAPPER.mapToDocumentResponseDto(document);
    }


    public DocumentResponseDto createDocument(@Valid DocumentRequestDto docDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));



        Document document = DocumentMapper.MAPPER.mapToDocument(docDto);
        document.setUser(currentUser);


        Document savedDocument = documentRepo.save(document);
        return DocumentMapper.MAPPER.mapToDocumentResponseDto(savedDocument);

    }

    public DocumentResponseDto delete(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Document document = documentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Document not found with id: " + id));

        // Verify document belongs to current user
        if (!document.getUser().getUsername().equals(username)) {
            throw new SecurityException("Access denied to document: " + id);
        }

        DocumentResponseDto deletedDoc = DocumentMapper.MAPPER.mapToDocumentResponseDto(document);
        documentRepo.delete(document);
        return deletedDoc;
    }

    public DocumentResponseDto updateDocument(@Valid DocumentRequestDto documentDto, Long id) {
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
        return DocumentMapper.MAPPER.mapToDocumentResponseDto(updatedDocument);
    }


    private void updateDocumentProperties(Document existingDocument, DocumentRequestDto dto) {
        existingDocument.setTitle(dto.getTitle());
        existingDocument.setDescription(dto.getDescription());
    }


}