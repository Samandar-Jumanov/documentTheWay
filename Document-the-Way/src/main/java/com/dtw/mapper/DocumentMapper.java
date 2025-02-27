package com.dtw.mapper;

import com.dtw.dtos.DocumentDto;
import com.dtw.dtos.PartDto;
import com.dtw.dtos.RepostedDocumentDto;
import com.dtw.entity.Document;
import com.dtw.entity.Part;
import com.dtw.entity.RepostedDocument;
import com.dtw.entity.User;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DocumentMapper {

    DocumentMapper MAPPER = Mappers.getMapper(DocumentMapper.class);

//    @Mapping(source = "user", target = "user")
//    @Mapping(source = "parts", target = "parts")
//    @Mapping(source = "reposts", target = "reposts")
//
//    // Map related entities to their DTOs
//    UserInfo mapToUserInfoDto(User user);
//    List<PartDto> mapToPartDtoList(List<Part> parts);
//    PartDto mapToPartDto(Part part);
//    List<RepostedDocumentDto> mapToRepostDtoList(List<RepostedDocument> reposts);
//    RepostedDocumentDto mapToRepostDto(RepostedDocument repost);

    Document mapToDocument ( DocumentDto documentDto);
    DocumentDto mapToDocumentDto ( Document document);




}
