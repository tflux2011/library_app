package edu.miu.Model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckoutRecord implements Serializable {
	private static final long serialVersionUID = 5103504110934376546L;
	private List<CheckoutEntry> entries;

    public CheckoutRecord() {
        this.entries = new ArrayList<>();
    }

    public void addCheckoutEntry(CheckoutEntry entry) {
        entries.add(entry);
    }

    public List<CheckoutEntry> getCheckoutEntries() {
        return Collections.unmodifiableList(entries);
    }
}
