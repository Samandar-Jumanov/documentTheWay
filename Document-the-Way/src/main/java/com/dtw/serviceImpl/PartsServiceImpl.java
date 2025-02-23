package com.dtw.serviceImpl;


import com.dtw.dtos.PartDto;
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

    public PartDto getSingle(Long id) {
        Part part = partsRepo.findById(id)
                .orElseThrow(( ) -> new ResourceNotFoundException("Part " , "Part" , id));

        return PartMapper.MAPPER.mapPartToPartDto(part);
    }

    public List<PartDto> getAllPartsOfDocument(Long id) {
        Document  document = documentRepo.findById(id)
                .orElseThrow( ( ) ->new ResourceNotFoundException("Part not found " , "Part " , id));

       return  partsRepo.findByDocument(document)
               .map(PartMapper.MAPPER::mapPartToPartDto)
               .collect(Collectors.toList());

    }

    public PartDto add(Long id, PartDto partDto) {
        Document document = documentRepo.findById(id)
                .orElseThrow(( ) -> new ResourceNotFoundException("Document not found", "Document" , id));


        System.out.println(document.getId());
        Part partEntity = PartMapper.MAPPER.mapPartDtoToPart(partDto);
        partEntity.setDocument(document);

        Part part = partsRepo.save(partEntity);
        return PartMapper.MAPPER.mapPartToPartDto(partEntity);

    }

    public PartDto delete(Long id) {

        Part foundPart = partsRepo.findById(id)
                .orElseThrow(( ) -> new ResourceNotFoundException("Part not found" , "Part" , id));


        partsRepo.delete(foundPart);

        return PartMapper.MAPPER.mapPartToPartDto(foundPart);
    }

    public PartDto update(Long id,  PartDto partDto) {

        Part foundPart = partsRepo.findById(id)
                .orElseThrow(( ) -> new ResourceNotFoundException("Part not found" , "Part" , id));


        foundPart.setPartTitle(partDto.getPartTitle());
        foundPart.setResource(partDto.getResource());


        partsRepo.save(foundPart);
        return PartMapper.MAPPER.mapPartToPartDto(foundPart);

    }
}
