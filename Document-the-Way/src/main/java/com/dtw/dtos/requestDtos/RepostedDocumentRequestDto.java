package com.dtw.dtos.requestDtos;

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
public class RepostedDocumentRequestDto {


    private Long id;
    private Document document;
    private User user;

    public RepostedDocumentRequestDto(User currentUser, Document foundDocument) {
        this.document = foundDocument;
        this.user = currentUser;
    }


}
