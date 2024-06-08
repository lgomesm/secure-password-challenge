package com.example.securepassword.service;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PasswordValidationService {
    private static final Pattern UPPER_CASE_PATTERN = Pattern.compile(".*[A-Z].*");
    private static final Pattern LOWER_CASE_PATTERN = Pattern.compile(".*[a-z].*");
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*[0-9].*");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[!@#$%^&*].*");

    public List<String> validate(String password) {
        List<String> errors = new ArrayList<>();

        if (!isValidLength(password)) {
            errors.add("A senha deve possuir pelo menos 8 caracteres");
        }
        if (!UPPER_CASE_PATTERN.matcher(password).find()) {
            errors.add("A senha deve possuir pelo menos uma letra maiúscula");
        }
        if (!LOWER_CASE_PATTERN.matcher(password).find()) {
            errors.add("A senha deve possuir pelo menos uma letra minúscula");
        }
        if (!DIGIT_PATTERN.matcher(password).find()) {
            errors.add("A senha deve possuir pelo menos um número");
        }
        if (!SPECIAL_CHAR_PATTERN.matcher(password).find()) {
            errors.add("A senha deve possuir pelo menos um caractere especial");
        }

        return errors;
    }

    private boolean isValidLength(String password) {
        return !StringUtils.isBlank(password) && password.length() >= 8;
    }
}
