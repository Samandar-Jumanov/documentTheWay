package com.dtw.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "users")
@Builder
public class User   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column( unique = true)
    private String username;

    private String fullName;
    private String password;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Document> documents = new ArrayList<Document>();


    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<Feedback>();


    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<RepostedDocument> reposts = new ArrayList<RepostedDocument>();

    @OneToMany(mappedBy = "recipient" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<Notification>();


    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Purchase> purchases = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
