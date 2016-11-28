package exo3;

import static org.junit.Assert.*;

import org.junit.Test;

public class WorkTest {

	@Test
	public void workTeacherTest() {
		Teacher t = new Teacher("Luc","Damas");
		assertTrue("erreur work Teacher", t.work() == "Corrige des copies");
	}
	
	@Test
	public void workStudentTest() {
		Student s = new Student("Nicolas","Vachon");
		assertTrue("erreur work Student", s.work() == "Dormir au fond de l'amphi");
	}

}
