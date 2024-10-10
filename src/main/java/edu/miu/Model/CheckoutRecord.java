package edu.miu.Model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckoutRecord {
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
