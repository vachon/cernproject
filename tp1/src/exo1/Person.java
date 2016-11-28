package exo1;

public class Person {
	
	private String firstname;
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Person(String firstname) {
		super();
		this.firstname = firstname;
	}

	@Override
	public String toString() {
		return "Person [firstname=" + firstname + "]";
	}
	
	public static void main(String[] args){
		Person p1 = new Person("nicolas");
		System.out.println(p1);
	}
	
}
