package exo3;

public abstract class Human {
	public String firstName;
	public String lastName;
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Human(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public abstract String work();

	@Override
	public String toString() {
		return "Human [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
