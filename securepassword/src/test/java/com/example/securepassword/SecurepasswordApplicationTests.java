package com.example.securepassword;

import com.example.securepassword.service.PasswordValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SecurepasswordApplicationTests {
	private PasswordValidationService passwordValidationService;

	@BeforeEach
	void setUp() {
		passwordValidationService = new PasswordValidationService();
	}

	@Test
	void testValidPassword() {
		String password = "Valid@123";
		List<String> errors = passwordValidationService.validate(password);
		assertEquals(0, errors.size());
	}

	@Test
	void testShortPassword() {
		String password = "Va1@";
		List<String> errors = passwordValidationService.validate(password);
		assertEquals(1, errors.size());
		assertEquals("A senha deve possuir pelo menos 8 caracteres", errors.get(0));
	}

	@Test
	void testMissingUpperCase() {
		String password = "valid@123";
		List<String> errors = passwordValidationService.validate(password);
		assertEquals(1, errors.size());
		assertEquals("A senha deve possuir pelo menos uma letra maiúscula", errors.get(0));
	}

	@Test
	void testMissingLowerCase() {
		String password = "VALID@123";
		List<String> errors = passwordValidationService.validate(password);
		assertEquals(1, errors.size());
		assertEquals("A senha deve possuir pelo menos uma letra minúscula", errors.get(0));
	}

	@Test
	void testMissingDigit() {
		String password = "Valid@abc";
		List<String> errors = passwordValidationService.validate(password);
		assertEquals(1, errors.size());
		assertEquals("A senha deve possuir pelo menos um número", errors.get(0));
	}

	@Test
	void testMissingSpecialChar() {
		String password = "Valid123";
		List<String> errors = passwordValidationService.validate(password);
		assertEquals(1, errors.size());
		assertEquals("A senha deve possuir pelo menos um caractere especial", errors.get(0));
	}

}
