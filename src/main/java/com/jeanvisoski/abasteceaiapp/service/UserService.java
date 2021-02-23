package com.jeanvisoski.abasteceaiapp.service;

import com.jeanvisoski.abasteceaiapp.dtos.UserDTO;
import com.jeanvisoski.abasteceaiapp.entity.ConfirmationTokenEntity;
import com.jeanvisoski.abasteceaiapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format ( " Usuário com e-mail {0} não pode ser encontrado. " , email)));
    }

    public String signUpUser(UserDTO user){
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new InternalError("O email informado já existe!");
        }else if (userRepository.existsByNameAndSurname(user.getName(), user.getSurname())){
            throw new InternalError("O Nome e sobrenome informado já existe!");
        }

        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        final ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(UserDTO.toEntity(user));
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        log.debug("UserService.signUpUser - End - Input {}", confirmationToken);

        return confirmationToken.getConfirmationToken();
    }
}
