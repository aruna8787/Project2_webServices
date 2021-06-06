package com.aruna.bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement
@XmlType(propOrder = {"userName", "invocationDate", "invocationTime"})
@JsonPropertyOrder({"userName", "invocationDate", "invocationTime"})
public class User {
private String userName;
private String invocationDate;
private String invocationTime;


public String getUserName()
{
	return userName;
}
public void setUserName(String name)
{
	this.userName=name;
	}
public String getInvocationDate()
{
	return invocationDate;
}
public void setInvocationDate(String date)
{
	this.invocationDate=date;
	}
public String getInvocationTime()
{
	return invocationTime;
}
public void setInvocationTime(String time)
{
	this.invocationTime=time;
	}

}

