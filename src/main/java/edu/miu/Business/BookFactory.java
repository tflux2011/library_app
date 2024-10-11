package edu.miu.Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import edu.miu.Model.Author;
import edu.miu.Model.Book;
import edu.miu.Model.BookCopy;

public class BookFactory {
    private static List<Book> books = new ArrayList<>();

    public static Book findBookByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
    }

    public static BookCopy getAvailableCopy(String isbn) {
        Book book = findBookByIsbn(isbn);
        return book.getCopies().stream()
                .filter(BookCopy::isAvailable)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No available copy for the book."));
    }

    public static String checkoutBook(String isbn, String memberId) {
        BookCopy availableCopy = getAvailableCopy(isbn);
        availableCopy.setAvailability(false);
        return "Book copy " + availableCopy.getCopyNumber() + " checked out successfully.";
    }

    public static String addBook(String isbn, String title, List<Author> authors, int maxCheckoutLength, int numOfCopies) {
    	Book book = new Book(isbn, title, authors, maxCheckoutLength, numOfCopies);
        books.add(book);
    	return "New book with ISBN " + isbn + " added with " + numOfCopies + " copies.";
    }
    
    public static void addBook(String isbn, int numOfCopies) {
    	Optional<Book> bookOptional = getBookByIsbn(isbn);
    	if(bookOptional.isPresent()) {
    		Book book = bookOptional.get();
            for (int i = 0; i < numOfCopies; i++) {
                book.addCopy();
            }
            
            books.add(book);
            System.out.println(numOfCopies + " new copies added to book with ISBN: " + isbn);
    	}
    	else {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " not found.");
        }
    }
    
    public static Optional<Book> getBookByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }
    
    public static List<Book> getAllBooks() {
//        if (books.isEmpty()) {
//            throw new IllegalStateException("No books found in the library.");
//        }
        
        return Collections.unmodifiableList(books);
    }
}
