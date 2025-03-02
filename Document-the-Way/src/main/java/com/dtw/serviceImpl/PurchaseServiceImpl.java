package com.dtw.serviceImpl;


// Get particular purchase for a user
// Get access for a user
// Add comment for documents comment
// Remove or update Comment for a particular comment


import com.dtw.dtos.requestDtos.PurchaseRequestDto;
import com.dtw.dtos.responseDtos.PurchaseResponseDto;
import com.dtw.entity.Document;
import com.dtw.entity.Purchase;
import com.dtw.entity.User;
import com.dtw.exception.NotGrantedAccess;
import com.dtw.exception.ResourceNotFoundException;
import com.dtw.mapper.PurchaseMapper;
import com.dtw.repo.DocumentRepo;
import com.dtw.repo.PurchaseRepo;
import com.dtw.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// Add comment for documents comment
// Remove or update Comment for a particular comment



@Service
@Transactional
public class PurchaseServiceImpl {


    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DocumentRepo documentRepo;

    public List<PurchaseResponseDto> getAllPurchases(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Stream<Purchase> purchaseStream = purchaseRepo.findByUser(currentUser);
        return  purchaseStream.map(PurchaseMapper.MAPPER::mapToPurchaseDto)
                .collect(Collectors.toList());
    }



    public PurchaseResponseDto getPurchase(Long purchaseId ){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));


        Purchase foundPurchase = purchaseRepo.findById(purchaseId)
                .orElseThrow(( ) -> new ResourceNotFoundException("Purchase" , "Purchase not found " , purchaseId));

//        if(foundPurchase.getUser().getId() != currentUser.getId()){
//            throw   new NotGrantedAccess("You have not purchase this course yet " , "Document:" + foundPurchase.getDocument().getTitle() );
//        }
        return PurchaseMapper.MAPPER.mapToPurchaseDto(foundPurchase);
    }


    public PurchaseResponseDto purchaseDocument(Long documentId){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));


        Document foundDocument = documentRepo.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Document" , "Document not found " , documentId));


        PurchaseRequestDto purchaseDto = new PurchaseRequestDto(
                currentUser , foundDocument
        );

        Purchase newPurchase  = purchaseRepo.save(PurchaseMapper.MAPPER.mapToPurchase(purchaseDto));

        // Send notification to user : foundDocument.getUser().getId()
        // Send notification to user : currentUser.getId();

        return  PurchaseMapper.MAPPER.mapToPurchaseDto(newPurchase);

    }


}
