package com.betsol.assignment.web_service;
import java.sql.SQLException;
import java.util.List;

import com.betsol.authentication.*;
import com.betsol.database.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	@Path("create") 
    @POST
   //@Produces(MediaType.TEXT_PLAIN)
    public String  create(String data) {
		Database database = new Database();
    	database.create_user(data);
    	return "sucessful";
    	
}
	@Path("retrieve")
	@POST
	@Consumes({MediaType.TEXT_PLAIN})
	@Produces({"application/json"})
	public Response retrieve(String name){
		Response res= null;
		try {
		res = Database.retrieve_user(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	@Path("update")
	@POST
	@Consumes({MediaType.TEXT_PLAIN})
	@Produces({"application/json"})
	public Response update(String name) {
		
		Response response = Database.update_user(name);
		return response;
	}
	
	@Path("login")
	@POST
	@Consumes({"application/json"})
	//@Produces("text/plain")
	public String login(ContainerRequestContext requestContext) {
	
	    List<String> usernameAndPassword;
        Authentication authentication = new Authentication();
        usernameAndPassword = authentication.extractHeaderInfo(requestContext);
        System.out.println(usernameAndPassword.get(0));
        System.out.println(usernameAndPassword.get(1));
        return "Log in Successful";
	}
	 
 }
