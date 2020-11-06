package com.todo.app.util;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.todo.app.dto.User;

@Service
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(User.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userEmail", "unmKey", "** Email required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "passKey", "** password required");
		User user = (User) target;

		if (user.getUserPassword() != null) {
			if (user.getUserPassword().length() != 3) {
				errors.rejectValue("userPassword", "passKey", "** password should contain more 2 chars");
			}

		}

	}

}
