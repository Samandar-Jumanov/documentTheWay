package com.dtw.mapper;


import com.dtw.dtos.requestDtos.PurchaseRequestDto;
import com.dtw.dtos.responseDtos.PurchaseResponseDto;
import com.dtw.entity.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface  PurchaseMapper {

    PurchaseMapper MAPPER = Mappers.getMapper(PurchaseMapper.class);

    Purchase mapToPurchase(PurchaseRequestDto dto);
    PurchaseResponseDto mapToPurchaseDto(Purchase purchase);
    

}
