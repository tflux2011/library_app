package edu.miu.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book {
    private String isbn;
    private String title;
    private List<Author> authors;
    private int maxCheckoutLength;
    private List<BookCopy> copies;

    public Book(String isbn, String title, int numOfAuthors, List<Author> authors, int maxCheckoutLength) {
    	if (authors == null || authors.isEmpty()) {
    		throw new IllegalArgumentException("A book must have at least one author.");
    	}
    	
        this.isbn = isbn;
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.maxCheckoutLength = maxCheckoutLength; // checkout duration
        this.copies = new ArrayList<>();
    }
    
    public String getIsbn() {
    	return isbn;
    }
    
    public String getTitle() {
    	return title;
    }
    
    public List<Author> getAuthors(){
    	return Collections.unmodifiableList(authors);
    }
    
    public void addAuthor(String firstName, String lastName, String credentials, String bio) {
    	Author author = new Author(firstName, lastName, credentials, bio);
    	this.authors.add(author);
    }

    public void addCopy(BookCopy copy) {
    	if (copy == null) {
            throw new IllegalArgumentException("Cannot add a null book copy.");
        }
        copies.add(copy);
    }

    public boolean isAvailable() {
        for (BookCopy copy : copies) {
            if (copy.isAvailable()) {
                return true;
            }
        }
        return false;
    }
    
    public int getMaxCheckoutLength() {
        return this.maxCheckoutLength;
    }
}

