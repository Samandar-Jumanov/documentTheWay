package com.dtw.entity;


import com.dtw.enums.PartsEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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


}
