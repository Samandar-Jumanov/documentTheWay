package com.dtw.dtos;

import com.dtw.entity.Document;
import com.dtw.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PurchaseDto {

    private Long id;
    private User user;
    private Document purchase;

    public PurchaseDto(User currentUser, Document foundDocument) {
        this.purchase = foundDocument;
        this.user = currentUser;
    }
}
