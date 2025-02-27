package com.dtw.controller;


import com.dtw.dtos.PurchaseDto;
import com.dtw.serviceImpl.PurchaseServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@Tag(name = "Purchase")
public class PurchaseController {


    @Autowired
    private PurchaseServiceImpl purchaseService;

    @GetMapping
    public ResponseEntity<List<PurchaseDto>> getAllPurchases(){
        return new ResponseEntity<>(purchaseService.getAllPurchases() , HttpStatus.OK);
    } // get all of your purchased documents

    @GetMapping("/{id}")
    public  ResponseEntity<PurchaseDto> getPurchase(
            @PathVariable Long id
    ){
        System.out.println(id);
         return  new ResponseEntity<>(purchaseService.getPurchase(id) , HttpStatus.OK);
    } // get purchased document


    @PostMapping("/{id}")
    public ResponseEntity<PurchaseDto> purchaseDocument(
            @PathVariable Long id
    ){
        PurchaseDto purchasedDocument = purchaseService.purchaseDocument(id);
        return  new ResponseEntity<>(purchasedDocument , HttpStatus.CREATED);
    } // purchase a document


}
