package edu.miu.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book implements Serializable {
	private static final long serialVersionUID = 2875085467925966317L;
	private String isbn;
    private String title;
    private List<Author> authors;
    private int maxCheckoutLength;
    private List<BookCopy> copies;

    public Book(String isbn, String title, List<Author> authors, int maxCheckoutLength, int numOfCopies) {
    	if (authors == null || authors.isEmpty()) {
    		throw new IllegalArgumentException("A book must have at least one author.");
    	}
    	
        this.isbn = isbn;
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.maxCheckoutLength = maxCheckoutLength;
        this.copies = new ArrayList<>();
        for (int i = 0; i < numOfCopies; i++) {
        	BookCopy copy = new BookCopy(this);
            copies.add(copy);
        }
    }
    
    public String getIsbn() {
    	return isbn;
    }
    
    public List<BookCopy> getCopies(){
    	return Collections.unmodifiableList(copies);
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

    public void addCopy() {
    	BookCopy copy = new BookCopy(this);
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

