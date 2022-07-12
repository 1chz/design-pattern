# 📜 템플릿 메서드 패턴(Template Method Pattern)

<br />

코드를 작성하시다 보면 동일한 절차를 가지면서 일부 사항만 다르게 동작시키고 싶은 경우가 있습니다.

이럴 때 적용할 수 있는게 템플릿 메서드 패턴입니다.

주로 프레임워크에서 아주 많이 사용되는 패턴이며, 자바 개발자라면 당연하게 사용하는 스프링 프레임워크 역시 이 패턴을 많이 사용했습니다.

상위 클래스에서 전체적인 알고리즘을 구현하고, 구체적으로 다르게 동작시키고 싶은 부분만 하위 클래스에 위임합니다.

보통 여기서 상위 클래스는 주로 추상 클래스를 사용하게 됩니다.

<br />

그리고 추상 클래스를 사용 할 경우 `훅 메서드`를 사용할 수 있습니다.

추상 클래스에서 메서드를 추상 메서드로 선언할 경우 해당 추상 클래스를 상속하는 모든 하위 클래스는 추상 메서드를 반드시 구현해야만 합니다.

자바의 이러한 제약이 때로는 지나친 변경을 불러일으킬 수도 있습니다.

따라서 이럴 경우 메서드를 추상 메서드가 아닌 일반 메서드로, 또한 아무런 행위를 하지 않도록 정의한 것이 훅 메서드라는 것입니다.

<br />

훅 메서드를 사용한다면 추상 클래스를 상속하는 모든 하위 클래스가 훅 메서드가 필요할 경우에만 재정의하여 사용할 수 있게됩니다.

하지만 훅 메서드를 사용하지 않는 하위 클래스도 분명 있을 수 있기 때문에, 이 경우에는 별도의 처리를 해주어야만 합니다.

<br />

별개로 `자바 8`이후부터 `인터페이스`에 `default` 키워드가 사용가능해지며, 자바 8이상이라면 추상 클래스를 사용해야만 하는 빈도가 크게 줄어들었습니다.

이러한 변화로 `Spring MVC`의 `WebMvcConfigurerAdapter`가 훅 메서드가 많은 추상 클래스였는데, `deprecated`되며 인터페이스로 대체된 예가 있습니다.

<br />

![image](https://user-images.githubusercontent.com/71188307/132672064-677f06d1-b240-4728-98bd-63fdfefb824f.png)

- 출처: 위키백과

<br />

# ⚙ 시나리오

<br />

사용할 데이터베이스를 입력받아, 해당 데이터베이스에 접속하는 코드를 작성하려고 합니다.

이때 데이터베이스 벤더는 여러가지 종류가 존재하지만, 접속한다는 행위는 항상 똑같습니다.

이런 시나리오가 있을 경우 사용할 데이터베이스를 입력받는 부분만 추상 메서드로 정의하여 템플릿 메서드 패턴을 구현할 수 있습니다.

<br />

```java
public abstract class DBConnector {
    protected DatabaseConnection connection;

    // 데이터 베이스를 입력받습니다.
    public abstract void setDatabase(DatabaseDriver databaseDriver);

    // 입력받은 데이터베이스에 접속합니다.
    public void connect() {
        System.out.printf("Connected to %s%n", connection);
    }

    // 필요 할 경우 재정의할 훅 메서드
    public void hook() {
    }
}

public class MysQLConnector extends DBConnector {
    @Override
    public void setDatabase(DatabaseDriver databaseDriver) {
        super.connection = databaseDriver.getConnection();
    }
}
```

<br />

해당 코드를 실행하면...!

<br />

```java
public class ConsoleRunner {
    public static void main(String[] args) {
        DBConnector dbConnector = new MysQLConnector();

        dbConnector.setDatabase(DatabaseDriver.from("MySQL"));
        dbConnector.connect();
    }
}
```

<br />

아래와 같은 문자열이 출력되며, 정상적으로 동작하게 됩니다.

<br />

```shell
Connected to MySQL
```

<br />
