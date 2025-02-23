package com.dtw.controller;


import com.dtw.dtos.RepostedDocumentDto;
import com.dtw.serviceImpl.RepostedDocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/repost")
public class RepostedDocumentController {

    @Autowired
    private RepostedDocumentServiceImpl repostedDocumentService;


    @PostMapping("/{documentId}")
    public ResponseEntity<RepostedDocumentDto> repost(
            @PathVariable Long documentId
    ){
        RepostedDocumentDto repost = repostedDocumentService.createRepost(documentId);
        return  new ResponseEntity<>(repost, HttpStatus.CREATED);
    } // repost

    @GetMapping
    public ResponseEntity<List<RepostedDocumentDto>> getUserReposts(){
        List<RepostedDocumentDto> reposts = repostedDocumentService.getUsersReposts();
        return new ResponseEntity<>(reposts , HttpStatus.OK);
    } // get user reposts

    @DeleteMapping("/{repostId}")
    public ResponseEntity<RepostedDocumentDto> deleteRepost(
            @PathVariable Long repostId
    ){
        return new ResponseEntity<RepostedDocumentDto>(repostedDocumentService.delete(repostId) , HttpStatus.OK);
    } // delete repost


    @GetMapping("/{id}")
    public ResponseEntity<RepostedDocumentDto> getSingleRepost(
            @PathVariable Long id
    ){
         return  new ResponseEntity<RepostedDocumentDto>(repostedDocumentService.getSingle(id) , HttpStatus.OK);
    }








}
