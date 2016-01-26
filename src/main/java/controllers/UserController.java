package controllers;

import java.util.List;

import spark.Request;
import model.User;

import com.google.gson.Gson;

import dao.UserDAO;

public class UserController {
	private static Gson gson = new Gson();
	private static final UserDAO userDao = new UserDAO();
	
	 public String getAllUsers() 
	 {		 
		List<User> li = userDao.getAllUsers();		  
		return gson.toJson(li);
	 }

	public Object[] createUser(Request request) 
	{		
		String reqBod =  request.body();
		User newUser = gson.fromJson(reqBod, User.class);
		boolean isCreated = userDao.createUser(newUser);
		
		return new Object[]{isCreated, newUser.getID()};		
	}
	
	public void updateUser(User user)
	{
		userDao.updateUser(user);
	}
	
	public boolean isUserExists(int id)
	{
		if(userDao.isExists(id))
			return true;
		else return false;
	}	
}
