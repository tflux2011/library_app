package edu.miu.Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.miu.Model.Author;

public class AuthorFactory {
    private static List<Author> authors = new ArrayList<>();

    public static String addAuthor(Author author) {
    	if (author == null) {
            throw new IllegalArgumentException("Author cannot be null.");
        }

        boolean exists = authors.stream()
        		.anyMatch(authorExist ->
        			authorExist.getFirstName().equals(author.getFirstName()) &&
        			authorExist.getLastName().equals(author.getLastName()));

        if (exists) {
            return "Author already exists: " + author.getFirstName() + " " + author.getLastName();
        } else {
            authors.add(author);
            return "Author added successfully: " + author.getFirstName() + " " + author.getLastName();
        }
    }

    public static List<Author> getAllAuthors() {
        return Collections.unmodifiableList(authors);
    }

    public static Author findAuthorByName(String firstName, String lastName) {
    	return authors.stream()
                .filter(author -> author.getFirstName().equals(firstName) &&
                                  author.getLastName().equals(lastName))
                .findFirst().orElse(null);
    }
}
