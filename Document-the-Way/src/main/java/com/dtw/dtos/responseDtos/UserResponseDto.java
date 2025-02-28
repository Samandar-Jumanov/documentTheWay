package com.dtw.dtos.responseDtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String username;
    private String fullName;

    private List<DocumentResponseDto> documents;
    private List<FeedbackResponseDto> feedbacks;
    private List<RepostedDocumentResponseDto> reposts;
    private List<NotificationResponseDto> notifications;
    private List<PurchaseResponseDto> purchases;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
