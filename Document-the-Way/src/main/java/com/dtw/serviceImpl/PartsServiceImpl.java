package com.dtw.serviceImpl;


import com.dtw.dtos.requestDtos.PartRequestDto;
import com.dtw.dtos.responseDtos.PartResponseDto;
import com.dtw.entity.Document;
import com.dtw.entity.Part;
import com.dtw.exception.ResourceNotFoundException;
import com.dtw.mapper.PartMapper;
import com.dtw.repo.DocumentRepo;
import com.dtw.repo.PartsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PartsServiceImpl {


    @Autowired
    private PartsRepo partsRepo;

    @Autowired
    private DocumentRepo documentRepo;

    public PartResponseDto getSingle(Long id) {
        Part part = partsRepo.findById(id)
                .orElseThrow(( ) -> new ResourceNotFoundException("Part " , "Part" , id));

        return PartMapper.MAPPER.mapToPartResponseDto(part);
    }

    public List<PartResponseDto> getAllPartsOfDocument(Long id) {
        Document  document = documentRepo.findById(id)
                .orElseThrow( ( ) ->new ResourceNotFoundException("Part not found " , "Part " , id));

       return  partsRepo.findByDocument(document)
               .map(PartMapper.MAPPER::mapToPartResponseDto)
               .collect(Collectors.toList());

    }

    public PartResponseDto add(Long id, PartRequestDto partDto) {
        Document document = documentRepo.findById(id)
                .orElseThrow(( ) -> new ResourceNotFoundException("Document not found", "Document" , id));


        System.out.println(document.getId());
        Part partEntity = PartMapper.MAPPER.mapToPart(partDto);
        partEntity.setDocument(document);

        Part part = partsRepo.save(partEntity);
        return PartMapper.MAPPER.mapToPartResponseDto(partEntity);

    }

    public PartResponseDto delete(Long id) {

        Part foundPart = partsRepo.findById(id)
                .orElseThrow(( ) -> new ResourceNotFoundException("Part not found" , "Part" , id));


        partsRepo.delete(foundPart);

        return PartMapper.MAPPER.mapToPartResponseDto(foundPart);
    }

    public PartResponseDto update(Long id,  PartRequestDto partDto) {

        Part foundPart = partsRepo.findById(id)
                .orElseThrow(( ) -> new ResourceNotFoundException("Part not found" , "Part" , id));


        foundPart.setPartTitle(partDto.getPartTitle());
        foundPart.setResource(partDto.getResource());


        partsRepo.save(foundPart);
        return PartMapper.MAPPER.mapToPartResponseDto(foundPart);

    }
}
