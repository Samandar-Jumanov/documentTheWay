package com.dtw.dtos.requestDtos;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FeedbackRequestDto {

    private Long id;

    @NotBlank
    @NotNull
    private String feedbackValue;

    @Nullable
    private String solution;

}
