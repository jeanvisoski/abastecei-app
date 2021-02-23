package com.jeanvisoski.abasteceaiapp.dtos;

import com.jeanvisoski.abasteceaiapp.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull(message = "name deve ser preenchido!")
    private String name;

    @NotNull(message = "surname deve ser preenchido!")
    private String surname;

    @NotNull(message = "email deve ser preenchido!")
    private String email;

    @NotNull(message = "password deve ser preenchido!")
    private String password;

    public static UserEntity toEntity(UserDTO dto) {
        return UserEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
