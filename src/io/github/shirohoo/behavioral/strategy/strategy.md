# 📜 전략 패턴(Strategy Pattern) 혹은 정책 패턴(Policy Pattern)

<br />

`행위 패턴`에 속하는 `전략 패턴`입니다.

누군가는 `정책 패턴`이라고도 부르기도 합니다만, 대체적으로 전략 패턴이라는 이름으로 더 잘 알려져있습니다.

혹자는 `디자인 패턴의 꽃`이라고 부르기도 할 정도로 아주 쉬우면서도 널리 사랑받고, 사용되고 있는 강력한 패턴입니다.

<br >

디자인 패턴을 한마디로 쉽게 정의하는것은 매우 어렵지만, 전략 패턴을 사용하는 이유에 대해 핵심만 먼저 말씀드리자면...!

<br />

`어떠한 처리 방법(=알고리즘)을 캡슐화하고, 이것을 특정 객체의 상태로 사용하며 이를 런타임에 유연하게 변경하는 것`에 목적을 둡니다.

[헤드퍼스트 디자인패턴](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=582754) 에서는 오리가 난다, 아무것도 안한다 등으로 비유하고 있습니다.

실제로 사용해볼만한 예를 생각해보자면, 콜센터에서 문의전화를 받는데, 문의전화를 어떤 방식으로 상담원들에게 돌려줄지라던가, 게임을 만드는데 케릭터의 직업에 따라 스킬이 바뀐다던가 하는 것들에 적용해볼 수 있을 것 같습니다.

<br />

![image](https://user-images.githubusercontent.com/71188307/133035797-e53b96bc-6e7f-454d-a524-7d56eee9b724.png)

출처 - 위키백과

<br />

## 🛠 구현

역시 관련 아티클을 보다보면 여러가지 구현 방식이 있지만, 핵심은 `인터페이스`를 사용해 알고리즘을 캡슐화 하는 것입니다.

여기까지 이해한다면 이후 응용은 본인 하기 나름이겠죠?

저는 이번에도 역시 DB 구현체를 이용해 예제를 작성했습니다.

클라이언트가 오라클을 사용하다가 갑자기 MySQL을 사용하고 싶다고 한다면, 전략 클래스만 갈아끼워 유연하게 변경할 수 있을겁니다.***(실제 이런 경우는 아마도 없습니다 😅)***

<br />

우선 전략 클래스를 정의합니다.

<br />

```java
public interface DBConnector {
    void connect();
}

public final class MySQLConnector implements DBConnector {
    @Override
    public void connect() {
        System.out.println("connected to MySQL");
    }
}

public final class OracleConnector implements DBConnector {
    @Override
    public void connect() {
        System.out.println("connected to Oracle");
    }
}
```

<br />

전략 클래스를 사용 하기 위한 객체도 정의해줍니다.

<br />

```java
public enum Databases {
    MYSQL(MySQLConnector::new),
    ORACLE(OracleConnector::new);
    private final Supplier<DBConnector> supplier;

    Databases(Supplier<DBConnector> supplier) {
        this.supplier = supplier;
    }

    public DBConnector createConnector() {
        return supplier.get();
    }
}

public final class Connector {
    private final DBConnector dbConnector;

    private Connector(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public static Connector from(Databases databases) {
        return new Connector(databases.createConnector());
    }

    public void connect() {
        dbConnector.connect();
    }
}
```

<br />

```java
public class ConsoleRunner {
    public static void main(String[] args) {
        Connector mysql = Connector.from(Databases.MYSQL);
        mysql.connect();

        Connector oracle = Connector.from(Databases.ORACLE);
        oracle.connect();
    }
}
```

<br />

처음 `Connector` 객체를 생성할 때 `Oracle`을 주입받아 생성했고, 이후 커넥터를 `MySQL`로 변경했습니다.

그리고 이러한 변경은 전략 패턴을 적용함으로서 이렇게 쉽게 끝날 수 있습니다.

결과는 다음과 같습니다.

<br />

```shell
connected to MySQL
connected to Oracle
```

<br />
