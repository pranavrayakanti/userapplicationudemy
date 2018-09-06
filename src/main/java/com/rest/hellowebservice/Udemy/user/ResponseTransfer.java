package com.rest.hellowebservice.Udemy.user;

public class ResponseTransfer {

	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	String text;

	public ResponseTransfer(String text) {
		super();
		this.text = text;
	}
}
