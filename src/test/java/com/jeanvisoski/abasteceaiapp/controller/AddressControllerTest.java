package com.jeanvisoski.abasteceaiapp.controller;

import com.jeanvisoski.abasteceaiapp.BaseTest;
import com.jeanvisoski.abasteceaiapp.entity.AddressEntity;
import com.jeanvisoski.abasteceaiapp.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest extends BaseTest {

    private static final String URL = "/api/v1/address";

    @MockBean
    private AddressService service;

    @Test
    public void testSaveAddress() throws Exception {

        when(service.saveAddress(any())).thenReturn(AddressEntity.builder()
                .id(1)
                .cep("99704642")
                .endereco("rua jacaranda")
                .numero(135)
                .cidade("Erechim")
                .bairro("Copas verdes")
                .uf("rs")
                .build());

        mockMvc.perform(post(URL)
                .contentType(APPLICATION_JSON_UTF8)
                .content(jsonAsString("json/addressRequest.json")))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().json(jsonAsString("json/addressResponse.json")));
    }

    @Test
    public void testSearchAddress() throws Exception {

        when(service.searchAddress(any())).thenReturn(AddressEntity.builder()
                .id(1)
                .cep("99704642")
                .endereco("rua jacaranda")
                .numero(135)
                .cidade("Erechim")
                .bairro("Copas verdes")
                .uf("rs")
                .build());

        mockMvc.perform(get(URL.concat("/99704642"))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().json(jsonAsString("json/addressResponse.json")));
    }

    @Test
    public void testUpdateAddress() throws Exception {

        when(service.updateAddress(any(), any())).thenReturn(AddressEntity.builder()
                .id(1)
                .cep("99704642")
                .endereco("rua jacaranda")
                .numero(135)
                .cidade("Erechim")
                .bairro("Copas verdes")
                .uf("rs")
                .build());

        mockMvc.perform(put(URL.concat("/99704642"))
                .contentType(APPLICATION_JSON_UTF8)
                .content(jsonAsString("json/addressUpdateRequest.json")))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().json(jsonAsString("json/addressResponse.json")));
    }

    @Test
    public void testDeleteAddress() throws Exception {
        mockMvc.perform(delete(URL.concat("/99704642"))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

}