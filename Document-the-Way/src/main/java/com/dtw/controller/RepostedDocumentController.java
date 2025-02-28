package com.dtw.controller;


import com.dtw.dtos.responseDtos.RepostedDocumentResponseDto;
import com.dtw.serviceImpl.RepostedDocumentServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/repost")
@Tag( name = "Repost ")
public class RepostedDocumentController {

    @Autowired
    private RepostedDocumentServiceImpl repostedDocumentService;


    @PostMapping("/{documentId}")
    public ResponseEntity<RepostedDocumentResponseDto> repost(
            @PathVariable Long documentId
    ){
        RepostedDocumentResponseDto repost = repostedDocumentService.createRepost(documentId);
        return  new ResponseEntity<>(repost, HttpStatus.CREATED);
    } // repost

    @GetMapping
    public ResponseEntity<List<RepostedDocumentResponseDto>> getUserReposts(){
        List<RepostedDocumentResponseDto> reposts = repostedDocumentService.getUsersReposts();
        return new ResponseEntity<>(reposts , HttpStatus.OK);
    } // get user reposts

    @DeleteMapping("/{repostId}")
    public ResponseEntity<RepostedDocumentResponseDto> deleteRepost(
            @PathVariable Long repostId
    ){
        return new ResponseEntity<RepostedDocumentResponseDto>(repostedDocumentService.delete(repostId) , HttpStatus.OK);
    } // delete repost


    @GetMapping("/{id}")
    public ResponseEntity<RepostedDocumentResponseDto> getSingleRepost(
            @PathVariable Long id
    ){
         return  new ResponseEntity<RepostedDocumentResponseDto>(repostedDocumentService.getSingle(id) , HttpStatus.OK);
    }








}
