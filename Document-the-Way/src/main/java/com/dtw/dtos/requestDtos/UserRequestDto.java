package com.dtw.dtos.requestDtos;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {


    private Long id;

    @NotBlank
    private String username;
    @NotBlank
    private String fullName;
    @NotBlank
    private String password;



}
