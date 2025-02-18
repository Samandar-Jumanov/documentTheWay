package com.dtw.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Getter
@Setter
public class Contents {

    @Id
    @Generated
    private Long id;

    private String name;
    private String resourceUrl;


}
