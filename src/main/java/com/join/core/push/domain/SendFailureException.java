package com.join.core.push.domain;

import lombok.Getter;

@Getter
public class SendFailureException extends Exception {

	private final Vendor vendor;
	private final String title;
	private final String body;
	private final String errorMessage;

	public SendFailureException(Vendor vendor, String title, String body, String errorMessage) {
		super(errorMessage);
		this.vendor = vendor;
		this.title = title;
		this.body = body;
		this.errorMessage = errorMessage;
	}

}
