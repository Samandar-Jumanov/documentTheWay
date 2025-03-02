package com.dtw.mapper;

import com.dtw.dtos.requestDtos.PartRequestDto;
import com.dtw.dtos.responseDtos.DocumentResponseDto;
import com.dtw.dtos.responseDtos.PartResponseDto;
import com.dtw.dtos.responseDtos.RepostedDocumentResponseDto;
import com.dtw.entity.Document;
import com.dtw.entity.Part;
import com.dtw.entity.RepostedDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PartMapper {

    PartMapper MAPPER = Mappers.getMapper(PartMapper.class);

    // Use named mappers to prevent circular references
    @Mapping(target = "document", source = "document", qualifiedByName = "toDocumentWithoutParts")
    @Mapping(target = "repost", source = "repost", qualifiedByName = "toRepostedDocumentWithoutParts")
    PartResponseDto mapToPartResponseDto(Part part);

    Part mapToPart(PartRequestDto partRequestDto);

    // Document mapper that doesn't include parts
    @Named("toDocumentWithoutParts")
    @Mapping(target = "parts", ignore = true)
    @Mapping(target = "user", ignore = true)
    DocumentResponseDto mapToDocumentWithoutParts(Document document);

    @Named("toRepostedDocumentWithoutParts")
    @Mapping(target = "parts", ignore = true)
    RepostedDocumentResponseDto mapToRepostedDocumentWithoutParts(RepostedDocument repostedDocument);
}