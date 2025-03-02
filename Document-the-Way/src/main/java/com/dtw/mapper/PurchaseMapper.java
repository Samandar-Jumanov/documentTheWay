package com.dtw.mapper;

import com.dtw.dtos.requestDtos.PurchaseRequestDto;
import com.dtw.dtos.responseDtos.DocumentResponseDto;
import com.dtw.dtos.responseDtos.PurchaseResponseDto;
import com.dtw.dtos.responseDtos.UserResponseDto;
import com.dtw.entity.Document;
import com.dtw.entity.Purchase;
import com.dtw.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    PurchaseMapper MAPPER = Mappers.getMapper(PurchaseMapper.class);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "document", ignore = true)
    Purchase mapToPurchase(PurchaseRequestDto dto);

    @Mapping(target = "user", source = "user", qualifiedByName = "mapUserWithoutCollections")
    @Mapping(target = "document", source = "document", qualifiedByName = "mapDocumentWithoutCollections")
    PurchaseResponseDto mapToPurchaseDto(Purchase purchase);

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
}