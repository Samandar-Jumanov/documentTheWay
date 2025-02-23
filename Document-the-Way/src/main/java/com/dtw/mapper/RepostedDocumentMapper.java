package com.dtw.mapper;

import com.dtw.dtos.RepostedDocumentDto;
import com.dtw.entity.RepostedDocument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface RepostedDocumentMapper {

    RepostedDocumentMapper MAPPER = Mappers.getMapper(RepostedDocumentMapper.class);

    RepostedDocument mapToRepost(RepostedDocumentDto repostDto);
    RepostedDocumentDto  mapToRepostDto ( RepostedDocument repost);
}
