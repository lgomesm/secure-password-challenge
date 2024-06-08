package com.example.securepassword.controller;

import com.example.securepassword.service.PasswordValidationService;
import jakarta.validation.Valid;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/password")
@RequiredArgsConstructor
@Validated
public class PasswordValidationController {
    private final PasswordValidationService passwordValidationService;

    @PostMapping("/validate")
    public ResponseEntity<List<String>> validatePassword(@Valid @RequestBody PasswordRequest passwordRequest) {
        log.info("Validando a senha: {}", passwordRequest.getPassword());
        List<String> validationErrors = passwordValidationService.validate(passwordRequest.getPassword());

        if (validationErrors.isEmpty()) {
            log.info("A senha é valida");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.warn("Validação da senha falhou: {}", validationErrors);
            return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
        }
    }
}
