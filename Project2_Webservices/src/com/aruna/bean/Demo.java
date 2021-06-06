package com.aruna.bean;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;


import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;



public class Demo {
	public static void main(String[] args) throws UnirestException, IOException {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"true\"?>\r\n\r\n-<user>\r\n\r\n<userName>hjk</userName>\r\n\r\n<invocationDate>2020/03/17</invocationDate>\r\n\r\n<invocationTime>17:44:18</invocationTime>\r\n<time>time</time>\r\n</user>";
	        Client client = Client.create();
	       WebResource service = client.resource("http://localhost/Project2_Webservices/rest/coordinator/post");
	        Builder res=service.header("Content-Type", "application/xml").accept("text/plain").entity(new File("C://Users/aruna/Desktop/test.xml"));
	        
	        String response = res.post(String.class);
	            System.out.println("XML File : "+response);
	 
	    }
	
}
