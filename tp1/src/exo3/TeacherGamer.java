package exo3;

public class TeacherGamer extends Teacher implements Gamer {

	public TeacherGamer(String firstName, String lastName) {
		super(firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String play() {
		// TODO Auto-generated method stub
		return "Prof OP";
	}

}
