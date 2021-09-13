# 📜 팩토리 메서드 패턴(Factory Method Pattern)

<br />

`생성 패턴`중 하나인 `팩토리 메서드 패턴`은 여러 인스턴스를 생성해주는 팩토리 클래스를 만드는 것으로, 실제로 아주 많이 사용되고 있습니다.

<br />

몇가지 예를 들자면, 첫째로 자바의 스트림을 사용해보신 분이라면 많이 사용해보셨을 `Collectors` 입니다.

`Collectors.toList()`, `Collectors.toMap()`, `Collectors.toSet()` 같은 것들도 팩토리 메서드를 응용한 예라고 볼 수 있을 것입니다.

<br />

두번째 예로는 `로거(Logger)`입니다.

`스프링`과 `롬복` 사용하시는 분들이라면 `@Slf4j` 어노테이션을 많이 사용하시겠지만, 이 방식을 사용하지 않는 분들의 경우엔 `LoggerFactory`를 직접 호출하여 사용해본 경험이 있으실 겁니다.

<br />

```java
private final static Logger LOG = LoggerFactory.getLogger(SomeClass.class);
```

<br />

이 역시 팩토리 메서드 패턴을 응용한 한 예라고 볼 수 있을 것 같습니다.

<br />

세번째 예로는 멀티스레드 프로그래밍을 해보신 분이라면 역시 사용해보셨을 `Executors`입니다.

<br />

![image](https://user-images.githubusercontent.com/71188307/133013221-0740849d-77f7-4b7a-8ad1-fd281c839f9a.png)

<br />

보시다시피 한개의 팩토리가 여러가지 `ExecutorService` 인스턴스를 생성해주는 것을 볼 수 있습니다.

<br />

## 💥 팩토리 메서드 패턴을 사용해서 얻을 수 있는 이점?

저는 위 명제에 대해 다음과 같은 이점을 얻을 수 있지 않나 생각해봅니다.

- 객체 생성을 위해 알고있어야 할 API가 대폭 좁혀지거나 한가지로 통일된다.
   - 예를 들어 A클래스와 B클래스의 인스턴스를 생성하기 위해 각자의 클래스의 생성자를 직접 호출해야 한다면, 개발자는 두 클래스의 이름을 모두 알아야 합니다.
   - 이러한 클래스가 수십개가 넘어간다면 수십개 클래스의 이름을 모두 알고 있어야 합니다.
   - 만약 수십개 클래스의 인스턴스를 모두 하나의 클래스에서 생성해낼 수 있다면, 수십개 클래스의 인스턴스를 생성해주는 팩토리 클래스의 이름만 알고 있으면 됩니다.
   
<br />

## 🛠 구현

여러 아티클을 찾아보면 구현 방법은 정말 다양합니다만, 제가 생각하기에 핵심은 결국 다음과 같다고 생각합니다.

바로 `한개의 팩토리 클래스가 여러종류의 인스턴스를 생성해줄 수 있으면 된다는 것` 입니다.

이 핵심만 잘 이해한다면 언제 어떤상황에서라도 자신만의 방식으로 응용하여 적용할 수 있지 않을까요?

저는 모든 디자인패턴에 대해 이 생각을 가지고 학습하고 있습니다. 😁 

그래서 저만의 방식으로 간단하게 구현을 해봤습니다.

<br />

여러 종류의 DB 벤더가 있고, 각 DB에 접속할 수 있는 커넥터를 만들고 싶다고 가정해봅시다.

그리고 모든 DB 커넥터들을 단 하나의 팩토리에서 생성해줄 수 있다면, 개발자는 이 팩토리 객체의 이름만 알면 됩니다. 

<br  />

### 팩토리가 생성해낼 임의의 구현체를 정의

```java
public interface DBConnector {
    void connect();
}

public class MySQLConnector implements DBConnector{
    @Override
    public void connect() {
        System.out.println("connect MySQL");
    }
}

public class OracleConnector implements DBConnector{
    @Override
    public void connect() {
        System.out.println("connect Oracle");
    }
}
```

<br />

### 팩토리를 정의

```java
public final class DBConnectors {
    public static DBConnector createMySQLConnector() {
        return new MySQLConnector();
    }

    public static DBConnector createOracleConnector() {
        return new OracleConnector();
    }
}
```

<br />

### 결과

```java
public class ConsoleRunner {
    public static void main(String[] args) {
        System.out.println("======== MySQL ========");
        DBConnector mySQLConnector = DBConnectors.createMySQLConnector();
        mySQLConnector.connect();

        System.out.println("======== Oracle ========");
        DBConnector oracleConnector = DBConnectors.createOracleConnector();
        oracleConnector.connect();
    }
}
```

<br />

```shell
======== MySQL ========
connect MySQL
======== Oracle ========
connect Oracle
```

<br />

이제 개발자는 `DBConnectors` 라는 이름만 알고 있다면 DB가 수십개가 되더라도 언제든지 모든 DB의 인스턴스를 생성해낼 수 있습니다.

<br />