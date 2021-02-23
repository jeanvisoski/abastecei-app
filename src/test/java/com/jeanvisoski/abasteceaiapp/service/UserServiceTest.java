package com.jeanvisoski.abasteceaiapp.service;

import com.jeanvisoski.abasteceaiapp.BaseTest;
import com.jeanvisoski.abasteceaiapp.dtos.UserDTO;
import com.jeanvisoski.abasteceaiapp.entity.AddressEntity;
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
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService service;

    @MockBean
    private ConfirmationTokenRepository confirmationTokenRepository;

    @MockBean
    private ConfirmationTokenService confirmationTokenService;

    @Test
    public void testSignUpUser() throws NotFoundException {
        UserDTO user = UserDTO.builder()
                .id(1L)
                .name("Jean")
                .surname("Visoski")
                .email("jean@teste.com.br")
                .password("1234")
                .build();

        ConfirmationTokenEntity token = ConfirmationTokenEntity.builder()
                .id(1L)
                .confirmationToken("token")
                .createdDate(LocalDate.now())
                .user(UserDTO.toEntity(user))
                .build();

        when(confirmationTokenRepository.save(any())).thenReturn(Optional.of(token));

        String response = service.signUpUser(user);

        assertThat(response).isNotNull();
    }

}