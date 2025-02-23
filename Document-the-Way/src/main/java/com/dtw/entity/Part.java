package com.dtw.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "part")
@Builder
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  partTitle;
    private String resource;

    // connect with resources

    @ManyToOne(fetch = FetchType.LAZY)
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY)
    private RepostedDocument repost;

    @CreationTimestamp
    private LocalTime createdAt;

    @UpdateTimestamp
    private LocalTime updatedAt;


}
