package com.todo.app.util;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.todo.app.dto.User;

@Service
public class SignupValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(User.class);

	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userFullName", "unmKey", "** Full Name required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userEmail", "uemKey", "** Email required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "passKey", "** password required");
		User user = (User) target;

		if (user.getUserFullName() != null) {
			if (user.getUserFullName().length() < 3) {
				errors.rejectValue("userPassword", "passKey", "**Fullname should contain more then 2 characters");
			}

		}

		if (user.getUserPassword() != null) {
			if (user.getUserPassword().length() < 3) {
				errors.rejectValue("userPassword", "passKey", "** password should contain more then 2 characters");
			}

		}
		if (user.getUserEmail() != null) {
			if (user.getUserEmail().length() < 3) {
				errors.rejectValue("userPassword", "passKey", "** email should contain more then 2 characters");
			}

		}
	}

}
