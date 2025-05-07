package com.app.wrapper;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UserResponseWrapper {

	private String message;
	private Object data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
