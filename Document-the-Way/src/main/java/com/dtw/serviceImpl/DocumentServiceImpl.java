package com.dtw.serviceImpl;

import com.dtw.dtos.requestDtos.DocumentRequestDto;
import com.dtw.dtos.responseDtos.DocumentResponseDto;
import com.dtw.dtos.responseDtos.MediaResponseDto;
import com.dtw.entity.Document;
import com.dtw.entity.Media;
import com.dtw.entity.User;
import com.dtw.exception.ResourceNotFoundException;
import com.dtw.mapper.DocumentMapper;
import com.dtw.mapper.MediaMapper;
import com.dtw.repo.DocumentRepo;
import com.dtw.repo.MediaRepo;
import com.dtw.repo.UserRepo;
import com.dtw.utils.AmazonS3Service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional // for transactions
public class DocumentServiceImpl {

    @Autowired
    private DocumentRepo documentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AmazonS3Service amazonS3Service;


    @Autowired
    private MediaRepo mediaRepo;

    public List<DocumentResponseDto> getAll() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return documentRepo.findByUser(currentUser)
                .map(DocumentMapper.MAPPER::mapToDocumentResponseDto)
                .collect(Collectors.toList());
    }

    public String uploadFile(MultipartFile fileToSave, Long documentId) {
        try {
            String originalFilename = fileToSave.getOriginalFilename();
            String fileExtension = originalFilename != null && originalFilename.contains(".")
                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
                    : "";
            String key = UUID.randomUUID().toString() + fileExtension;

            Document foundDocument = documentRepo.findById(documentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Document", "Document not found", documentId));

            String mediaUrl = amazonS3Service.uploadFile(key, fileToSave);

            String mediaType = fileToSave.getContentType() != null
                    ? fileToSave.getContentType().split("/")[0]
                    : "unknown";

            MediaResponseDto mediaResponseDto = new MediaResponseDto();
            mediaResponseDto.setMediaType(mediaType);
            mediaResponseDto.setMediaUrl(mediaUrl);
            mediaResponseDto.setDocument(foundDocument);

            Media newMedia = mediaRepo.save(MediaMapper.MAPPER.mapToMedia(mediaResponseDto));

            foundDocument.setIntroductionMedia(newMedia);
            documentRepo.save(foundDocument);

            return mediaUrl;

        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to AWS: message: " + e.getMessage() + " cause: " + e.getCause());
        }
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