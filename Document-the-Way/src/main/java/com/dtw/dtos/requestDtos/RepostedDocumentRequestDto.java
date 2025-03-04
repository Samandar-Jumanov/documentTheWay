package com.dtw.dtos.requestDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepostedDocumentRequestDto {
    private Long userId;
    private Long documentId;
}