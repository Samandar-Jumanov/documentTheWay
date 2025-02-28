package com.dtw.controller;


import com.dtw.dtos.requestDtos.PartRequestDto;
import com.dtw.dtos.responseDtos.PartResponseDto;
import com.dtw.serviceImpl.PartsServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
@Tag(name = "Parts")
public class PartsController {

    @Autowired
    private PartsServiceImpl partsService;

    @GetMapping("/{id}")
    public ResponseEntity<PartResponseDto> getSinglePart(
            @PathVariable Long id
    ){

        PartResponseDto part = partsService.getSingle(id);
        return new ResponseEntity<PartResponseDto>( part , HttpStatus.OK);

    }


    @GetMapping("/document/{id}/all")
    public ResponseEntity<List<PartResponseDto>> getDocumentParts(
            @PathVariable Long id
    ){
        List<PartResponseDto> parts = partsService.getAllPartsOfDocument(id);
        return  new ResponseEntity<List<PartResponseDto>>( parts ,HttpStatus.OK);
    }



    @PostMapping("/document/{id}")
    public ResponseEntity<PartResponseDto> addPart(
            @PathVariable Long id ,
            @Valid @RequestBody PartRequestDto partDto
    ){
        PartResponseDto addedPart = partsService.add(id , partDto);
        return new ResponseEntity<PartResponseDto>(addedPart , HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<PartResponseDto> deletePart(
              @PathVariable Long id
    ){

        PartResponseDto deletedPart = partsService.delete(id);
        return new ResponseEntity<PartResponseDto>(deletedPart , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartResponseDto> updatePart(
            @PathVariable Long id,
            @Valid @RequestBody PartRequestDto partDto
    ){
        PartResponseDto updatedPartDto = partsService.update(id, partDto);
        return new ResponseEntity<PartResponseDto>(updatedPartDto , HttpStatus.OK);
    }


}
