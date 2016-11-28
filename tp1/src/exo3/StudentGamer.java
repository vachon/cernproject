package exo3;

public class StudentGamer extends Student implements Gamer {

	public StudentGamer(String firstName, String lastName) {
		super(firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String play() {
		// TODO Auto-generated method stub
		return "rage";
	}

}
