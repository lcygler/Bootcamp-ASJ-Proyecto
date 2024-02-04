package com.asj.api.models.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ValidationErrorResponse {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final Map<Integer, String> STATUS_CODES = new HashMap<>();

	static {
		STATUS_CODES.put(400, "Bad Request");
		STATUS_CODES.put(401, "Unauthorized");
		STATUS_CODES.put(403, "Forbidden");
		STATUS_CODES.put(404, "Not Found");
		STATUS_CODES.put(409, "Conflict");
		STATUS_CODES.put(500, "Internal Server Error");
	}

	private String timestamp;
	private int statusCode;
	private String status;
	private String message;
	private Map<String, String> errors;

	public ValidationErrorResponse(int statusCode, String message, Map<String, String> errors) {
		this.timestamp = LocalDateTime.now().format(FORMATTER);
		this.statusCode = statusCode;
		this.status = STATUS_CODES.getOrDefault(statusCode, "Unknown Error");
		this.message = message;
		this.errors = errors;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ValidationErrorResponse [timestamp=" + timestamp + ", statusCode=" + statusCode + ", status=" + status
				+ ", message=" + message + ", errors=" + errors + "]";
	}

}
