package edu.miu.Business;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.miu.DataAccess.DataAccessFacade;
import edu.miu.DataAccess.StorageManager;
import edu.miu.Model.Author;

public class AuthorFactory {
    public static void addAuthor(Author author) {
    	if (author == null) {
            throw new IllegalArgumentException("Author cannot be null.");
        }
    	
    	StorageManager manager = new DataAccessFacade();
    	Map<String, Author> authorMap = manager.readAuthorsFromStorage();
        
        String uniqueKey = author.getFirstName() + " " + author.getLastName();
        
        boolean exists = authorMap.keySet().stream()
                .anyMatch(key -> key.equals(uniqueKey));

        if (exists) {
            System.out.println("Author already exists: " + uniqueKey);
        } else {
            authorMap.put(uniqueKey, author);
            manager.saveAuthorsToStorage(authorMap);
            System.out.println("Author added successfully: " + uniqueKey);
        }
    }

    public static List<Author> getAllAuthors() {
    	StorageManager manager = new DataAccessFacade();
    	Map<String, Author> authorMap = manager.readAuthorsFromStorage();
    	 
         return authorMap.values().stream()
                 .collect(Collectors.toUnmodifiableList());
    }

    public static Author findAuthorByName(String firstName, String lastName) {
    	StorageManager manager = new DataAccessFacade();
    	Map<String, Author> authorMap = manager.readAuthorsFromStorage();
        String uniqueKey = firstName + " " + lastName;
        
        return authorMap.entrySet().stream()
                .filter(entry -> entry.getKey().equals(uniqueKey))
                .map(Map.Entry::getValue)
                .findFirst().orElse(null);
    }
}
