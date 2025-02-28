package com.dtw.dtos.requestDtos;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PartRequestDto {

    private Long id ;

    @NotNull
    private String partTitle;

    @NotNull
    private String resource;

}
