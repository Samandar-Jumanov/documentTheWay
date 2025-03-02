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
public class PartResponseDto {

    private Long id;

    private String partTitle;
    private String resource;


    private DocumentResponseDto document;
    private RepostedDocumentResponseDto repost;


    private LocalTime createdAt;
    private LocalTime updatedAt;

}
