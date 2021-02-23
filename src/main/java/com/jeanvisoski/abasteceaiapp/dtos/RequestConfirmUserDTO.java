package com.jeanvisoski.abasteceaiapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestConfirmUserDTO {

    @NotNull(message = "token deve ser preenchido!")
    private String token;
}
