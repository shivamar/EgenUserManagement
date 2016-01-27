package controllers;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.List;

import model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.easymock.*;

import dao.UserDAO;
import spark.Request;
import spark.Response;

public class UserControllerTest {
	static UserController userCon;
	@Before
	public void setUp(){
		userCon = new UserController();	 
	}
	
	@After
	public void tearDown(){
		userCon = null;	    
	}
	
	@Test
	public void testCreateUser(){		
	    Request request = EasyMock.createMock(Request.class);
	    Object[] expectedResult = new Object[] {true, 1};
	    
	    expect(request.body()).andReturn("{\"id\":1,\"firstName\":\"io\",\"middleName\":\"ram\",\"lastName\":\"ram\",\"age\":411,\"gender\":\"male\",\"phoneNumber\":8111,\"zipCode\":95035}").anyTimes();
	    
	    replay(request);

	    userCon.reset();
	    Object[] testResult = userCon.createUser(request);	    
	    	    
	    verify(request);	    
	    assertArrayEquals(testResult, expectedResult);	    
	}
		
	@Test
	public void testUpdateUser(){
		Request request = EasyMock.createMock(Request.class);		    		 
	    expect(request.body()).andReturn("{\"id\":1,\"firstName\":\"io\",\"middleName\":\"ram\",\"lastName\":\"ram\",\"age\":411,\"gender\":\"male\",\"phoneNumber\":8111,\"zipCode\":95035}").anyTimes();
	    
	    replay(request);
	         
	    userCon.createUser(request);	    
	    	    
	    verify(request);	    
	    
	    UserDAO userDAO = userCon.getuserDAO();
	    
	    List<User> user_list = userDAO.getAllUsers();		    
	    User user = user_list.get(0);
	    
	    user.setPhone(71636157);
	    
	    Response response = EasyMock.createMock(Response.class);
	    
	    userCon.updateUser(user, response);
	    
	    User updatedUser = (userDAO.getAllUsers()).get(0);
	    
	    assertSame(user, updatedUser);
	}


}
