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

    public LibraryMember(int memberId, String firstName, String lastName, String phone, String street, String city, String state, String zip) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = new Address(street, city, state, zip);
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
