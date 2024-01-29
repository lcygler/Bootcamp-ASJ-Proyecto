package com.asj.api.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;

public class Utils {

	public static Map<String, String> handleErrors(BindingResult bindingResult) {
		Map<String, String> errors = new HashMap<>();

		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach(error -> {
				String field = error.getField();
				String errorMsg = error.getDefaultMessage();
				errors.put(field, errorMsg);
			});

			System.out.println(errors);
		}

		return errors;
	}

}