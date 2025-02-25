package com.dtw.controller;


import com.dtw.dtos.DocumentDto;
import com.dtw.entity.Document;
import com.dtw.serviceImpl.DocumentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/documents")
@AllArgsConstructor
@Tag( name = "Document")

public class DocumentController {


    @Autowired
    private DocumentServiceImpl documentService;

    @GetMapping("/all")
    public ResponseEntity<List<DocumentDto>> getUserAllDocuments(){

          List<DocumentDto> documents = documentService.getAll();
          return new ResponseEntity<>(documents , HttpStatus.OK);
    };


    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocument(
            @PathVariable Long id
    ){
        DocumentDto document = documentService.getSingleDocumentOfUser(id);
        return new ResponseEntity<>(document , HttpStatus.OK);
    };

    @PostMapping
    public ResponseEntity<DocumentDto> createDocument(
            @Valid  @RequestBody DocumentDto docDto
    ){
        DocumentDto createdDocument = documentService.createDocument(docDto);
        return  new ResponseEntity<>(createdDocument , HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<DocumentDto> deleteDocument(
            @PathVariable Long id
    ){

        DocumentDto deletedDocument = documentService.delete(id);
        return new ResponseEntity<>(deletedDocument , HttpStatus.OK);

    }


    @PutMapping("/{id}")
    public ResponseEntity<DocumentDto> updateDocument(
            @Valid @RequestBody DocumentDto documentDto,
            @PathVariable Long id
    ){
        DocumentDto updatedDocument = documentService.updateDocument(documentDto , id);
        return new ResponseEntity<>(updatedDocument , HttpStatus.OK);
    }


}
