package com.dtw.dtos.requestDtos;


import com.dtw.entity.Document;
import com.dtw.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseRequestDto {

    private Long id;
    private User user;
    private Document purchase;

    public PurchaseRequestDto(User currentUser, Document foundDocument) {
        this.purchase = foundDocument;
        this.user = currentUser;
    }

}
