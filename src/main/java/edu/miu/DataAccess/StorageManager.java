package edu.miu.DataAccess;

import java.util.Map;

import edu.miu.Model.Author;
import edu.miu.Model.Book;
import edu.miu.Model.CheckoutRecord;
import edu.miu.Model.LibraryMember;

public interface StorageManager {
	public Map<String, Author> readAuthorsFromStorage();
	public void saveAuthorsToStorage(Map<String, Author> authors);
	public Map<String, Book> readBooksFromStorage();
	public void saveBooksToStorage(Map<String, Book> books);
	public Map<Integer, CheckoutRecord> readCheckoutRecordsFromStorage();
	public void saveCheckoutRecordsToStorage(Map<Integer, CheckoutRecord> checkoutRecords);
	public Map<Integer, LibraryMember> readMembersFromStorage();
	public void saveMembersToStorage(Map<Integer, LibraryMember> members);
}
