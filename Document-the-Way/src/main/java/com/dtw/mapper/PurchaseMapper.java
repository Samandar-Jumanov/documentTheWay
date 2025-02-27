package com.dtw.mapper;


import com.dtw.dtos.PurchaseDto;
import com.dtw.entity.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface  PurchaseMapper {

    PurchaseMapper MAPPER = Mappers.getMapper(PurchaseMapper.class);

    Purchase mapToPurchase(PurchaseDto dto);
    PurchaseDto mapToPurchaseDto(Purchase purchase);
    

}
