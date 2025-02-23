package com.dtw.dtos;


import com.dtw.entity.Document;
import com.dtw.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepostedDocumentDto {

    private Long id;
    private Document document;
    private User user;

    public RepostedDocumentDto(User currentUser, Document foundDocument) {
        this.document = foundDocument;
        this.user = currentUser;
    }


}
