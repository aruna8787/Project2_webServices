package com.aruna.bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement
@XmlType(propOrder = {"status", "message"})
@JsonPropertyOrder({"status", "message"})
public class ResponseMessage {

	private boolean status;
	private String message;
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	
	}
	public String toString()
	{
		return "status: "+status+ ",    message:  "+message;
	}
	
}