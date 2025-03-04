package com.dtw.mapper;

import com.dtw.dtos.requestDtos.PurchaseRequestDto;
import com.dtw.dtos.responseDtos.DocumentResponseDto;
import com.dtw.dtos.responseDtos.PurchaseResponseDto;
import com.dtw.dtos.responseDtos.UserResponseDto;
import com.dtw.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    PurchaseMapper MAPPER = Mappers.getMapper(PurchaseMapper.class);

    @Mapping(target = "user", source = "user", qualifiedByName = "mapUserMinimal")
    @Mapping(target = "document", source = "document", qualifiedByName = "mapDocumentMinimal")
    PurchaseResponseDto mapToPurchaseDto(Purchase purchase);

    Purchase mapToPurchase(PurchaseRequestDto purchaseRequestDto);

    @Named("mapUserMinimal")
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "reposts", ignore = true)
    @Mapping(target = "feedbacks", ignore = true)
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    UserResponseDto mapUserMinimal(User user);

    @Named("mapDocumentMinimal")
    @Mapping(target = "parts", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "reposts", ignore = true)
    DocumentResponseDto mapDocumentMinimal(Document document);
}