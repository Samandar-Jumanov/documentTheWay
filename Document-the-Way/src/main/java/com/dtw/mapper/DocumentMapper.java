package com.dtw.mapper;

import com.dtw.dtos.DocumentDto;
import com.dtw.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocumentMapper {

    DocumentMapper MAPPER = Mappers.getMapper(DocumentMapper.class);

    Document mapToDocument ( DocumentDto documentDto);
    DocumentDto mapToDocumentDto ( Document document);




}
