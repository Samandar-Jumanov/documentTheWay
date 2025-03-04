package com.dtw.mapper;

import com.dtw.dtos.requestDtos.RepostedDocumentRequestDto;
import com.dtw.dtos.responseDtos.*;
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

    @Mapping(target = "user", qualifiedByName = "userWithoutDocuments")
    @Mapping(target = "document", qualifiedByName = "documentWithoutUser")
    RepostedDocumentResponseDto mapToRepostDto(RepostedDocument repost);

    @Named("userWithoutDocuments")
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "feedbacks", ignore = true)
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    @Mapping(target = "reposts", ignore = true)
    UserResponseDto mapUserWithoutDocuments(User user);

    // Document without user to break the cycle
    @Named("documentWithoutUser")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "parts", ignore = true)
    @Mapping(target = "reposts", ignore = true)
    DocumentResponseDto mapDocumentWithoutUser(Document document);

    // Original methods for other uses
    RepostedDocument mapToRepost(RepostedDocumentRequestDto repostDto);
    DocumentResponseDto mapToDocumentResponseDto(Document document);
    UserResponseDto mapToUserResponseDto(User user);
    PartResponseDto mapToPartResponseDto(Part repostPart);
    List<PartResponseDto> mapToPartResponseDtoList(List<Part> repostParts);
}