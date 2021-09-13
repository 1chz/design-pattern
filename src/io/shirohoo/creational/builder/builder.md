# 📜 빌더 패턴(Builder Pattern)

<br />

`생성 패턴`중 하나인 `빌더 패턴`은 대부분의 자바 개발자 분들이 `롬복(Lombok)`을 통해 아주 많이 사용하고 있는 패턴입니다.

이미 롬복을 통해 아주 쉽게 사용하고, 여러가지 옵션도 줄 수 있는 패턴이라 사실 직접 구현할 일이 많지는 않을 것 같습니다.

[이펙티브 자바](http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&barcode=9788966262281) 에서는 이 빌더 패턴에 대해 점층적 생성자 패턴을 사용하는 것보다 효율적일 것이라고 설명합니다.

대체로 생성자 매개변수가 2~3개 이하라면 정적 팩토리 메서드를 통해 객체 생성을 권장하며, 그 이상일 경우 많은 매개변수를 묶어주는 도우미 클래스를 생성하거나, 이 빌더 패턴의 사용을 고려하라고 하고 있습니다.

<br />

제 경우엔 생성자 매개변수가 3~4개가 넘어간다면 대체로 빌더 패턴을 사용하는 것 같습니다. 그 이하라면 정적 팩토리 메서드를 이용합니다.

<br >

빌더 패턴의 장점으로는 객체 생성 전까지 객체의 상태가 일관성을 갖는다는 것입니다.

단점으로는 흔히 [보일러 플레이트 코드](https://ko.wikipedia.org/wiki/%EC%83%81%EC%9A%A9%EA%B5%AC_%EC%BD%94%EB%93%9C) 라고 불리는 것이 대량으로 생긴다는 점입니다.

하지만 자바에서는 이 귀찮은 작업을 롬복이 대신 처리 해 줍니다. 

만약 빌더 패턴을 사용할 때마다 직접 코드를 작성해야 한다면, 정말 귀찮고 지루한 작업이 되지 않을까 싶네요.

<br />

빌더 패턴이 활용되기 전까지 객체 생성은 다음과 같은 방법들로 이루어 졌습니다.

<br />

```java
public Member createMember(){
    Member member = new Member();
    member.setName("shirohoo");
    member.setAge(1);
    member.set...;
    member.set...;
    member.set...;
    member.set...;
    return member;
}
```

<br />

위 방법은 아직도 아주 많이 사용되고 있는 방법이지만, 치명적인 단점이 두가지 있습니다.

첫째로, 모든 수정자의 호출이 끝날때까지 객체의 상태가 매우 불안정하다는 것입니다.

만약 수정자가 호출되던 와중 예외가 발생한다면 객체가 완벽한 상태로 초기화되지 못한채로 와르르 무너지게 됩니다.

둘째로, 모든 클래스 변수에 대해 수정자(Setter)를 생성해야 합니다.

이럴 경우 객체의 캡슐화가 깨지기 때문에 객체지향 프로그래밍에서 권장하는 방법은 아닙니다.

다음은 생성자를 사용한 방법입니다.

<br />

```java
public Member createMember(){
    return new Member("f", "m", "l", 5, "ko");
}
```

<br />

이 방법도 여전히 단점이 있습니다.

일단 매개변수가 많아질 경우 개발자가 모든 매개변수의 타입과 순서를 정확히 지키기 힘들어지기 때문에 문제가 발생 할 가능성이 매우 높아집니다.

이 경우 특히 같은 타입의 매개변수가 연속적으로 입력되야 할 경우 순서가 더더욱 중요해지는데, 개발자가 헷갈릴 가능성이 더욱 높아집니다.

<br />

또한 해당 생성자가 어떤 시점에 호출되어야 할 지 개발자가 API문서를 보지 않고는 알 수 없습니다.

생성자의 고질적인 단점인데, 생성자는 `new` 키워드를 통해 사용되기 때문에 `이름`을 가질 수 없습니다.

<br />

위의 단점들 중 일부는 IDE의 도움을 받는다면 어느정도 극복할 수 있기는 합니다.

하지만 IDE를 사용하지 않는 개발자라면 바로 API 문서를 찾으러 달려가야 할지도 모릅니다. ~~(IDE를 쓰지않는 개발자가 있긴할까요? 🤔)~~

<br />

```java
public static Member whenRegistering(final String firstName, final String middleName, final String lastName, final int age, final String country) {
    return new Member(firstName, middleName, lastName, age, country);  
}
```       

<br />
        
```java
Member.whenRegistering("f", "m", "l", 5, "ko");
```

<br />

위의 방법은 정적 팩토리 메서드를 활용한 방법입니다.

생성자의 이름을 가질 수 없다는 단점을 명확하게 해결 해 줍니다.

하지만 여전히 인수가 많을 경우 생성자와 같은 단점을 공유합니다.

<br />

```java
public static void main(String[] args) {
    Member member = Member.builder()
            .firstName("f")
            .middleName("m")
            .lastName("l")
            .age(1)
            .country("ko")
            .build();
}
```

<br />

빌더 패턴을 사용한 모습입니다.

객체의 상태를 한눈에 파악 할 수 있습니다.

또한 `build()`가 호출되기 전 까지 `Member`는 생성되지 않은 상태이며, `build()`가 호출되는 순간 `Member`의 상태가 결정되며 인스턴스가 생성되므로, 상태일관성이 유지됩니다.

다음은 빌더 패턴을 구현한 코드입니다.

<br />

```java
public class Member {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final int age;
    private final String country;

    private Member(final String firstName, final String middleName, final String lastName, final int age, final String country) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
    }

    public static MemberBuilder builder() {
        return new MemberBuilder();
    }

    public static class MemberBuilder {
        private String firstName;
        private String middleName;
        private String lastName;
        private int age;
        private String country;

        private MemberBuilder() {}

        public MemberBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public MemberBuilder middleName(final String middleName) {
            this.middleName = middleName;
            return this;
        }

        public MemberBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public MemberBuilder age(final int age) {
            this.age = age;
            return this;
        }

        public MemberBuilder country(final String country) {
            this.country = country;
            return this;
        }

        public Member build() {
            return new Member(this.firstName, this.middleName, this.lastName, this.age, this.country);
        }
    }
}
```

<br />

보시다시피 빌더 하나만 구현했음에도 불구하고 굉장히 많은 코드가 작성되어야 합니다.

또한 이 대량의 코드들은 대부분의 객체에 반고정적으로 들어가는 코드들이기도 합니다.

여기에 `접근자(Getter)`, `수정자(Setter)`, `생성자(Constructor)` 등을 추가한다면 보일러 플레이트만 100줄이 넘어가는 경우도 종종 생깁니다.

이러한 문제를 롬복이 해결해 주는데, 롬복을 사용하면 다음과 같은 모습이 됩니다.

<br />

```java
public class Member {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final int age;
    private final String country;

    @Builder
    private Member(final String firstName, final String middleName, final String lastName, final int age, final String country) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
    }
}
```

<br />

클래스 레벨에 `@AllArgsConstructor`와 `@Builder`를 함께 선언할 수도 있으나, 클래스 변수로 컬렉션이 들어가거나 하는 경우 객체의 초기화가 제대로 되지 않는 등의 이슈가 있어서 보통 이런 방식으로 사용합니다.

<br />

마지막으로 롬복의 단점이라면 롬복은 리플렉션을 통해 동작하기 때문에, 타 라이브러리와의 호환성 이슈가 생기는 경우가 간혹 있습니다.
