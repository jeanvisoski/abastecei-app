package com.jeanvisoski.abasteceaiapp.service;

import com.jeanvisoski.abasteceaiapp.dtos.RequestConfirmUserDTO;
import com.jeanvisoski.abasteceaiapp.entity.ConfirmationTokenEntity;
import com.jeanvisoski.abasteceaiapp.repository.ConfirmationTokenRepository;
import com.jeanvisoski.abasteceaiapp.repository.UserRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final UserRepository userRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository, UserRepository userRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userRepository = userRepository;
    }

    public void saveConfirmationToken(ConfirmationTokenEntity confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public String confirmUser(RequestConfirmUserDTO requestConfirmUserDTO) throws NotFoundException {
        confirmationTokenRepository.findByConfirmationToken(requestConfirmUserDTO.getToken())
                .map(ConfirmationTokenEntity::getUser).map(userEntity -> {
                    userEntity.setEnabled(true);
                    return userRepository.save(userEntity);
        }).orElseThrow(() -> new NotFoundException("Token Não Localizado!"));

        log.debug("ConfirmationTokenService.confirmUser - End - Input {}", requestConfirmUserDTO);

        return "Usuário confirmado com sucesso!";

    }
}
