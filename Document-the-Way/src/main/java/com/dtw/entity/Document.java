package com.dtw.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table( name = "documents")
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "document" , cascade = CascadeType.ALL , orphanRemoval = true )
    private List<Part> parts = new ArrayList<>();

    @OneToMany(mappedBy = "document" ,cascade = CascadeType.ALL , orphanRemoval = true)
    private List<RepostedDocument> reposts = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id" , nullable = true)
    private Purchase purchase;

    private String title;
    private String description;



    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;




}