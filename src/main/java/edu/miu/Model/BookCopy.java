package edu.miu.Model;

public class BookCopy {
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
