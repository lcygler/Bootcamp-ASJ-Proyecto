/*
package com.asj.api.archive;

import java.lang.reflect.Field;
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

	public static Map<String, String> handlePartialErrors(BindingResult bindingResult, Object requestObject) {
		Map<String, String> errors = new HashMap<>();

		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach(error -> {
				String field = error.getField();

				if (isFieldPresent(field, requestObject)) {
					String errorMsg = error.getDefaultMessage();
					errors.put(field, errorMsg);
				}
			});

			System.out.println(errors);
		}

		return errors;
	}

	private static boolean isFieldPresent(String fieldName, Object object) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(object) != null;
		} catch (NoSuchFieldException | IllegalAccessException e) {
			return false;
		}
	}

	public static void updateObject(Object existingObject, Object newObject) {
		try {
			for (Field field : existingObject.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object newValue = field.get(newObject);

				if (newValue != null) {
					field.set(existingObject, newValue);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
*/