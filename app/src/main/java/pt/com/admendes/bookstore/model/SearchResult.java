package pt.com.admendes.bookstore.model;

import java.util.List;

public class SearchResult {
    private List<Volume> items;

    public SearchResult(List<Volume> items) {
        this.items = items;
    }

    public List<Volume> getItems() {
        return this.items;
    }

    public void setItems(List<Volume> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "items=" + items +
                '}';
    }
}
