package com.dtw.controller;


import com.dtw.dtos.requestDtos.FeedbackRequestDto;
import com.dtw.dtos.responseDtos.FeedbackResponseDto;
import com.dtw.serviceImpl.FeedbackServiceImpl;
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
    public ResponseEntity<FeedbackResponseDto>  giveFeedback(
            @RequestBody FeedbackRequestDto feedbackDto
    ){
        FeedbackResponseDto feedBack = feedbackService.create(feedbackDto);
        return new ResponseEntity<>(feedBack , HttpStatus.CREATED);
    }

    @GetMapping("/not-null")
    public ResponseEntity<List<FeedbackResponseDto>> getFeedbackWithSolutions(){
        return new ResponseEntity<>(feedbackService.findNotNull() , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponseDto>> getAll(){
        return new ResponseEntity<>(feedbackService.getAll() , HttpStatus.OK);
    }

}
