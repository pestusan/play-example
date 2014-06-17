package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}
	
	@Test
	public void testAuthenticatePositiv() {
		new User("test@playwell.org", "Alexander", "Pester", "positiv").save();
	
		User user = User.authenticate("test@playwell.org", "positiv");
		assertNotNull(user);
		assertEquals("Alexander", user.firstName);
		assertEquals("Pester", user.lastName);
	}
	
	@Test
	public void testAuthenticateNegativ() {
		new User("test@playwell.org", "Alexander", "Pester", "negativ").save();
		User user = User.authenticate("test@playwell.org", "positiv");
		assertNull(user);
	}

}
