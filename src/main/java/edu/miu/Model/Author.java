package edu.miu.Model;

import java.io.Serializable;

public class Author implements Serializable {
	private static final long serialVersionUID = -3638116717142937164L;
	private String firstName;
    private String lastName;
    private String credentials;
    private String bio;

    public Author(String firstName, String lastName, String credentials, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.credentials = credentials;
        this.bio = bio;
    }

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFullName(){
		return getFirstName() +" "+getLastName();
	}

	public String getCredentials() {
		return credentials;
	}

	public String getBio() {
		return bio;
	}

	@Override
	public String toString() {
		return firstName+" "+lastName;
	}
}
