package com.aruna.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.Entity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.sun.jersey.api.client.ClientResponse.Status;


@WebService(serviceName="service1")
@Entity
@Path("/service1" )
public class Service1 {
		    @GET
		    @Path("/newrequest")
		    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
		    @WebMethod(operationName = "invokeservice1")
		public Response invokeService1(@QueryParam("user") String user) {
		    	 
		     User u=new User();
			 u.setUserName(user);
			 DateFormat time = new SimpleDateFormat("HH:mm:ss");
			 DateFormat date=new SimpleDateFormat("yyyy/MM/dd");
			 Calendar cal = Calendar.getInstance();
			 u.setInvocationDate(date.format(cal.getTime()));
			 u.setInvocationTime(time.format(cal.getTime()));
			 
			 if ( user.toUpperCase().charAt(0) >= 'A' && user.toUpperCase().charAt(0) <= 'M' ) { 
			return Response.status(Status.OK).type(MediaType.APPLICATION_XML).header("Content-Type", "Application/xml").entity(u).build();
			} 
			else {
				return Response.status(Status.OK).type(MediaType.APPLICATION_JSON).header("Content-Type", "Application/json").entity(u).build();
			}
			}
		    
			}
