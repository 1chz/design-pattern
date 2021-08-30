package io.shirohoo.creational.prototype;

import java.util.Objects;

public class UsePrototype {
    public static void main(String[] args) {
        // 책장에는 책이 여러개 꼽혀있을 수 있습니다.
        // 따라서 책장과 책은 일대다 관계를 가지며, 이를 컬렉션으로 표현합니다.
        BookShelf originBookShelf = BookShelf.newInstance();
        originBookShelf.addBook(Book.of("조슈아 블로크", "이펙티브 자바"));
        originBookShelf.addBook(Book.of("토비", "토비의 스프링"));
        originBookShelf.addBook(Book.of("조영호", "오브젝트"));

        // 프로토타입 패턴으로 작성된 메서드를 호출해 깊은 복사를 수행합니다.
        // 객체를 아무리 많이 복사해도 언제나 아래 단 한줄의 코드만 호출하면 됩니다.
        BookShelf clonedBookShelf = originBookShelf.deepClone();

        Book 원본_토비_토비의_스프링 = originBookShelf.getBook(1);

        // 원본의 이름을 '토비의 스프링'에서 '토비의 스프링2'로 바꿉니다.
        원본_토비_토비의_스프링.setTitle("토비의 스프링2");
        Book 원본_토비_토비의_스프링2 = 원본_토비_토비의_스프링;

        // 복제본을 꺼냅니다. 복제본의 이름은 '토비의 스프링'이 맞을까요?
        Book 복제본_토비_토비의_스프링 = clonedBookShelf.getBook(1);

        // 성공적으로 깊은복사가 수행되어 아래의 단정문이 참이 됩니다.
        assert !Objects.equals(원본_토비_토비의_스프링2, 복제본_토비_토비의_스프링);
    }
}
