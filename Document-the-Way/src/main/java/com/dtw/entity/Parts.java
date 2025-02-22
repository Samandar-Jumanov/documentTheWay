package com.dtw.entity;


import com.dtw.enums.PartsEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Parts {

    @Id
    @Generated
    private Long id;

    private PartsEnum partName;

    // connect with resources

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "document_id" , nullable = false )
    private Document document;


}
