package com.dtw.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalTime;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)

    private User user;

    @ManyToOne(fetch = FetchType.EAGER)

    @PrimaryKeyJoinColumn(name = "document_id")
    private Document purchase;


    @CreationTimestamp
    private LocalTime createdAt;

    @UpdateTimestamp
    private LocalTime updatedAt;

}
