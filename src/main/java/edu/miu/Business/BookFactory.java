package edu.miu.Business;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.miu.DataAccess.DataAccessFacade;
import edu.miu.DataAccess.StorageManager;
import edu.miu.Model.Author;
import edu.miu.Model.Book;
import edu.miu.Model.BookCopy;

public class BookFactory {    
    public static Book findBookByIsbn(String isbn) {
    	StorageManager manager = new DataAccessFacade();
        Map<String, Book> booksMap = manager.readBooksFromStorage();
        return booksMap.values().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ISBN: " + isbn));
    }

    private static BookCopy getAvailableCopy(Book book) {
        return book.getCopies().stream()
                .filter(BookCopy::isAvailable)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No available copy for the book with ISBN: " + book.getIsbn()));
    }

    public static BookCopy checkoutBook(String isbn) {
    	try {
            Book book = findBookByIsbn(isbn);
    		BookCopy availableCopy = getAvailableCopy(book);
            availableCopy.setAvailability(false);
            book.updateCopyAvailability(availableCopy);

            StorageManager manager = new DataAccessFacade();
            Map<String, Book> booksMap = manager.readBooksFromStorage();
            booksMap.put(isbn, availableCopy.getBook());
            manager.saveBooksToStorage(booksMap);
            return availableCopy;
    	}
    	catch(IllegalArgumentException ex) {
    		return null;
    	}
    }

    public static String addBook(String isbn, String title, List<Author> authors, int maxCheckoutLength, int numOfCopies) {
    	StorageManager manager = new DataAccessFacade();
    	Map<String, Book> booksMap = manager.readBooksFromStorage();
        
        if (booksMap.containsKey(isbn)) {
        	addBookCopies(isbn, numOfCopies);
        	return "Book with ISBN " + isbn + " has been updated with " + numOfCopies + " copies.";
        }

        Book newBook = new Book(isbn, title, authors, maxCheckoutLength, numOfCopies);
        booksMap.put(isbn, newBook);
        manager.saveBooksToStorage(booksMap);

        return "New book with ISBN " + isbn + " added with " + numOfCopies + " copies.";
    }
    
    private static void addBookCopies(String isbn, int numOfCopies) {
    	StorageManager manager = new DataAccessFacade();
        Map<String, Book> booksMap = manager.readBooksFromStorage();
        
        Book book = booksMap.get(isbn);
        if (book != null) {
            for (int i = 0; i < numOfCopies; i++) {
                book.addCopy();
            }
            booksMap.put(isbn, book);
            manager.saveBooksToStorage(booksMap);
            System.out.println(numOfCopies + " new copies added to book with ISBN: " + isbn);
        } else {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " not found.");
        }
    }
    
    public static Optional<Book> getBookByIsbn(String isbn) {
    	StorageManager manager = new DataAccessFacade();
        Map<String, Book> booksMap = manager.readBooksFromStorage();
        return booksMap.values().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }
    
    public static List<Book> getAllBooks() {
    	StorageManager manager = new DataAccessFacade();
        Map<String, Book> booksMap = manager.readBooksFromStorage();
        if (booksMap.isEmpty()) {
            throw new IllegalStateException("No books found in the library.");
        }
        
        return booksMap.values().stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
