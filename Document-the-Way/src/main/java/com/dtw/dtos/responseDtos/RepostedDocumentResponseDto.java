package com.dtw.dtos.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepostedDocumentResponseDto {

    private Long id;

    private Long userId;
    private Long documentId;

    private List<PartResponseDto> parts;

}
