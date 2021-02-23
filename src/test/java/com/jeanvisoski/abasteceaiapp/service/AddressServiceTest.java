package com.jeanvisoski.abasteceaiapp.service;

import com.jeanvisoski.abasteceaiapp.BaseTest;
import com.jeanvisoski.abasteceaiapp.dtos.AddressDTO;
import com.jeanvisoski.abasteceaiapp.entity.AddressEntity;
import com.jeanvisoski.abasteceaiapp.repository.AddressRepository;
import javafx.beans.binding.When;
import javassist.NotFoundException;
import org.assertj.core.api.OptionalAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
@SpringBootTest
@AutoConfigureMockMvc
public class AddressServiceTest extends BaseTest {

    @Autowired
    private AddressService service;

    @MockBean
    private AddressRepository repository;

    @Test
    public void testSaveAddress() {
        AddressEntity addressEntity = AddressEntity.builder()
                .id(1)
                .cep("99700000")
                .endereco("jacaranda")
                .numero(135)
                .cidade("Erechim update")
                .bairro("Copas verdes")
                .uf("rs")
                .build();

        when(repository.save(any())).thenReturn(addressEntity);

        AddressEntity response = service.saveAddress(AddressDTO.builder()
                .id(1)
                .cep("99700000")
                .endereco("jacaranda")
                .numero(135)
                .cidade("Erechim")
                .bairro("Copas verdes")
                .uf("rs")
                .build());

        assertThat(response).isEqualTo(addressEntity);
    }

    @Test
    public void testSearchAddress() throws NotFoundException {
        when(repository.findByCep(any())).thenReturn(Optional.of(AddressEntity.builder()
                .id(1)
                .cep("99700000")
                .endereco("jacaranda")
                .numero(135)
                .cidade("Erechim")
                .bairro("Copas verdes")
                .uf("rs")
                .build()));

        AddressEntity expectResponse = AddressEntity.builder()
                .id(1)
                .cep("99700000")
                .endereco("jacaranda")
                .numero(135)
                .cidade("Erechim")
                .bairro("Copas verdes")
                .uf("rs")
                .build();

        AddressEntity response = service.searchAddress("99700000");

        assertThat(response).isEqualTo(expectResponse);
    }

    @Test
    public void testUpdateAddress() throws NotFoundException {
        AddressEntity addressEntity = AddressEntity.builder()
                .id(1)
                .cep("99700000")
                .endereco("jacaranda")
                .numero(135)
                .cidade("Erechim update")
                .bairro("Copas verdes")
                .uf("rs")
                .build();

        when(repository.findByCep(any())).thenReturn(Optional.of(addressEntity));
        when(repository.save(any())).thenReturn(addressEntity);

        AddressEntity response = service.updateAddress("99700000",AddressDTO.builder()
                .id(1)
                .cep("99700000")
                .endereco("jacaranda")
                .numero(135)
                .cidade("Erechim update")
                .bairro("Copas verdes")
                .uf("rs")
                .build());

        assertThat(response).isEqualTo(addressEntity);
    }

    @Test
    public void testDeleteAddress() throws NotFoundException {
        AddressEntity addressEntity = AddressEntity.builder()
                .id(1)
                .cep("99700000")
                .endereco("jacaranda")
                .numero(135)
                .cidade("Erechim update")
                .bairro("Copas verdes")
                .uf("rs")
                .build();

        when(repository.findByCep(any())).thenReturn(Optional.of(addressEntity));

        assertThat(service.deleteAddress("99700000")).isEqualTo("Deletado com sucesso!");
    }

}