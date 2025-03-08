package com.dtw.controller;


import com.dtw.dtos.requestDtos.DocumentRequestDto;
import com.dtw.dtos.responseDtos.DocumentResponseDto;
import com.dtw.serviceImpl.DocumentServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@RestController
@RequestMapping("/api/user/documents")
@AllArgsConstructor
@Tag( name = "Document")

public class DocumentController {

    @Autowired
    private DocumentServiceImpl documentService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/all")
    public ResponseEntity<List<DocumentResponseDto>> getUserAllDocuments(){

          List<DocumentResponseDto> documents = documentService.getAll();
          return new ResponseEntity<>(documents , HttpStatus.OK);
    };


    @PostMapping("/introduction-media/{documentId}")
    public ResponseEntity<String> uploadIntroductionMedia(
            @RequestParam("file")MultipartFile file  , RedirectAttributes redirectAttributes, @PathVariable Long documentId
    ){
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
            return new ResponseEntity<>("redirect:/" , HttpStatus.BAD_REQUEST);
        }

        String mediaurl = documentService.uploadFile(file , documentId);
        return new ResponseEntity<>(mediaurl , HttpStatus.CREATED);

    }


    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponseDto> getDocument(
            @PathVariable Long id
    ){
        DocumentResponseDto document = documentService.getSingleDocument(id);
        return new ResponseEntity<>(document , HttpStatus.OK);
    };

    @PostMapping
    public ResponseEntity<DocumentResponseDto> createDocument(
            @Valid  @RequestBody DocumentRequestDto docDto
    ){
        DocumentResponseDto createdDocument = documentService.createDocument(docDto);
        return  new ResponseEntity<>(createdDocument , HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<DocumentResponseDto> deleteDocument(
            @PathVariable Long id
    ){

        DocumentResponseDto deletedDocument = documentService.delete(id);
        return new ResponseEntity<>(deletedDocument , HttpStatus.OK);

    }


    @PutMapping("/{id}")
    public ResponseEntity<DocumentResponseDto> updateDocument(
            @Valid @RequestBody DocumentRequestDto documentDto,
            @PathVariable Long id
    ){
        DocumentResponseDto updatedDocument = documentService.updateDocument(documentDto , id);
        return new ResponseEntity<>(updatedDocument , HttpStatus.OK);
    }


}
