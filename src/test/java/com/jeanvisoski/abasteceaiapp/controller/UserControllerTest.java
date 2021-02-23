package com.jeanvisoski.abasteceaiapp.controller;

import com.jeanvisoski.abasteceaiapp.BaseTest;
import com.jeanvisoski.abasteceaiapp.entity.AddressEntity;
import com.jeanvisoski.abasteceaiapp.entity.UserEntity;
import com.jeanvisoski.abasteceaiapp.service.AddressService;
import com.jeanvisoski.abasteceaiapp.service.ConfirmationTokenService;
import com.jeanvisoski.abasteceaiapp.service.UserService;
import junit.framework.TestCase;
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
public class UserControllerTest extends BaseTest {


    private static final String URL = "/api/v1";

    @MockBean
    private UserService service;

    @MockBean
    private ConfirmationTokenService confirmationTokenService;

    @Test
    public void testSaveAddress() throws Exception {

        when(service.signUpUser(any())).thenReturn("token");

        mockMvc.perform(post(URL.concat("/signUpUser"))
                .contentType(APPLICATION_JSON_UTF8)
                .content(jsonAsString("json/userRequest.json")))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().string("token"));
    }

    @Test
    public void testConfirmUser() throws Exception {

        when(confirmationTokenService.confirmUser(any())).thenReturn("Usuário confirmado com sucesso!");

        mockMvc.perform(post(URL.concat("/confirmUser"))
                .contentType(APPLICATION_JSON_UTF8)
                .content(jsonAsString("json/confirmUserRequest.json")))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().string("Usuário confirmado com sucesso!"));
    }

}