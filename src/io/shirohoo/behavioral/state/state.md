# 📜 상태 패턴(State Pattern)

`행위 패턴`에 속하는 `상태 패턴`입니다.

클래스의 내부 상태에 따라 클래스가 하는 일이 바뀌는 것을 목적으로 합니다.

이를 상태 패턴을 적용하지 않고 직접 구현 할 경우 `if/else` 문이 굉장히 많이 사용되게 됩니다.

이렇게 조건문이 많아지게 되면 코드를 봤을 때 한눈에 무슨 작업을 하고 있는지 알아보기 힘듭니다. 즉, 가독성이 일단 떨어집니다.

또한, 요구사항이 변경되어 코드를 수정해야 할 경우 유지보수성도 떨어지게 됩니다.

코딩을 하다보면 조건문을 아예 안쓸수는 없지만, 최대한 사용하지 않는 방향으로 코딩하는 것이 중요한 것 같습니다.

<br />

## 🛠 구현

<br />

![image](https://user-images.githubusercontent.com/71188307/136762407-4b3ac575-cd2e-426f-a899-b3e1678411dc.png)

<br />

컴퓨터의 전원이 켜져있는지, 꺼져있는지에 따라 다른 동작을 하게끔 하고 싶다고 가정하겠습니다.

먼저 컴퓨터의 상태를 작성합니다. 

<br />

```java
public interface PowerState {

    void powerOperate();

}

public class PowerOn implements PowerState {

    @Override
    public void powerOperate() {
        System.out.println("Power off.");
    }

}

public class PowerOff implements PowerState {

    @Override
    public void powerOperate() {
        System.out.println("Power on.");
    }

}
```

<br />

그리고 구현한 상태를 사용하는 컴퓨터를 작성합니다.

<br />

```java
public class Computer {

    private PowerState powerState;

    private Computer(final PowerState powerState) {
        this.powerState = powerState;
    }


    public static Computer from(final PowerState powerState) {
        return new Computer(powerState);
    }

    public void changePowerState(final PowerState powerState) {
        this.powerState = powerState;
    }

    public void powerOperate() {
        powerState.powerOperate();
    }

}
```

<br />

작성한 컴퓨터를 사용해보면...

<br />

```java
public class ConsoleRunner {

    public static void main(String[] args) {
        // 컴퓨터가 꺼진 상태로 초기화
        Computer computer = Computer.from(new PowerOff());

        computer.powerOperate(); // Power on. 출력
        computer.changePowerState(new PowerOn()); // 컴퓨터가 켜진 상태로 변경

        computer.powerOperate(); // Power off. 출력
        computer.changePowerState(new PowerOff()); // 컴퓨터가 꺼진 상태로 변경
    }

}
```

<br />

```shell
Power on.
Power off.
```

<br />

컴퓨터의 전원 상태에 절전모드 등이 추가되더라도, 절전모드를 표현하는 클래스만 하나 추가하면 매끄러운 확장이 가능해집니다.

또한, 각 상태가 클래스로 분리되어있기 때문에, 특정 상태만 변경하고자 하면 해당 상태를 표현하는 클래스만 집중적으로 살펴보면 되게 됩니다.

이렇게 상태 패턴은 조건문을 획기적으로 줄이면서도 유지보수하기 좋은 코드를 만들 수 있게 해줍니다.

전략 패턴과 아주 유사한데, 전략 패턴은 상속을 줄이기 위한 목적으로 사용되고, 상태 패턴은 조건문을 줄이기 위한 목적으로 사용됩니다.

<br />
