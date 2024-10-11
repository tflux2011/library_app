package edu.miu.DataAccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import edu.miu.Model.Author;
import edu.miu.Model.Book;
import edu.miu.Model.CheckoutRecord;
import edu.miu.Model.LibraryMember;


public class DataAccessFacade implements StorageManager {
	enum StorageType {
		AUTHORS, BOOKS, CHECKOUTRECORDS, MEMBERS;
	}
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ "\\src\\main\\java\\edu\\miu\\DataAccess\\storage";
	
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
    @SuppressWarnings("unchecked")
    public Map<String, Author> readAuthorsFromStorage() {
        Object authors = readFromStorage(StorageType.AUTHORS);
        if (authors != null) {
            return (Map<String, Author>) authors;
        }
        
        return new HashMap<>();
    }
    
    public void saveAuthorsToStorage(Map<String, Author> authors) {
        saveToStorage(StorageType.AUTHORS, authors);
    }
    
    @SuppressWarnings("unchecked")
    public Map<String, Book> readBooksFromStorage() {
        Object books = readFromStorage(StorageType.BOOKS);
        if (books != null) {
            return (Map<String, Book>) books;
        }
        return new HashMap<>();
    }

    public void saveBooksToStorage(Map<String, Book> books) {
        saveToStorage(StorageType.BOOKS, books);
    }
    
	@SuppressWarnings("unchecked")
    public Map<Integer, CheckoutRecord> readCheckoutRecordsFromStorage() {
        Object records = readFromStorage(StorageType.CHECKOUTRECORDS);
        if (records != null) {
            return (Map<Integer, CheckoutRecord>) records;
        }
        return new HashMap<>();
    }

    public void saveCheckoutRecordsToStorage(Map<Integer, CheckoutRecord> checkoutRecords) {
        saveToStorage(StorageType.CHECKOUTRECORDS, checkoutRecords);
    }
    
    @SuppressWarnings("unchecked")
    public Map<Integer, LibraryMember> readMembersFromStorage() {
        Object members = DataAccessFacade.readFromStorage(StorageType.MEMBERS);
        if (members != null) {
            return (Map<Integer, LibraryMember>) members;
        }
        return new HashMap<>();
    }

    public void saveMembersToStorage(Map<Integer, LibraryMember> members) {
        DataAccessFacade.saveToStorage(StorageType.MEMBERS, members);
    }
	
	public static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
	public static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
	
	final static class Pair<S,T> implements Serializable{
		private static final long serialVersionUID = 7398907105783586898L;
		S first;
		T second;
		Pair(S s, T t) {
			first = s;
			second = t;
		}
		@Override 
		public boolean equals(Object ob) {
			if(ob == null) return false;
			if(this == ob) return true;
			if(ob.getClass() != getClass()) return false;
			@SuppressWarnings("unchecked")
			Pair<S,T> p = (Pair<S,T>)ob;
			return p.first.equals(first) && p.second.equals(second);
		}
		
		@Override 
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}
		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}
	}
}
