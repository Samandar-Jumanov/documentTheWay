package com.dtw.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RepostedDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)

    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Document document;

    @OneToMany(mappedBy = "repost" , cascade = CascadeType.ALL , orphanRemoval = true)
    List<Part> parts = new ArrayList<>();


}
