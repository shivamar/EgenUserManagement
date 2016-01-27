package controllers;

import java.util.List;

import spark.Request;
import spark.Response;
import model.User;

import com.google.gson.Gson;

import dao.UserDAO;

public class UserController {
	private static Gson gson = new Gson();
	private static final UserDAO userDao = new UserDAO();
	
	 public String getAllUsers() {		 
		List<User> li = userDao.getAllUsers();		  
		return gson.toJson(li);
	 }

	public Object[] createUser(Request request) {		
		String reqBod =  request.body();
		User newUser = gson.fromJson(reqBod, User.class);
		boolean isCreated = userDao.createUser(newUser);
		
		return new Object[]{isCreated, newUser.getID()};		
	}
	
	public String updateUser(User user, Response response){
		int id = user.getID();
		
		if(isUserExists(id)){     		     		
     		userDao.updateUser(user);     		
     		return  gson.toJson("{\""+id+"\" : \"Updated\" }"); //"{\""+id+"\" : \"Updated\" }";
     	}        		
     	else{
     		response.status(404);
     		return gson.toJson("{\"result\" : \"User Not Found\"}");
     	}			
	}
	
	public boolean isUserExists(int id){
		if(userDao.isExists(id))
			return true;
		else return false;
	}	
	
	public UserDAO getuserDAO(){
		return userDao;
	}
	
	public void reset(){
		userDao.resetDataStore();
	}
}
