package com.matt.service_base.exception;

public class MattException extends RuntimeException {
	private Integer code;
	private String  meg;
	public MattException() {
	}	
	public MattException(Integer code, String meg) {
		
		this.code = code;
		this.meg = meg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMeg() {
		return meg;
	}
	public void setMeg(String meg) {
		this.meg = meg;
	}
	
	@Override
	public String toString() {
	    return "MattException{" +
	        "message=" + this.getMessage() +
	        ", code=" + code +
	        '}';
	}
}
