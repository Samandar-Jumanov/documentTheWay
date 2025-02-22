package com.dtw.mapper;


import com.dtw.dtos.PartDto;
import com.dtw.entity.Part;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PartMapper {

    PartMapper MAPPER = Mappers.getMapper(PartMapper.class);



    PartDto mapPartToPartDto (Part part);
    Part mapPartDtoToPart ( PartDto partDto);
}
