package edu.miu.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibraryMember implements Serializable {
	private static final long serialVersionUID = -2549060898016489221L;
	private int memberId;
    private String firstName;
    private String lastName;
    private Address address;
    private String phone;
    private CheckoutRecord checkoutRecord;

    public LibraryMember(int memberId, String firstName, String lastName, Address address, String phone) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.checkoutRecord = new CheckoutRecord();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
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
    
    public int getMemberId() {
    	return memberId;
    }
}
