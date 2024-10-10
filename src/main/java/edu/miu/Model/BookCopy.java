package edu.miu.Model;

public class BookCopy {
    private static int idCounter = 0;
    private String copyNumber;
    private boolean isAvailable;
    private Book book;

    public BookCopy(Book book) {
        this.book = book;
        this.isAvailable = true;
        this.copyNumber = "Loto" + (++idCounter);
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
