package edu.miu.Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.miu.DataAccess.DataAccessFacade;
import edu.miu.DataAccess.StorageManager;
import edu.miu.Model.BookCopy;
import edu.miu.Model.CheckoutEntry;
import edu.miu.Model.CheckoutRecord;


public class CheckoutRecordFactory {
    public static void addCheckoutEntry(int memberId, BookCopy bookCopy) {
    	StorageManager manager = new DataAccessFacade();
        Map<Integer, CheckoutRecord> recordsMap = new HashMap<>();
//        		manager.readCheckoutRecordsFromStorage();

        CheckoutEntry entry = new CheckoutEntry(bookCopy);
        CheckoutRecord record = recordsMap.getOrDefault(memberId, new CheckoutRecord());
        record.addCheckoutEntry(entry);
        
        recordsMap.put(memberId, record);
        manager.saveCheckoutRecordsToStorage(recordsMap);
    }
    
    public static CheckoutRecord getCheckoutRecord(int memberId) {
    	StorageManager manager = new DataAccessFacade();
        Map<Integer, CheckoutRecord> recordsMap = manager.readCheckoutRecordsFromStorage();
        return recordsMap.get(memberId);
    }
    
    public static Map<Integer, CheckoutRecord> getAllCheckoutRecords() {
    	StorageManager manager = new DataAccessFacade();
        Map<Integer, CheckoutRecord> recordsMap = manager.readCheckoutRecordsFromStorage();
        if (recordsMap.isEmpty()) {
            throw new IllegalStateException("No checkout records found.");
        }
        return Collections.unmodifiableMap(recordsMap);
    }
    
    public static List<BookCopy> getOverdueBooks() {
        List<BookCopy> overdueBooks = new ArrayList<>();
        Date currentDate = new Date();

        StorageManager manager = new DataAccessFacade();
        Map<Integer, CheckoutRecord> recordsMap = manager.readCheckoutRecordsFromStorage();
        
        recordsMap.values().forEach(record -> {
            record.getCheckoutEntries().forEach(checkoutEntry -> {
                if (checkoutEntry.isOverdue(currentDate)) {
                    overdueBooks.add(checkoutEntry.getBookCopy());
                }
            });
        });

        return overdueBooks;
    }
}
