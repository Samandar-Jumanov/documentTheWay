package com.dtw.dtos.requestDtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {


    private Long id;

    @NotBlank
    @NotNull
    private String username;


    @NotBlank
    @NotNull
    private String password;

}
