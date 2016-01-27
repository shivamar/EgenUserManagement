package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.User;

public class UserDAO {			
	HashMap<Integer, User> hmap = new HashMap<>();	 
	 
	 public List<User> getAllUsers() {	
		 List<User> li = new ArrayList<>();
		 
		 for(User user : hmap.values())
		 {
			 li.add(user);
		 }		    
		return li;
	 }
 
	public boolean createUser(User newUser) {		
		User val = hmap.putIfAbsent(newUser.getID(), newUser);
		
		if(val == null)
			return true;
		else return false;
	}

	public boolean isExists(int id) {		
		return hmap.containsKey(id);
	}

	public void updateUser(User user) {
		int id = user.getID();		
		hmap.put(id, user);			
	}

	public void resetDataStore() {
		hmap=new HashMap<>();		
	}	   
}
