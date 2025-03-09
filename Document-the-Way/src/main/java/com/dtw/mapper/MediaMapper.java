package com.dtw.mapper;


import com.dtw.dtos.responseDtos.MediaDto;
import com.dtw.dtos.responseDtos.MediaResponseDto;
import com.dtw.entity.Media;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MediaMapper {
    MediaMapper MAPPER = Mappers.getMapper(MediaMapper.class);

    Media mapToMedia (MediaDto dto );
    MediaResponseDto mapToMediaResponse( Media media );


}
