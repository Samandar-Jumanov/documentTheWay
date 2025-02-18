package com.dtw.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PartsEnum {
    BEGINNER("Beginner"),
    PRE_INTERMEDIATE("Pre-Intermediate"),
    INTERMEDIATE("Intermediate"),
    UPPER_INTERMEDIATE("Upper"),
    ADVANCED("Advanced");
    private String partName ;
}


