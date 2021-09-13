# 📜 프로토타입 패턴(Prototype Pattern)

<br />

`생성 패턴` 중 하나인 프로토타입 패턴은 객체를 새로 생성하는 코드가 아주 복잡한 경우에 활용할 수 있는 패턴입니다.

객체를 복사해야 하는 경우 주소참조를 하는 `얕은 복사`와 값 자체를 참조하는 `깊은 복사` 두 가지 유형이 존재합니다.

객체를 복사했는데 얕은 복사가 되는 경우 원본의 데이터가 변경되면 모든 복사본의 데이터도 함께 변경되는 사이드 이펙트가 존재합니다.

따라서 객체를 복사할 때는 깊은 복사를 해야 할지를 잘 판단해야 합니다.

단순 객체를 깊은 복사해야 할 경우 생성자를 한번 호출하면 끝날수도 있지만, `컬렉션`을 깊은 복사해야 할 경우 이 코드가 매우 복잡하고 장황해지는 경우가 많습니다.

이때 자바에서 간혹 사용하게 되는 프로토타입 패턴은 대표적으로 ***마커 인터페이스***인 `Cloneable`을 선언해 사용하는 `Object.clone()`이 있습니다.

<br />

> 🤔 마커 인터페이스 (Marker Interface) ?
>
> 내부적으로 아무것도 선언돼있지 않은 인터페이스를 말합니다.
> 
> 컴파일러에게 해당 클래스가 특정한 속성을 가졌음을 알려주며, 대표적으로 `Serializable`, `Cloneable`등이 존재합니다.
> 
> 예를 들어 클래스에 `Serializable`가 `implements`돼 있다면 해당 클래스는 직렬화 할 수 있음을 의미합니다.

<br />

---

<br />

# ⚙ 프로토타입 패턴을 사용하지 않은 경우

<br />

```java
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
```

<br />

# ⚙ 프로토타입 패턴을 사용한 경우

<br />

우선 `Book`과 `BookShelf`에 프로토타입 패턴을 구현합니다.

<br />

```java
public class Book {
    ...

    // 프로토타입 패턴
    public Book deepClone() {
        return Book.of(this.author, this.title);
    }

    ...
}

public class BookShelf {
    ...

    // 프로토타입 패턴
    public BookShelf deepClone() {
        return new BookShelf(shelf.stream()
                .map(Book::deepClone)
                .collect(Collectors.toUnmodifiableList())
        );
    }
    
    ...
}
```

<br />

이제 이 프로토타입 패턴을 사용해 봅시다.

<br />

```java
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
```

<br />

미리 작성해둔 프로토타입 메서드로 귀찮은 깊은 복사를 한번의 메서드 호출을 통해 해결할 수 있게 됐습니다.

<br />
