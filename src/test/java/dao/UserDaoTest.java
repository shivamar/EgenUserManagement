package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {	
	List<User> list;
	
	@Before
	public void setUp() throws Exception {
		list = new ArrayList<>();
	}

	@After
	public void tearDown() throws Exception {		
		list = null;
	}

	@Test
	public void testdaogetAll() {
		UserDAO dao = new UserDAO();
		User expectedUser = new User(1, "Jack", "", "Brown", 45, "Male", 12234444, 98989);		
		dao.createUser(expectedUser);
		list.add(expectedUser);
		
		List<User> result = dao.getAllUsers();
		
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(list,result);
	}

}
