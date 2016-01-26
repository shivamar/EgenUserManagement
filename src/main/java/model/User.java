package model;

public class User {
	int id;
	String firstName;
	String middleName;
	String lastName;
	int age;
	String gender;
	long phoneNumber;
	long zipCode;
	
	User(int id, String firstName, String middleName, String lastName,int age, String gender,long phone,long zip)
	{
		this.id = id;
		this.firstName=firstName;
		this.middleName=middleName;
		this.lastName=lastName;
		this.age=age;
		this.gender=gender;
		this.phoneNumber=phone;
		this.zipCode=zip;
	}

	public int getID() {	
		return id;
	}
}
