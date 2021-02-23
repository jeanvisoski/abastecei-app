package com.jeanvisoski.abasteceaiapp.controller;

import com.jeanvisoski.abasteceaiapp.dtos.RequestConfirmUserDTO;
import com.jeanvisoski.abasteceaiapp.dtos.UserDTO;
import com.jeanvisoski.abasteceaiapp.service.ConfirmationTokenService;
import com.jeanvisoski.abasteceaiapp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping(value = "/api/v1")
@Api(value = "User", tags = "User")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final ConfirmationTokenService confirmationTokenService;

    @ApiOperation(value = "Cadastrar usuário")
    @PostMapping(value = "/signUpUser")
    public ResponseEntity<String> signUpUser(@Valid @RequestBody UserDTO userDTO){
        log.debug("UserController.signUpUser - Start - Input {}", userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUpUser(userDTO));
    }

    @ApiOperation(value = "Confirmar usuário")
    @PostMapping(value = "/confirmUser")
    public ResponseEntity<String> confirmUser(@Valid @RequestBody RequestConfirmUserDTO requestConfirmUserDTO) throws NotFoundException {
        log.debug("UserController.confirmUser - Start - Input {}", requestConfirmUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body(confirmationTokenService.confirmUser(requestConfirmUserDTO));
    }

}
