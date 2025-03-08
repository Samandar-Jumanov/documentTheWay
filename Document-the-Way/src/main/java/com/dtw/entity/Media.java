package com.dtw.entity;

import jakarta.persistence.*;
import lombok.*;


@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mediaType;
    private String mediaUrl;

    @OneToOne
    @JoinColumn(name = "document_id" , nullable = false)
    private Document document;

}
