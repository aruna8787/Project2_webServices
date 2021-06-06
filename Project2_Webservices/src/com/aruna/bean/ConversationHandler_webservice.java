package com.aruna.bean;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.Entity;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.soap.Node;

import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;
import com.sun.jersey.api.client.ClientResponse.Status;


@WebService()
@Entity
@Path("/coordinator")
public class ConversationHandler_webservice {
	
private static Map<String,UserData> users = new HashMap<String,UserData>();
	
@POST
@Path("/initiate")
@Produces("text/html")
@WebMethod(operationName = "requestservice")
public Response requestService(@FormParam("user") String user,@FormParam("serviceName") String serviceName) throws ClientProtocolException, IOException {
	String conversationId=null;	
	UserData u=null;
	String start_state=null;
	String end_state[]=new String[2];
	String content_type=null;
	String targetservice1=null;
	String mediatype1=null;
	String mediatype2=null;
	
	String targetservice2=null;
	String output=null;
	
//code to fetch coordination protocol file and parse 
try {
	
//opening states.xml file
File file = new File("C:/Users/aruna/eclipse-workspace/Project2_Webservices/WebContent/states.xml");

DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
Document doc = dBuilder.parse(file);

NodeList nList = doc.getElementsByTagName("state");
org.w3c.dom.Node flow =doc.getElementsByTagName("flow").item(0);

//extracting conversation flow  from file 
Element conversationflow=(Element) flow;

start_state=conversationflow.getElementsByTagName("start-state").item(0).getTextContent();
end_state[0]=conversationflow.getElementsByTagName("end-state").item(0).getTextContent();
end_state[1]=conversationflow.getElementsByTagName("end-state").item(1).getTextContent();


//checking if the user is already exist in userdata
if (users.containsKey(user)) {
        u=users.get(user);
        conversationId=u.getConversationId();
		
	}		
else {
	
	if(serviceName.equals(start_state)) {
	//creating new user and adding new user info to userData	
	    u =new UserData();
	    u.setUser(user);
	
	   String id=Double.toString(Math.random());
	   u.setConversationId(id);
	   
	  
	   users.put(user,u);
	   conversationId=id;}
	else {
		//returning response message if the service cant be invoked
		ResponseMessage r=new ResponseMessage();
		r.setMessage("you cannot invoke   "+serviceName);
		r.setStatus(false);
		return  Response.status(Status.OK).type("text/html").entity(r.toString()).build();		
        }

}

//checking if the services invoked are not end states in the coordination protocol
if(!(serviceName.equals(end_state[0]) || serviceName.equals(end_state[1]))) {
	
		
	        
			String url="http://localhost/Project2_Webservices/rest/"+serviceName+"/newrequest";
			 //creating client to send request to service
		    HttpClient client = HttpClientBuilder.create().build();
		   	HttpGet request = new HttpGet(url+"?user="+user);
		   	request.setHeader("Accept", "application/json");
		   	HttpResponse response = client.execute(request);
		   	HttpEntity entity = response.getEntity();
		   	
		   	String responseString = EntityUtils.toString(entity, "UTF-8");
		   	System.out.println(responseString);
		   	
		   	//get all headers	and extracting content type	
		   	org.apache.http.Header[] headers = response.getAllHeaders();
		   	for (org.apache.http.Header header : headers) {
		   		if(header.getName().equals("Content-Type")) {
				content_type= header.getValue();
			}}
          
		   	
		   	
//extracting next services can be invoked for the requested service
for (int temp = 0; temp < nList.getLength(); temp++) {

	org.w3c.dom.Node nNode = nList.item(temp);
				
	if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		Element eElement = (Element) nNode;
		String serviceid=eElement.getAttribute("id");
		
	   if(serviceid.equals(serviceName)) {
		   
		     Element transition1=(Element) eElement.getElementsByTagName("transition").item(0);
		     mediatype1= transition1.getAttribute("mediaType");
		     targetservice1= transition1.getAttribute("target");
		
		
		     Element transition2=(Element) eElement.getElementsByTagName("transition").item(1);
		     mediatype2= transition2.getAttribute("mediaType");
		     targetservice2= transition2.getAttribute("target");
		     
	       }
		    	
		     
		     
		
		}
		
	
	}

	          
		           System.out.println(serviceName+" invoked by coordinator");
		           
		           //creating html response  as string 
		           
	          output = "<html><head><meta charset=\"ISO-8859-1\"></head>"
	           		+ "<body style='background-color:#C0C0C0'>"
	           		+"<h3> conversation id:"+conversationId+"</h2>"
	           		+ "<div align=\"center\" >"
	               
	    		   +"<p>"+serviceName +" invoked by___"+responseString+"</p><br>"+
	    		   "<form id=\"serviceName\" action=\"initiate\"  method=\"POST\" onsubmit=\"return get_action()\">"+
	    		   "<input type= \"text\" name=\"user\" value=\""+user+"\" readonly /><br><br>"+
	    		   "<label> select available services:   </label>"+
	              "<select id=\"serviceName\" name=\"serviceName\">"+
	    		   "<option value=\"pick a service\">pick a service";
	          
	          if(content_type.equals(mediatype1)) {
	        	  output=output+ "<option value=\""+targetservice1+"\">"+targetservice1;
	          }else if(content_type.equals(mediatype2)){
	        	  output=output+"<option value=\""+targetservice2+"\">"+targetservice2;
	          }
	    		   output=output+"</select><br><br>"+
	    		   
	    		   "<input type=\"submit\" value=\"submit\"></form>"+
	    		   "</div></body></html>";
	   
}
else{
	/* removing username from data after conversation is over so that 
	user can start conversation again in future as new user.*/
	 users.remove(user);
	output="<html><head><meta charset=\"ISO-8859-1\"></head>"
       		+ "<body style='background-color:#C0C0C0'><div align=\"center\" >"+
            "<h2>you have reached end of conversation</h2>"+
            "</div></body></html>";
	}

}catch(Exception e){
	e.printStackTrace();
	}

	
	           return Response.status(Status.OK).entity(output).build();
	
	}

    @POST
    @Path("/post")
	@Produces("text/plain")
	@Consumes("Application/xml")
	@WebMethod(operationName = "conversation")
	public String displayServices(String xmlfile) {
		System.out.println(xmlfile);
		return xmlfile;
	}
	
}
