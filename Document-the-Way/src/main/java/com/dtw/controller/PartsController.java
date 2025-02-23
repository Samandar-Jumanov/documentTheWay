package com.dtw.controller;


import com.dtw.dtos.PartDto;
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
    public ResponseEntity<PartDto> getSinglePart(
            @PathVariable Long id
    ){

        PartDto part = partsService.getSingle(id);
        return new ResponseEntity<PartDto>( part , HttpStatus.OK);

    }


    @GetMapping("/document/{id}/all")
    public ResponseEntity<List<PartDto>> getDocumentParts(
            @PathVariable Long id
    ){
        List<PartDto> parts = partsService.getAllPartsOfDocument(id);
        return  new ResponseEntity<List<PartDto>>( parts ,HttpStatus.OK);
    }



    @PostMapping("/document/{id}")
    public ResponseEntity<PartDto> addPart(
            @PathVariable Long id ,
            @Valid @RequestBody PartDto partDto
    ){
        PartDto addedPart = partsService.add(id , partDto);
        return new ResponseEntity<PartDto>(addedPart , HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<PartDto> deletePart(
              @PathVariable Long id
    ){

        PartDto deletedPart = partsService.delete(id);
        return new ResponseEntity<PartDto>(deletedPart , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartDto> updatePart(
            @PathVariable Long id,
            @Valid @RequestBody PartDto partDto
    ){
        PartDto updatedPartDto = partsService.update(id, partDto);
        return new ResponseEntity<PartDto>(updatedPartDto , HttpStatus.OK);
    }


}
