package com.dtw.mapper;

import com.dtw.dtos.requestDtos.RepostedDocumentRequestDto;
import com.dtw.dtos.responseDtos.DocumentResponseDto;
import com.dtw.dtos.responseDtos.PartResponseDto;
import com.dtw.dtos.responseDtos.RepostedDocumentResponseDto;
import com.dtw.dtos.responseDtos.UserResponseDto;
import com.dtw.entity.Document;
import com.dtw.entity.Part;
import com.dtw.entity.RepostedDocument;
import com.dtw.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RepostedDocumentMapper {

    RepostedDocumentMapper MAPPER = Mappers.getMapper(RepostedDocumentMapper.class);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "document", ignore = true)
    @Mapping(target = "parts", ignore = true)
    RepostedDocument mapToRepost(RepostedDocumentRequestDto repostDto);

    @Mapping(target = "user", source = "user", qualifiedByName = "mapUserWithoutCollections")
    @Mapping(target = "document", source = "document", qualifiedByName = "mapDocumentWithoutCollections")
    @Mapping(target = "parts", source = "parts", qualifiedByName = "mapPartsWithoutRepost")
    RepostedDocumentResponseDto mapToRepostDto(RepostedDocument repost);

    @Named("mapUserWithoutCollections")
    @Mapping(target = "feedbacks", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "reposts", ignore = true)
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    UserResponseDto mapUserWithoutCollections(User user);

    @Named("mapDocumentWithoutCollections")
    @Mapping(target = "parts", ignore = true)
    @Mapping(target = "user", ignore = true)
    DocumentResponseDto mapDocumentWithoutCollections(Document document);

    @Named("mapPartsWithoutRepost")
    List<PartResponseDto> mapPartsWithoutRepost(List<Part> parts);

    @Named("mapPartWithoutRepost")
    @Mapping(target = "document", ignore = true)
    @Mapping(target = "repost", ignore = true)
    PartResponseDto mapPartWithoutRepost(Part part);
}