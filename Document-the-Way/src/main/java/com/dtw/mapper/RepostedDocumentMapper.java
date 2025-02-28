package com.dtw.mapper;

import com.dtw.dtos.requestDtos.RepostedDocumentRequestDto;
import com.dtw.dtos.responseDtos.RepostedDocumentResponseDto;
import com.dtw.entity.RepostedDocument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface RepostedDocumentMapper {

    RepostedDocumentMapper MAPPER = Mappers.getMapper(RepostedDocumentMapper.class);

//    @Mapping(source = "document", target = "document")
    RepostedDocument mapToRepost(RepostedDocumentRequestDto repostDto);
    RepostedDocumentResponseDto mapToRepostDto (RepostedDocument repost);
}
