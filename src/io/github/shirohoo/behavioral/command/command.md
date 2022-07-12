# 📜 커맨드 패턴(Command Pattern)

`행위 패턴`에 속하는 `커맨드 패턴`입니다.

웹에서는 일반적으로 메뉴같은 것들에 사용하면 아주 좋을것 같은 패턴이라고 생각됩니다.

커맨드 패턴에는 4가지 용어가 나오며 각 용어에 대해서는 밑에서 후술하도록 하겠습니다.

- 명령(Command)
- 클라이언트(Client)
- 수신자(Receiver)
- 호출자(Invoker)

<br />

커맨드 패턴은 행위 자체를 캡슐화합니다.

이렇게 해서 얻을 수 있는 이점이 무엇일까요?

바로 **호출자와 수신자의 의존성을 분리하여 피호출자가 변경되더라도 호출자는 아무런 영향을 받지 않게 되는 것**입니다.

즉, **객체지향 5대원칙상의 OCP(개방폐쇄원칙)** 를 충실히 따르게 되며, 이것이 바로 커맨드 패턴을 사용하는 가장 큰 이유이기도 합니다.

추가로 커맨드 패턴을 적용하게 되면 기본적으로 프록시를 사용하기 때문에 실행된 명령에 대한 `기록을 남기거나`, `취소(undo)`하는 등의 기능도 수월하게 추가할 수 있게 됩니다.

<br />

커맨드 패턴의 `명령(Command)`은 자바에서 `인터페이스(interface)`로 작성하며, 단 하나의 추상 메서드를 갖는데, 주로 `execute()`라는 이름을 사용합니다.

<br />

![image](https://user-images.githubusercontent.com/71188307/141615401-23e04fa3-4c76-4223-b025-8917d0d4f56c.png)

- 이미지 출처: [위키백과](https://ko.wikipedia.org/wiki/%EC%BB%A4%EB%A7%A8%EB%93%9C_%ED%8C%A8%ED%84%B4)

<br />

이제 각 용어가 어떻게 사용되는지 살펴봅시다.

예를 들어 집안의 모든 가전제품을 제어할 수 있는 리모컨이 있다고 할 때, 리모컨의 각 버튼은 각자가 제어할 수 있는 가전제품이 정해져있을 것입니다.

1번 버튼을 누르면 TV가 켜지고, 2번 버튼을 누르면 TV가 꺼지고, 3번 버튼을 누르면 에어컨이 켜지고 ...

이 모든것을 조건문을 사용해서 처리하게 될 경우 코드가 난잡해지는 문제도 크지만 !

`호출자(리모컨)`와 `수신자(버튼의 기능)`가 강결합되어있기 때문에 둘 중 하나가 변경 될 경우 다른 하나도 함께 변경되어야 한다는 아주 큰 문제점이 생깁니다.

커맨드 패턴에서는 이 문제를 프록시를 사용해 수신자를 캡슐화함으로써 해결합니다.

즉, `호출자` - `수신자`의 구조를 `호출자` - `명령` - `수신자`의 구조로 바꿉니다.

그리고 `클라이언트`는 리모컨을 사용하는 주체를 의미합니다.

웹에서는 사용자가 되겠죠?

<br />

# ⚙ 구현

리모컨의 버튼을 누르면 불이 켜지게 하고 싶습니다.

커맨드 패턴이 무엇인가에 대해 감을 잡기위한 아주 기초적인 예제이며, 이것을 커맨드 패턴을 이용해 구현해보도록 하겠습니다.

<br />

```java
// Command interface
public interface Command {
    void execute();
}

// Command implementation
public class LightOnCommand implements Command { // 기능을 감싸는 프록시 객체
    private final LightOn lightOn; // Receiver

    public LightOnCommand(LightOn lightOn) {
        this.lightOn = lightOn;
    }

    @Override
    public void execute() {
        lightOn.on();
    }
}

// Receiver
public class LightOn { // 실제 기능 그 자체
    public void on() {
        System.out.println("light on !");
    }
}

// Invoker
public class RemoteControl {
    private final Command lightOn;

    public RemoteControl(Command lightOn) {
        this.lightOn = lightOn;
    }

    public void pressedButton() {
        lightOn.execute();
    }
}

// 이 클래스는 커맨드 패턴과 아무런 관계가 없습니다.
public class RemoteControlLoader {
    public static RemoteControl load() {
        // 호출자에 명령을 세팅하고 반환
        LightOnCommand command = new LightOnCommand(new LightOn());
        return new RemoteControl(command);
    }
}

public class ConsoleRunner {
    public static void main(String[] args) {
        RemoteControl remoteControl = RemoteControlLoader.load();
        remoteControl.pressedButton();
    }
}
```

<br />
