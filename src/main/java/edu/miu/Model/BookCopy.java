package edu.miu.Model;

import java.io.Serializable;

public class BookCopy implements Serializable {
	private static final long serialVersionUID = 4176092367401293351L;
	private String copyNumber;
    private boolean isAvailable;
    private Book book;

    public BookCopy(Book book) {
        this.book = book;
        this.isAvailable = true;
        int newCopyNumber = this.book.getCopies().size() + 1;
        this.copyNumber = "Loto" + newCopyNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean available) {
        this.isAvailable = available;
    }

    public Book getBook() {
        return book;
    }

	public String getCopyNumber() {
		return copyNumber;
	}
}
