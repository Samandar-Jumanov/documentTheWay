package com.dtw.dtos;

import com.dtw.enums.PartsEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartDto {


    private Long id ;

    @NotNull
    private String partTitle;

    @NotNull
    private String resource;

}
