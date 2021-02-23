package com.jeanvisoski.abasteceaiapp.service;

import com.jeanvisoski.abasteceaiapp.BaseTest;
import com.jeanvisoski.abasteceaiapp.dtos.RequestConfirmUserDTO;
import com.jeanvisoski.abasteceaiapp.dtos.UserDTO;
import com.jeanvisoski.abasteceaiapp.entity.ConfirmationTokenEntity;
import com.jeanvisoski.abasteceaiapp.repository.ConfirmationTokenRepository;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
@SpringBootTest
@AutoConfigureMockMvc
public class ConfirmationTokenServiceTest extends BaseTest {

    @Autowired
    private ConfirmationTokenService service;

    @MockBean
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Test
    public void testSignUpUser() throws NotFoundException {
        RequestConfirmUserDTO token = RequestConfirmUserDTO.builder()
                .token("token")
                .build();

        UserDTO user = UserDTO.builder()
                .id(1L)
                .name("Jean")
                .surname("Visoski")
                .email("jean@teste.com.br")
                .password("1234")
                .build();

        ConfirmationTokenEntity returnToken = ConfirmationTokenEntity.builder()
                .id(1L)
                .confirmationToken("token")
                .createdDate(LocalDate.now())
                .user(UserDTO.toEntity(user))
                .build();

        when(confirmationTokenRepository.findByConfirmationToken(any())).thenReturn(Optional.of(returnToken));

        String response = service.confirmUser(token);

        assertThat(response).isEqualTo("Usuário confirmado com sucesso!");
    }

}