# 📜 프록시 패턴(Proxy Pattern)

<br />

`프록시 패턴`은 `구조 패턴` 중 하나이며, `프록시`는 한글로 대리자, 대리인이라는 뜻을 갖습니다.

프록시 패턴은 별도의 라이브러리를 통해 사용하게 되는 경우가 많기 때문에, 실제로 직접 구현할 일은 생각보다 많지는 않지만, 프록시가 뭔지에 대한 이해는 제대로 해야 합니다.

실제로 사용되는 곳이 매우 많기 때문입니다.

몇가지 예를 들어보자면 JPA의 지연로딩, ASM과 ASM을 사용하는 ByteBuddy, 각종 캐시관련 라이브러리 등이 있겠습니다.

자바에도 프록시 패턴을 쉽게 사용할 수 있게 이미 `java.lang.reflect.Proxy`라는 클래스를 만들어두었습니다. 

아무튼 프록시는 실제로 굉장히 유용하게 사용되고 있는 패턴이며, 대부분의 라이브러리에서 핵심적인 역할을 담당하고 있으니, 이에 대한 이해가 제대로 수반되어야 할 것입니다.

<br />

프로그래밍에는 `인디렉션(Indirection)`이라는 개념이 있습니다.

**<br />**

> 📜 Dennis DeBruler
> > 컴퓨터 과학은 인디렉션 계층을 한 단계 더 만들면 모든 문제를 풀 수 있다고 믿는 학문이다.

<br />

인디렉션에 대한 여러 아티클을 참고해보면 두 계층사이에 어떤 문제가 발생했을 때 두 계층 사이에 별도의 계층을 하나 추가하면 해당 문제가 깔끔하게 해결되는 경우가 많다는 내용이 자주 나옵니다

그리고 이런 개념을 프로그래밍 용어로 바꿔보면 `간접참조` 혹은 `추상화`로 이해해도 무방 할 것 같습니다.

<br />

`프록시`는 이 위 내용과 궤를 같이하며 프록시 패턴은 SOLID중 `개방폐쇄원칙(OCP)`과 `의존역전원칙(DIP)`을 충실히 따릅니다.

<br />

여기까지는 사전적인 정의들이었습니다.

그렇다면 정말 프록시는 뭘까요?

<br />

프로그래밍에서의 프록시는 정말 사전적인 뜻 그대로 특정 개체의 사이에 위치하는, 진짜인 척을 하는 가짜 개체입니다.

그리고 프록시가 목표로 하는 것은 `본연의 로직에는 전혀 영향을 주지 않으면서 부가적인 기능을 더하는 것`입니다.

결과적으로 실제 두 개체는 사이에 프록시라는 가짜 개체가 있는지 전혀 모른채로 서로를 진짜라고 신뢰하고 메시지를 주고받게 됩니다.

코드를 통해 보시죠.

<br />

![image](https://user-images.githubusercontent.com/71188307/125183114-d42d5800-e24e-11eb-927c-62399c15aaab.png)

<br />

통상적인 프록시 패턴의 클래스 다이어그램입니다.

이를 코드로 풀어내면 다음과 같습니다.

<br />

```java
public class Client {
    private Operator operator;

    public Client(final Operator operator) {
        this.operator = operator;
    }

    public void callOperation(){
        operator.operation();
    }
}
```

<br />

```java
public interface Operator {
    void operation();
}
```

<br />

```java
public class RealOperator implements Operator{
    @Override
    public void operation() {
        System.out.println("it's real operator !");
    }
}
```

<br />

```java
public class ProxyOperator implements Operator {
    private final Operator operator = new RealOperator();

    @Override
    public void operation() {
        System.out.println("it's proxy operator !");
        operator.operation();
    }
}
```

<br />

```java
public class ConsoleRunner {
    public static void main(String[] args) {
        Client client = new Client(new ProxyOperator());
        client.callOperation();
    }
}
```

<br />

출력은 다음과 같습니다.

<br />

```shell
it's proxy operator !
it's real operator !
```

<br />

중간에 `ProxyOperator`의 로직이 추가될 수 있으면서

실제 결과는 `RealOperator`의 연산결과가 나옴을 볼 수 있습니다.

<br />

## 🤔 프록시를 사용하는 이유 ?

---

이제 프록시가 대충 뭔지 알았다면 프록시를 사용하는 이유에 대한 납득이 필요합니다.

프록시는 대표적으로 다음과 같은 역할들을 수행할 수 있습니다.

<br />

1. 흐름제어
2. 캐싱
3. 지연연산

<br />

### 💡 흐름제어

---

서문의 예시코드와 같습니다.

예시코드에서는 단순히

<br />

```java
System.out.println("it's proxy operator !");
```

<br />

한줄만을 추가했지만, 이곳에 개발자가 임의의 코드를 추가할 수도 있을 것입니다.

일반적으로는 조건문을 사용하여 특정 조건에 대한 흐름을 제어하게 되는 경우가 많습니다.

이러한 흐름제어 기능을 극한으로 활용하는 예로 `포워드 프록시`, `리버스 프록시` 등이 있을 것 같습니다.

또한, 스프링에서는 이를 활용해 `AOP` 기술을 구현하고 있으며 `AOP` 기술을 통해 구현되는 대표적인 기술로는 우리가 자주 사용하는 `@Transactional`이 있습니다.

<br />

### 💡 캐싱

---

> 📜 시나리오
>
> 특정한 텍스트를 읽어 복호화하여 반환해주는 기능이 있습니다.
>
> 매번 새로운 텍스트를 반환하니 리소스의 낭비가 심해 이를 캐시하고자 합니다.

<br />

```java
public interface TextFileReader {
    SecretText read();
}
```

<br />

```java
public class RealTextFileReader implements TextFileReader {
    private final String plainText;

    public RealTextFileReader(final String encryptedText) {
        this.plainText = SecretUtil.decode(encryptedText);
    }

    @Override
    public SecretText read() {
        System.out.println("reading text from : " + plainText);
        return new SecretText(plainText);
    }
}
```

<br />

```java
public class ConsoleRunner {
    public static void main(String[] args) {
        // 캐시하지 않음
        final TextFileReader realTextFileReader = new RealTextFileReader("plainText");
        realTextFileReader.read();
        realTextFileReader.read();
        realTextFileReader.read();
        realTextFileReader.read();
        realTextFileReader.read();
    }
}
```

<br />

출력은 다음과 같습니다.

<br />

```shell
reading text from : plainText
reading text from : plainText
reading text from : plainText
reading text from : plainText
reading text from : plainText
```

<br />

이를 프록시 패턴을 활용해 캐시해볼겁니다.

<br />

```java
public class ProxyTextFileReader implements TextFileReader {
    private String plainText;
    private SecretText secretText;

    public ProxyTextFileReader(final String encryptedText) {
        this.plainText = SecretUtil.decode(encryptedText);
    }

    @Override
    public SecretText read() {
        // 가지고 있는 파일이 없거나, 가지고 있는 파일과 요청받은 파일이 다른 경우 새로운 파일을 생성하여 캐시
        if(isNull(secretText) || !Objects.equals(secretText.getPlainText(), this.plainText)) {
            System.out.println("reading text from : " + plainText);
            this.secretText = new SecretText(plainText);
            return this.secretText;
        }

        System.out.println("using cached text.");
        return new SecretText(plainText);
    }
}
```

<br />

생성자를 통해 `ProxyTextFileReader`가 초기화되면 내부에 복호화 한 파일을 캐시해두고 이후 호출되면 캐시해둔 파일을 즉시 리턴하는 코드입니다.

<br />

```java
public class ConsoleRunner {
    public static void main(String[] args) {
        // 프록시 패턴을 활용한 캐시 기능 구현
        final TextFileReader proxyTextFileReader = new ProxyTextFileReader("plainText");
        proxyTextFileReader.read();
        proxyTextFileReader.read();
        proxyTextFileReader.read();
        proxyTextFileReader.read();
        proxyTextFileReader.read();
    }
}
```

<br />

```text
reading text from : plainText
using cached text.
using cached text.
using cached text.
using cached text.
```

<br />

이처럼 프록시를 사용하여 기존의 아키텍처에 영향을 주지 않는 선에서 캐시 기능을 간단하게 추가할 수도 있습니다.

<br />

### 💡 지연연산

---

지연연산이라는 것은 어떤 연산이 정말로 실행되어야 하기 전까지 해당 연산의 실행을 유예하는 것입니다.

이렇게 함으로써 필요하지 않은 연산을 최소화하여 성능 최적화를 시도할 수 있습니다.

<br />

구현은 정말정말 간단합니다.

진짜 객체를 프록시로 한번 래핑하면 끝입니다.

<br />

```java
public class LazyTextFileReader implements TextFileReader {
    private String plainText;
    private TextFileReader reader;

    public LazyTextFileReader(final String encryptedText) {
        this.plainText = SecretUtil.decode(encryptedText);
    }

    @Override
    public SecretText read() {
        if (isNull(reader)) {
            reader = new RealTextFileReader(plainText);
        }
        System.out.println("lazy initialisation");
        return reader.read();
    }
}
```

<br />

```java
public class ConsoleRunner {
    public static void main(String[] args) {
        TextFileReader lazyTextFileReader = new LazyTextFileReader("plainText");
        lazyTextFileReader.read();
        lazyTextFileReader.read();
        lazyTextFileReader.read();
        lazyTextFileReader.read();
        lazyTextFileReader.read();
    }
}
```

<br />

```text
lazy initialisation
reading text from : plainText
lazy initialisation
reading text from : plainText
lazy initialisation
reading text from : plainText
lazy initialisation
reading text from : plainText
lazy initialisation
reading text from : plainText
```

<br />

이렇게 하면 `read()`가 정말로 실행되야 할 순간이 오면 그제야 진짜 객체를 생성하고 호출하여 연산을 시작합니다.

<br />  