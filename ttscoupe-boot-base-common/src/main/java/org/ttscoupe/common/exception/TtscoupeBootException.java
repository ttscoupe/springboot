package org.ttscoupe.common.exception;

public class TtscoupeBootException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TtscoupeBootException(String message){
		super(message);
	}
	
	public TtscoupeBootException(Throwable cause)
	{
		super(cause);
	}
	
	public TtscoupeBootException(String message,Throwable cause)
	{
		super(message,cause);
	}
}
