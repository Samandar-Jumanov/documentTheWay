package com.dtw.mapper;

import com.dtw.dtos.DocumentDto;
import com.dtw.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocumentMapper {

    DocumentMapper MAPPER = Mappers.getMapper(DocumentMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)

    Document mapToDocument ( DocumentDto documentDto);
    DocumentDto mapToDocumentDto ( Document document);




}
