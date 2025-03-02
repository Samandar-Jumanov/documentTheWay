package com.dtw.dtos.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponseDto {

    private Long id;
    private String feedbackValue;
    private String solution;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UserResponseDto user;


}
