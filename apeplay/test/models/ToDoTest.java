package models;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ToDoTest {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}
	
	@Test
	public void findTodosByUserEMailtest() {
		User testUser = new User("test@playwell.org", "Alexander", "Pester", "secure");
		testUser.save();
		
		ToDo todo = new ToDo();
		todo.description = "Play lernen";
		todo.assignedUser = testUser;
		todo.done = true;
		todo.save();
		
		todo = new ToDo();
		todo.description = "Play programmieren";
		todo.assignedUser = testUser;
		todo.done = false;
		todo.save();
		
		List<ToDo> resultList = ToDo.findTodosByUserEMail(testUser.email);
		assertEquals(1, resultList.size());
		assertEquals("Play programmieren", resultList.get(0).description);
	}

}
