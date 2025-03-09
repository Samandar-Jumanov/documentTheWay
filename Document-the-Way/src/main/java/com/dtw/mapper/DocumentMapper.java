package com.dtw.mapper;

import com.dtw.dtos.requestDtos.DocumentRequestDto;
import com.dtw.dtos.responseDtos.*;
import com.dtw.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentMapper MAPPER = Mappers.getMapper(DocumentMapper.class);

    @Mapping(target = "user", source = "user.id")
    @Mapping(target = "purchase", source = "purchase.id")
    @Mapping(target = "introductionMedia", source = "introductionMedia")
    DocumentResponseDto mapToDocumentResponseDto(Document document);
    Document mapToDocument(DocumentRequestDto dto);

    PartResponseDto mapToPartResponseDto(Part part);
    PurchaseResponseDto mapToPurchaseResponseDto(Long  id);
    RepostedDocumentResponseDto mapToRepostedDocumentResponseDto(RepostedDocument repostedDocument);
    UserResponseDto mapToUserResponseDto(Long  id);
    MediaResponseDto mapToMediaResponse(Long id );



}
