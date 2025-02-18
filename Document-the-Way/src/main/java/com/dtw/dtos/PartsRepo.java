package com.dtw.dtos;

import com.dtw.enums.PartsEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PartsRepo {

    @NotBlank
    private PartsEnum partName;

}
