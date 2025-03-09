package com.dtw.dtos.responseDtos;


import com.dtw.entity.Document;
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
public class MediaDto {

    private Long id;

    @NotBlank
    @NotNull
    private String mediaType;

    @NotBlank
    @NotNull
    private String mediaUrl;

    private Document document;

}
