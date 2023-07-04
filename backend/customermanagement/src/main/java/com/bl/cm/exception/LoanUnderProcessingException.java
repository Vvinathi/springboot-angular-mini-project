package com.bl.cm.exception;
public class LoanUnderProcessingException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    public LoanUnderProcessingException(String message) {
        super(message);
    }
}