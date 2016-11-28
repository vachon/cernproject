package exo3;

import static org.junit.Assert.*;

import org.junit.Test;

import exo1.Person;

public class playTest {

	@Test
	public void studentTest() {
		StudentGamer s = new StudentGamer("Nicolas","Vachon");
		assertTrue("probleme sur StudentGamer play()",s.play()== "rage");
	}

	@Test
	public void teacherTest() {
		TeacherGamer t = new TeacherGamer("Luc","Damas");
		assertTrue("probleme sur TeacherGamer play()",t.play()== "Prof OP");
	}
}
