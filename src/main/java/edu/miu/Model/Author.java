package edu.miu.Model;

public class Author {
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

	public String getCredentials() {
		return credentials;
	}

	public String getBio() {
		return bio;
	}
}
