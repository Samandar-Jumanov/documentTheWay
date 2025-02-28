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
public class NotificationResponseDto {

    private Long id;

    private String type;
    private String message;

    private LocalTime createdAt;

    private Long recipientId;
}
