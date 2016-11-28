package exo3;
import java.util.ArrayList;
import java.util.List;

public class Lan {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Gamer> someGamer = new ArrayList<Gamer>();
		
		StudentGamer nicolas = new StudentGamer("Nicolas","Vachon");
		StudentGamer romain = new StudentGamer("Romain","Vachon");
		StudentGamer louis = new StudentGamer("Louis","Vachon");
		StudentGamer tim = new StudentGamer("Tim","Vachon");
		TeacherGamer luc = new TeacherGamer("Luc","Damas");
		
		someGamer.add(nicolas);
		someGamer.add(romain);
		someGamer.add(louis);
		someGamer.add(tim);
		someGamer.add(luc);
		
		for (Gamer g : someGamer) {
			System.out.println(g);
			System.out.println(g.play());
			System.out.println(((Human) g).work());
		}
	}

}
