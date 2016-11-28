package exo1;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PersonTest {

	@Test
	public void bavard() {
		assertTrue("G23 est bavard",1==1);
	}
	
	@Test
	public void test() {
		Person p = new Person("luc");
		assertTrue("firstname not null",p.getFirstname() != null);
	}
	

}
