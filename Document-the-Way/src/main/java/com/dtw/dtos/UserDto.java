package com.dtw.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    private Long id;

    @NotBlank
    private String username;
    @NotBlank
    private String fullName;
    @NotBlank
    private String password;

}
