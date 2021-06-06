package com.aruna.bean;

import java.io.IOException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
public class UserClient {
	private String user=null;
   public UserClient(String user) {
	   this.user=user;
   }
	    public ResponseMessage run() throws UnirestException, IOException {
	        Client c = Client.create();
	       WebResource resource = c.resource("http://localhost/Project2_Webservices/rest/userservice/initiate").queryParam("user", user);
	        Builder res=resource.accept("Application/json");
	       String response1 = res.get(String.class);
	       ResponseMessage r =new ResponseMessage();
	       r.setMessage(response1);
	       r.setStatus(true);
	       return r;
	        
	    }
	
}
