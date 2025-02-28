package com.dtw.mapper;


import com.dtw.dtos.requestDtos.PartRequestDto;
import com.dtw.dtos.responseDtos.PartResponseDto;
import com.dtw.entity.Part;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PartMapper {

    PartMapper MAPPER = Mappers.getMapper(PartMapper.class);


    @Mapping(target = "documentId", source = "document.id")
    @Mapping(target = "repostId", source = "repost.id")
    PartResponseDto mapToPartResponseDto(Part part);
    Part mapToPart(PartRequestDto partRequestDto);

}
