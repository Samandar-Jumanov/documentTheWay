package com.dtw.dtos.responseDtos;

import com.dtw.entity.User;
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
    private PurchaseResponseDto purchase;  // Assuming you want to expose purchase details
    private List<PartResponseDto> parts;   // Parts are nested inside document
    private List<RepostedDocumentResponseDto> reposts;  // Nested reposts
}
