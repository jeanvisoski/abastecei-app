package com.jeanvisoski.abasteceaiapp.dtos;

import com.jeanvisoski.abasteceaiapp.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Integer id;

    @NotNull(message = "cep deve ser preenchido!")
    private String cep;

    @NotNull(message = "endereco deve ser preenchido!")
    private String endereco;

    @NotNull(message = "numero deve ser preenchido!")
    private Integer numero;

    @NotNull(message = "cidade deve ser preenchido!")
    private String cidade;

    @NotNull(message = "bairro deve ser preenchido!")
    private String bairro;

    @NotNull(message = "uf deve ser preenchido!")
    private String uf;

    public static AddressEntity toEntity(AddressDTO dto) {
        return AddressEntity.builder()
                .id(dto.getId())
                .cep(dto.getCep())
                .endereco(dto.getEndereco())
                .numero(dto.getNumero())
                .cidade(dto.getCidade())
                .bairro(dto.getBairro())
                .uf(dto.getUf())
                .build();
    }

    public static AddressDTO toDTO(AddressEntity dto) {
        return AddressDTO.builder()
                .id(dto.getId())
                .cep(dto.getCep())
                .endereco(dto.getEndereco())
                .numero(dto.getNumero())
                .cidade(dto.getCidade())
                .bairro(dto.getBairro())
                .uf(dto.getUf())
                .build();
    }
}
