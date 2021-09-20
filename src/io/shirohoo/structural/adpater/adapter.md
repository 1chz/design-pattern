# 📜 어댑터 패턴(Adapter Pattern)

<br />

`어댑터 패턴`은 `구조 패턴` 중 하나이며, `인터페이스 호환`을 위한 디자인 패턴입니다.

`인터페이스 호환`에 대해 이해하지 못한다면 추후에 어댑터 패턴을 `데코레이터 패턴`, `프록시 패턴`등과 헷갈릴 수도 있습니다.

<br />

## 💡 시나리오

- 110V와 220V를 사용하는 가전제품이 있습니다.
- 두 가전 제품은 정격전압이 다르기 때문에 서로 다른 소켓을 사용해야만 합니다.
- 하지만 두 가전 제품을 하나의 소켓으로 사용하고 싶습니다.

<br />

```java
public interface Electronic220V {

    void on();

}

public class AirConditioner implements Electronic220V {

    @Override
    public void on() {
        System.out.println("220V - 에어컨");
    }

}
```

<br />

```java
public interface Electronic110V {

    void on();

}

public class HairDryer implements Electronic110V {

    @Override
    public void on() {
        System.out.println("110V - 헤어 드라이기");
    }

}
```

<br />

```java
public class Socket110V {

    public static void connect(final Electronic110V electronic110V) {
        electronic110V.on();
    }

}
```

<br />

```java
public class ConsoleRunner {

    public static void main(String[] args) {
        HairDryer hairDryer = new HairDryer();
        Socket110V.connect(hairDryer);
    }

}

public class ConsoleRunner {

    public static void main(String[] args) {
        // Socket110V.connect()가 110V만 입력받을 수 있기 때문에 이 코드는 컴파일 에러 발생.
        // 110V와 220V를 호환시켜줄 수 있는 어댑터가 필요하다.
        AirConditioner airConditioner = new AirConditioner();
        Socket110V.connect(airConditioner); // 불가
    }

}
```

<br />

110V와 220V를 호환해줄 수 있는 어댑터를 작성합니다.

<br />

```java
public class Adapter implements Electronic110V {

    private final Electronic220V electronic220V;

    private Adapter(final Electronic220V electronic220V) {
        this.electronic220V = electronic220V;
    }

    public static Adapter from(final Electronic220V electronic220V) {
        return new Adapter(electronic220V);
    }

    @Override
    public void on() {
        System.out.println("220V -> 110V 컨버트 !");
        electronic220V.on();
    }

}
```

<br />

이 어댑터는 이제 이렇게 사용할 수 있습니다.

<br />

```java
public class ConsoleRunner {

    public static void main(String[] args) {
        Adapter adapter = Adapter.from(new AirConditioner());
        Socket110V.connect(adapter);
    }

}
```

<br />

출력은 다음과 같습니다.

<br />

```shell
220V -> 110V 컨버트 !
220V - 에어컨
```

<br />

# 사용처 ?

자바 SDK에서 어댑터 패턴이 적용된 예제라고 하면 `InputStreamReader` 가 있습니다.

<br />

![image](https://user-images.githubusercontent.com/71188307/133993951-0fa374c6-ad0a-43b7-afde-97d01d921e4c.png)


<br />

외에도 주로 표준 입출력과 관계된 라이브러리에 `어댑터 패턴`, `데코레이터 패턴`등이 많이 사용됐습니다.

<br />

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
```

<br />

`BufferedReader`의 생성자 코드를 까보면 다음과 같습니다.

<br />

```java
public BufferedReader(Reader in){
    this(in,defaultCharBufferSize);
    }
```

<br />

파라미터로 `Reader` 타입을 받는데,  `System.in`은 `InputStream` 타입을 반환하므로 원래 호환되지 않아야 정상입니다.

<br />

```java
public final static InputStream in=null;
```

<br />

하지만 여기에 `InputStreamReader`라는, `InputStream`을 입력받아 `Reader` 타입을 반환해주는 어댑터 클래스를 적용하므로써 호환이 가능해집니다.

<br />

```java
public class InputStreamReader extends Reader { // InputStreamReader 는 Reader 를 상속받습니다.

    public InputStreamReader(InputStream in) {
        super(in);
        sd = StreamDecoder.forInputStreamReader(in, this,
            Charset.defaultCharset()); // ## check lock object
    }

}
```

<br />
