package com.dtw.dtos.responseDtos;

import com.dtw.entity.Document;
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

    private UserResponseDto user;
    private DocumentResponseDto document;
    private List<PartResponseDto> repostParts;

}
