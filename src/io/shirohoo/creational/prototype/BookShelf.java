package io.shirohoo.creational.prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookShelf {
    private final List<Book> shelf;

    private BookShelf() {
        this.shelf = new ArrayList<>();
    }

    private BookShelf(final List<Book> shelf) {
        this.shelf = shelf;
    }

    public static BookShelf newInstance() {
        return new BookShelf();
    }

    public boolean addBook(final Book book) {
        return shelf.add(book);
    }

    public Book getBook(final int index) {
        return shelf.get(index);
    }

    public int size() {
        return shelf.size();
    }

    // 프로토타입 패턴
    public BookShelf deepClone() {
        return new BookShelf(shelf.stream()
                .map(Book::deepClone)
                .collect(Collectors.toUnmodifiableList())
        );
    }
}
