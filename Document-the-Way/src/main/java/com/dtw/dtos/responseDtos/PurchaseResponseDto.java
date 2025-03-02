package com.dtw.dtos.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PurchaseResponseDto {
    private long id;
    private UserResponseDto user;
    private DocumentResponseDto document;
    private LocalTime createdAt;
    private LocalTime updatedAt;
}
