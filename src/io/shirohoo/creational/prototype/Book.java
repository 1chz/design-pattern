package io.shirohoo.creational.prototype;

import java.util.Objects;

public class Book {
    private String author;
    private String title;

    private Book(final String author, final String title) {
        this.author = author;
        this.title = title;
    }

    public static Book of(final String author, final String title) {
        return new Book(author, title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    // 프로토타입 패턴
    public Book deepClone() {
        return Book.of(this.author, this.title);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        final Book book = (Book) o;
        return Objects.equals(author, book.author) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title);
    }
}
