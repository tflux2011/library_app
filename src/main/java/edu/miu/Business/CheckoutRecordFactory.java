package edu.miu.Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.miu.Model.BookCopy;
import edu.miu.Model.CheckoutEntry;
import edu.miu.Model.CheckoutRecord;


public class CheckoutRecordFactory {
    private static Map<Integer, CheckoutRecord> checkoutRecords = new HashMap<>();

    public static void addCheckoutEntry(int memberId, CheckoutEntry entry) {
        CheckoutRecord record = checkoutRecords.getOrDefault(memberId, new CheckoutRecord());
        record.addCheckoutEntry(entry);
        checkoutRecords.put(memberId, record);
    }

    public static CheckoutRecord getCheckoutRecord(int memberId) {
        return checkoutRecords.get(memberId);
    }
    
    public static Map<Integer, CheckoutRecord> getAllCheckoutRecord(){
    	return Collections.unmodifiableMap(checkoutRecords);
    }
    
    public static List<BookCopy> getOverdueBooks() {
        List<BookCopy> overdueBooks = new ArrayList<>();
        Date currentDate = new Date();

        for (Map.Entry<Integer, CheckoutRecord> entry : checkoutRecords.entrySet()) {
            CheckoutRecord record = entry.getValue();

            for (CheckoutEntry checkoutEntry : record.getCheckoutEntries()) {
                if (checkoutEntry.isOverdue(currentDate)) {
                    overdueBooks.add(checkoutEntry.getBookCopy());
                }
            }
        }

        return overdueBooks;
    }
}
