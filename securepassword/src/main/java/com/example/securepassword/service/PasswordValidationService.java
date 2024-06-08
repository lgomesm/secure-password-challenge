package com.example.securepassword.service;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PasswordValidationService {
   private static final String PATTERN = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])";

   public List<String> validate(String password) {
       List<String> errors = new ArrayList<>();

       if (!isValidLength(password)) {
           errors.add("A senha deve possuir pelo menos 8 caracteres");
       } else if (!Pattern.compile(PATTERN).matcher(password).find()) {
           errors.add("A senha deve conter letras maiúsculas, minúsculas, números e caracteres especiais");
       }

       return errors;
   }

    private boolean isValidLength(String password) {
        return !StringUtils.isBlank(password) && password.length() >= 8;
    }
}
