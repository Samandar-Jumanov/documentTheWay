package com.dtw.dtos;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FeedbackDto {

    private Long id;

    @NotBlank
    @NotNull
    private String feedbackValue;

    @Nullable
    private String solution;

}
