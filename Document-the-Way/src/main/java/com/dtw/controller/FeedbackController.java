package com.dtw.controller;


import com.dtw.dtos.FeedbackDto;
import com.dtw.serviceImpl.FeedbackServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@Tag( name = "Feedback")
public class FeedbackController {


    @Autowired
    private FeedbackServiceImpl feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackDto>  giveFeedback(
            @RequestBody FeedbackDto feedbackDto
    ){
        FeedbackDto feedBack = feedbackService.create(feedbackDto);
        return new ResponseEntity<>(feedBack , HttpStatus.CREATED);
    }

    @GetMapping("/not-null")
    public ResponseEntity<List<FeedbackDto>> getFeedbackWithSolutions(){
        return new ResponseEntity<>(feedbackService.findNotNull() , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackDto>> getAll(){
        return new ResponseEntity<>(feedbackService.getAll() , HttpStatus.OK);
    }

}
