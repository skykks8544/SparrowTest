/* EXP01-J. Never dereference null pointers */
/* NULL_RETURN */
package cert;

public class EXP01_J {

	public void NDComplex() {
		String firstName = getName(0);
		String lastName = getName(2);
		
		Person person = new Person(firstName, lastName);
		
		System.out.println(person.getLastName().toUpperCase()); //@violation NULL_RETURN 
	}
	
	public void NDComplexSafe() {
		String firstName = getName(0);
		String lastName = getName(1);
		
		Person person = new Person(firstName, lastName);
		
		System.out.println(person.getLastName().toUpperCase());  /* SAFE */
	}
	
	public String getName(int type) {
		if (type == 0) return "John";
		else if (type == 1) return "Doe";
		else return null;
	}
}

class Person {
	
	private String firstName;
	private String lastName;
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

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
}
