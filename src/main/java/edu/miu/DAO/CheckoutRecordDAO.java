package edu.miu.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.miu.Model.BookCopy;
import edu.miu.Model.CheckoutEntry;
import edu.miu.Model.CheckoutRecord;


public class CheckoutRecordDAO {
    private static Map<Integer, CheckoutRecord> checkoutRecords = new HashMap<>();

    public void addCheckoutEntry(int memberId, CheckoutEntry entry) {
        CheckoutRecord record = checkoutRecords.getOrDefault(memberId, new CheckoutRecord());
        record.addCheckoutEntry(entry);
        checkoutRecords.put(memberId, record);
    }

    public CheckoutRecord getCheckoutRecord(int memberId) {
        return checkoutRecords.get(memberId);
    }
    
    public List<BookCopy> getOverdueBooks() {
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
