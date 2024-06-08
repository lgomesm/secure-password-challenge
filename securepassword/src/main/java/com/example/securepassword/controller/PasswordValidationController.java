package com.example.securepassword.controller;

import com.example.securepassword.service.PasswordValidationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/password")
@RequiredArgsConstructor
@Getter
@Setter
public class PasswordValidationController {
    private PasswordValidationService passwordValidationService;

    @PostMapping("/validate")
    public ResponseEntity<?> validatePassword(@RequestBody PasswordRequest passwordRequest) {
        List<String> validationErrors = passwordValidationService.validate(passwordRequest.getPassword());

        return validationErrors.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

}
