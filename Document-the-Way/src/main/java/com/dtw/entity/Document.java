package com.dtw.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter

public class Document {

    // Have parts , user ,

    @Id
    @Generated
    private Long id;

    // connect with parts

}
