package edu.miu.Model;

import java.io.Serializable;
import java.util.Date;

public class CheckoutEntry implements Serializable {
	private static final long serialVersionUID = 7036780757941959421L;
	private BookCopy bookCopy;
    private Date checkoutDate;
    private Date dueDate;

    public CheckoutEntry(BookCopy bookCopy) {
    	 if (bookCopy == null) {
             throw new IllegalArgumentException("BookCopy cannot be null.");
         }
         
        this.bookCopy = bookCopy;
        this.checkoutDate = new Date();
        this.dueDate = calculateDueDate(bookCopy.getBook().getMaxCheckoutLength());
    }

    public boolean isOverdue(Date currentDate) {
        return dueDate.before(currentDate);
    }

    public Date getDueDate() {
        return dueDate;
    }

    public BookCopy getBookCopy() {
    	return bookCopy;
    }

    private Date calculateDueDate(int maxCheckoutLength) {
    	 if (maxCheckoutLength <= 0) {
             throw new IllegalArgumentException("Max checkout length must be greater than 0.");
         }
    	 
        long ONE_DAY_MILLIS = 24 * 60 * 60 * 1000;
        return new Date(checkoutDate.getTime() + (maxCheckoutLength * ONE_DAY_MILLIS));
    }
}

