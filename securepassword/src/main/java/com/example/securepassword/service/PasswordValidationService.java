package com.example.securepassword.service;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Service
public class PasswordValidationService {
    private static final Pattern UPPER_CASE_PATTERN = Pattern.compile(".*[A-Z].*");
    private static final Pattern LOWER_CASE_PATTERN = Pattern.compile(".*[a-z].*");
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*[0-9].*");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[!@#$%^&*].*");

    public List<String> validate(String password) {
        log.info("Iniciando a validação da senha");

        List<String> errors = new ArrayList<>();

        if (!isValidLength(password)) {
            String error = "A senha deve possuir pelo menos 8 caracteres";
            errors.add(error);
            log.warn(error);
        }
        if (!UPPER_CASE_PATTERN.matcher(password).find()) {
            String error = "A senha deve possuir pelo menos uma letra maiúscula";
            errors.add(error);
            log.warn(error);
        }
        if (!LOWER_CASE_PATTERN.matcher(password).find()) {
            String error = "A senha deve possuir pelo menos uma letra minúscula";
            errors.add(error);
            log.warn(error);
        }
        if (!DIGIT_PATTERN.matcher(password).find()) {
            String error = "A senha deve possuir pelo menos um número";
            errors.add(error);
            log.warn(error);
        }
        if (!SPECIAL_CHAR_PATTERN.matcher(password).find()) {
            String error = "A senha deve possuir pelo menos um caractere especial";
            errors.add(error);
            log.warn(error);
        }

        if (errors.isEmpty()) {
            log.info("A senha é valida");
        } else {
            log.warn("A validação da senha falhoy com erros: {}", errors);
        }

        log.info("Validação da senha finalizada");
        return errors;
    }

    private boolean isValidLength(String password) {
        return !StringUtils.isBlank(password) && password.length() >= 8;
    }
}
