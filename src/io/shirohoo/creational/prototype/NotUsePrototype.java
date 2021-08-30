package io.shirohoo.creational.prototype;

import java.util.Objects;

public class NotUsePrototype {
    public static void main(String[] args) {
        // 책장에는 책이 여러개 꼽혀있을 수 있습니다.
        // 따라서 책장과 책은 일대다 관계를 가지며, 이를 컬렉션으로 표현합니다.
        BookShelf originBookShelf = BookShelf.newInstance();
        originBookShelf.addBook(Book.of("조슈아 블로크", "이펙티브 자바"));
        originBookShelf.addBook(Book.of("토비", "토비의 스프링"));
        originBookShelf.addBook(Book.of("조영호", "오브젝트"));

        // 객체를 복사합니다. 원본 책장의 책을 하나씩 꺼내서 새로운 책장에 집어넣습니다.
        // 하지만 이 때 얕은 복사가 수행 됩니다.
        // 약간의 변경을 가하면 깊은 복사를 할 수 있지만 복사를 수행 때마다 아래같이 장황한 코드를 매번 작성해야 합니다.
        BookShelf clonedBookShelf = BookShelf.newInstance();
        for (int i = 0; i < originBookShelf.size(); i++) {
            clonedBookShelf.addBook(originBookShelf.getBook(i));
        }

        Book 원본_조영호_오브젝트 = originBookShelf.getBook(2);
        원본_조영호_오브젝트.setTitle("객체지향의 사실과 오해");

        // 원본의 책 이름을 '오브젝트' 에서 '객체지향의 사실과 오해'로 바꿉니다.
        Book 원본_조영호_객체지향의_사실과_오해 = 원본_조영호_오브젝트;

        // 복제되있는 책을 꺼냅니다. 이 책의 이름이 '오브젝트'일까요?
        Book 복제본_조영호_오브젝트 = clonedBookShelf.getBook(2);

        // 원본의 이름이 변경되며 이로 인한 사이드 이펙트가 복제본에 영향을 끼쳤습니다.
        // 따라서 아래의 단정문은 부자연스럽지만 참이 됩니다.
        assert Objects.equals(원본_조영호_객체지향의_사실과_오해, 복제본_조영호_오브젝트);
    }
}
