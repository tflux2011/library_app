package edu.miu.Model;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private String memberId;
    private String firstName;
    private String lastName;
    private Address address;
    private String phone;
    private CheckoutRecord checkoutRecord;

    public LibraryMember(String memberId, String firstName, String lastName, Address address, String phone) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.checkoutRecord = new CheckoutRecord();
    }

    public void checkoutBook(BookCopy copy) {
        if (copy.isAvailable()) {
            copy.setAvailability(false);
            CheckoutEntry entry = new CheckoutEntry(copy);
            checkoutRecord.addCheckoutEntry(entry);
        }
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }
}
