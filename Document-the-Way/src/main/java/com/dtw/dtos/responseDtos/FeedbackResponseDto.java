package com.dtw.dtos.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponseDto {

    private Long id;
    private String feedbackValue;
    private String solution;

    private Long userId; // Optional if you want to show the user's ID in response
}
