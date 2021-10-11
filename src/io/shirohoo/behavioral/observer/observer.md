# 📜 옵저버 패턴(Observer Pattern)

`행위 패턴`에 속하는 `옵저버 패턴`입니다.

이게 디자인 패턴상으로는 `옵저버`라고 표현하지만 실제로는 `리스너`, `컨슈머` 등의 용어로 혼용되어 사용되기도 합니다.

따라서 이미 어느정도 여러가지 애플리케이션을 다뤄보신분이라면 위의 용어들에 대입해서 생각해보시면 한결 이해하기 쉬우실 것 같습니다.

주로 이벤트 드리븐 방식의 구조에 사용되는 디자인 패턴입니다. 백엔드 개발자라면 최소 한번쯤을 사용해보셨을 `Nginx`도 이벤트 드리븐 방식으로 동작하죠 !

용어는 좀 집어치우고 간단하게 요약해보자면 이렇습니다.

<br />

옵저버 패턴에서 옵저버는 옵저버라는 단어의 뜻 그대로 관찰자를 의미합니다.

무언가를 관찰하고 있다가 관찰 대상에 어떤 변화가 감지되면 자신이 어떠한 행동을 하게됩니다. 

이를 `이벤트 드리븐(Event Driven) 방식`이라고도 표현해도 무방할 것 같습니다.

조금 더 이해하기 쉽도록 비유해서 설명볼게요.

<br />

학교 교실에서 친구들과 판치기를 하고있습니다. 

선생님에게 들키면 모든 동전을 압수당하고 혼이 나는 대참사가 발생하기 때문에 한명이 복도를 쳐다보며 망을 봅니다. 

이렇게 복도를 관찰하고있는 학생이 옵저버에 이벤트를 전달해주는, 주로 `퍼블리셔(Publisher)`, `프로듀서(Producer)`라고 불리는 객체라고 보시면 되겠습니다. 

그리고 판치기를 하고있는 학생들을 옵저버 패턴에서의 `옵저버`라도 봐도 무방할 것 같습니다. (주로 `리스너(Listener)`라고도 많이 부릅니다 !)

한참 재미있게 판치기를 하고있던 와중 망을 보던 학생은 복도에 선생님이 출현했음을 알았고, 친구들에게 선생님이 교실로 다가오고있음을 알립니다.

난리가 난 친구들은 즉시 판을 정리하고 자리에 앉습니다.

<br />

이 옵저버 패턴을 자바 라이브러리에서 많이 사용된 부분을 따지면 `스윙(Swing)`이 있겠습니다.

스프링 부트에서의 이벤트 처리또한 이를 응용하여 동작합니다.

조금 더 상세한 내용은 [📜 블로그 포스팅](https://shirohoo.github.io/spring/spring-boot/2021-09-15-spring-events/) 을 참고해주세요 !

또한, 자바 내장 API로 `Observable`과 `Observer`를 통해 이 옵저버 패턴을 제공하기도 합니다만, `자바9` 이후로 `Deprecated`되었으며, `Flow API` 사용을 권장하고 있습니다.

이 부분은 `RxJava`의 영역으로 들어가므로 여기서 더 다루지는 않겠습니다.

<br />

옵저버 패턴의 클래스 다이어그램은 대체로 아래와 같습니다.

<br />

![image](https://user-images.githubusercontent.com/71188307/134644185-4a4efa0c-ea67-4c12-9a71-fcdc363618cd.png)

-출처: 위키백과

<br />

# 📜 구현

우선 망보는 친구를 구현해봅시다.

<br />

```java
public class Watcher {

    private final Friends listener;
    
    // 생성자를 통해 선생님이 출현했음을 알림받을 친구들을 등록합니다.
    public Watcher(final Friends listener) {
        this.listener = listener;
    }

    // 친구들에게 이벤트가 발생(복도에 선생님이 출현)했음을 알려야 합니다 !
    public void publish(final String event){
        listener.listening(event);
    }

}
```

<br />

이어서 판치기하는 친구들을 구현합니다.

<br />

```java
public interface Friends {
    // 친구들은 판치기를 하면서도 망보는 친구가 해주는 얘기에 귀를 기울이고 있습니다 !
    void listening(final String event);
}
```

<br />

구현은 이렇게 아주 간단하게 끝났습니다.

이 객체들을 사용해볼까요?

<br />

```java
public class ConsoleRunner {

    public static void main(String[] args) {
        final Watcher publisher = new Watcher(
            event -> System.out.println(String.format("망보는 친구: 복도에 %s 선생님 출현 !", event))
        );

        publisher.publish("홍길동");
        publisher.publish("이순신");
        publisher.publish("유관순");

        System.out.println("친구들은 판을 정리하고 자리에 앉았다 !");
    }

}
```

<br />

위 코드를 실행시키면 아래와 같은 결과가 나타납니다.

<br />

```shell
망보는 친구: 복도에 홍길동 선생님 출현 !
망보는 친구: 복도에 이순신 선생님 출현 !
망보는 친구: 복도에 유관순 선생님 출현 !
친구들은 판을 정리하고 자리에 앉았다 !
```

<br />

디자인 패턴이 대체로 다 그렇지만, 이 옵저버 패턴도 역시 응용 방법이 정말 매우 많기 때문에 대략 이런 느낌으로 사용하는구나 ! 라고 감을 잡는게 중요할 것 같습니다.

<br />
