package service;

import static spark.Spark.*;

import com.google.gson.Gson;

import model.User;
import spark.Request;
import spark.Response;
import controllers.UserController;

public class UserWebService {
	
	private static final UserController userController = new UserController();
	
	public static void main(String[] args)
	{		
		setPort(4567);
		get("/users", (request, response) -> getAllUsers());

		put("/user/:id", (request, response) -> updateUser(request.params(":id"), request, response));
	  
        post("/create", (request, response) -> createUser(request, response));
    }
	
	 private static String getAllUsers() {
		 
		return userController.getAllUsers();
	}

	private static String createUser(Request request, Response response) {
		    Object[] result =  userController.createUser(request);
		    
		    boolean isCreated = (boolean) result[0];
		    int id = (int) result[1];
		    
			if(isCreated)  return "{\"id\":\""+id+"\"}"; 
			else {
				response.status(409);
				return "{\"Error Message\":\"User ID already in use\"}";			
			}
	}

	private static String updateUser(String id, Request request,Response response){     	
     	int id_query= Integer.parseInt(id);     	
     	
     	Gson gson = new Gson();
 		User user = gson.fromJson(request.body(), User.class);
 		int id_requestBody=user.getID();
 		
     	if(id_requestBody != id_query) {
     		response.status(409);
     		return "Error Message : ID value conflict between id in request body versus id in query parameter";
     	}
     	
     	return userController.updateUser(user, response);     	
     }
}
