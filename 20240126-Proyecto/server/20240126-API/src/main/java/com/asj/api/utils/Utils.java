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

			System.out.println("Validation errors occurred:");
			System.out.println(errors);
		}

		return errors;
	}

	public static Map<String, Object> successResponse(String message, Integer id) {
		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("id", id);
		return response;
	}

	public static Map<String, String> errorResponse(String message) {
		Map<String, String> response = new HashMap<>();
		response.put("error", message);
		return response;
	}

}