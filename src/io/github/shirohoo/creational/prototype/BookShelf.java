package io.github.shirohoo.creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class BookShelf {
    private final List<Book> shelf;

    private BookShelf() {
        this.shelf = new ArrayList<>();
    }

    private BookShelf(List<Book> shelf) {
        this.shelf = shelf;
    }

    public static BookShelf newInstance() {
        return new BookShelf();
    }

    public boolean addBook(Book book) {
        return shelf.add(book);
    }

    public Book getBook(int index) {
        return shelf.get(index);
    }

    public int size() {
        return shelf.size();
    }

    // 프로토타입 패턴
    public BookShelf deepClone() {
        return new BookShelf(shelf.stream().map(Book::deepClone).toList());
    }
}
