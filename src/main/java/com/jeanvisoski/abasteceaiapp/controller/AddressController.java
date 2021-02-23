package com.jeanvisoski.abasteceaiapp.controller;

import com.jeanvisoski.abasteceaiapp.dtos.AddressDTO;
import com.jeanvisoski.abasteceaiapp.repository.AddressRepository;
import com.jeanvisoski.abasteceaiapp.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping(value = "/api/v1/address")
@Api(value = "Address", tags = "Address")
@AllArgsConstructor
public class AddressController {

    private final AddressService service;

    private final AddressRepository repository;

    @ApiOperation(value = "Criar um endereço")
    @PostMapping
    public ResponseEntity<AddressDTO> saveAddress(@Valid @RequestBody AddressDTO addressDTO){
        if (repository.existsByCep(addressDTO.getCep())) {
            throw new InternalError("O Cep informado já existe!");
        }else if (repository.existsByBairroAndCepAndCidadeAndEnderecoAndNumeroAndUf(
                addressDTO.getBairro(),
                addressDTO.getCep(),
                addressDTO.getCidade(),
                addressDTO.getEndereco(),
                addressDTO.getNumero(),
                addressDTO.getUf())){
            throw new InternalError("O Endereco informado já existe!");
        }

        log.debug("AddressController.saveAddress - Start - Input {}", addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(AddressDTO.toDTO(service.saveAddress(addressDTO)));
    }

    @ApiOperation(value = "Buscar um endereço a partir do CEP")
    @GetMapping(value = "/{cep}")
    public ResponseEntity<AddressDTO> searchAddress(@PathVariable("cep") String cep) throws NotFoundException {
        log.debug("AddressController.searchAddress - Start - Input {}", cep);
        return ResponseEntity.ok(AddressDTO.toDTO(service.searchAddress(cep)));
    }

    @ApiOperation(value = "Atualizar um endereço a partir do CEP")
    @PutMapping(value = "/{cep}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable("cep") String cep, @Valid @RequestBody AddressDTO addressDTO) throws NotFoundException {
        log.debug("AddressController.updateAddress - Start - Input {}", addressDTO);
        return ResponseEntity.ok(AddressDTO.toDTO(service.updateAddress(cep, addressDTO)));
    }

    @ApiOperation(value = "Deletar um endereço a partir do CEP")
    @DeleteMapping(value = "/{cep}")
    public ResponseEntity<String> deleteAddress(@PathVariable("cep") String cep) {
        log.debug("AddressController.deleteAddress - Start - Input {}", cep);
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteAddress(cep));
    }
}
