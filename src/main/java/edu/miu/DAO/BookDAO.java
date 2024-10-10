package edu.miu.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.miu.Model.Author;
import edu.miu.Model.Book;
import edu.miu.Model.BookCopy;

public class BookDAO {
    private static List<Book> books = new ArrayList<>();

    public Book findBookByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
    }

    public BookCopy getAvailableCopy(String isbn) {
        Book book = findBookByIsbn(isbn);
        return book.getCopies().stream()
                .filter(BookCopy::isAvailable)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No available copy for the book."));
    }

    public void checkoutBook(String isbn, String memberId) {
        BookCopy availableCopy = getAvailableCopy(isbn);
        availableCopy.setAvailability(false);
        System.out.println("Book copy " + availableCopy.getCopyNumber() + " checked out successfully.");
    }

    public void addBook(String isbn, String title, List<Author> authors, int maxCheckoutLength, int numOfCopies) {
    	new Book(isbn, title, authors, maxCheckoutLength, numOfCopies);
        System.out.println("New book with ISBN " + isbn + " added with " + numOfCopies + " copies.");
    }
    
    public void addBook(String isbn, int numOfCopies) {
    	Optional<Book> bookOptional = getBookByIsbn(isbn);
    	if(bookOptional.isPresent()) {
    		Book book = bookOptional.get();
            for (int i = 0; i < numOfCopies; i++) {
                book.addCopy();
            }
            System.out.println(numOfCopies + " new copies added to book with ISBN: " + isbn);
    	}
    	else {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " not found.");
        }
    }
    
    public Optional<Book> getBookByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }
}
