package com.dtw.dtos.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private UserResponseDto user;
    private PurchaseResponseDto purchase;
    private List<PartResponseDto> parts;
    private List<RepostedDocumentResponseDto> reposts;
    private MediaResponseDto introductionMedia;

}
