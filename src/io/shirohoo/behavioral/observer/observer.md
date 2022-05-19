# 📜 옵저버 패턴(Observer Pattern)

`행위 패턴`에 속하는 `옵저버 패턴`입니다.

디자인 패턴에서는 `옵저버`라고 표현하지만 실제로는 `리스너`, `컨슈머` 등의 용어로도 자주 사용됩니다.

따라서 이미 어느정도 여러가지 애플리케이션을 다뤄보신분이라면 위의 용어들에 대입해서 생각해보시면 한결 이해하기 쉬우실 것 같습니다.

옵저버 패턴은 `느슨한 결합`을 위해 사용됩니다.

주로 이벤트 드리븐 방식의 구조에서 많이 사용되는 디자인 패턴입니다. 백엔드 개발자시라면 최소 한번쯤은 사용해보셨을 `Nginx`도 이벤트 드리븐 방식으로 동작하죠 !

<br />

간단하게 요약해보자면 이렇습니다.

옵저버 패턴에서 옵저버는 옵저버라는 단어의 뜻 그대로 관찰자를 의미합니다.

무언가를 관찰하고 있다가 관찰 대상에 어떤 변화가 감지되면 자신도 어떠한 행동을 하게됩니다.

조금 더 이해하기 쉽도록 비유해서 설명볼게요.

<br />

학교 교실에서 친구들과 판치기를 하고있습니다.

선생님에게 들키면 모든 동전을 압수당하고 혼이 나는 대참사가 발생하기 때문에 한명이 복도를 쳐다보며 망을 봐야 합니다.

이렇게 복도를 관찰하고있는 학생이 옵저버에 이벤트를 전달해주는, 주로 `퍼블리셔(Publisher)`, 혹은 `프로듀서(Producer)`라고도 많이 불리우는 객체라고 보시면 되겠습니다.

옵저버 패턴에서는 이를 `주제(Subject)`라고 표현합니다. 신문사-구독자 모델에서 신문사가 이 `Subject` 라고 볼 수 있겠죠 !

그리고 판치기를 하고있는 학생들이 바로 옵저버 패턴에서 말하는 `옵저버` 입니다.

한참 재미있게 판치기를 하고있던 와중 망을 보던 학생은 복도에 선생님이 출현했음을 알았고, 친구들에게 선생님이 교실로 다가오고있음을 알렸습니다.

난리가 난 친구들은 즉시 판을 정리하고 자리에 앉습니다.

느낌이 오시나요?

<br />

이 옵저버 패턴을 자바 라이브러리에서 많이 사용된 부분을 찾아보면 `스윙(Swing)`이 있겠습니다.

또한 스프링 부트에서의 이벤트 처리도 이를 응용하여 동작합니다.

조금 더 상세한 내용은 [📜 블로그 포스팅](https://shirohoo.github.io/spring/spring-boot/2021-09-15-spring-events/) 을 참고해주세요 !

또한, 자바 내장 API로 `Observable`과 `Observer`를 통해 이 옵저버 패턴을 제공하기도 합니다만, `자바9` 이후로 `Deprecated`되었으며, `Flow API` 사용을 권장하고 있습니다.

이 부분은 `RxJava`의 영역으로 들어가므로 여기서 더 다루지는 않겠습니다. (저도 잘 모릅니다 !)

<br />

옵저버 패턴의 공식적인 클래스 다이어그램은 아래와 같습니다.

<br />

![image](https://user-images.githubusercontent.com/71188307/134644185-4a4efa0c-ea67-4c12-9a71-fcdc363618cd.png)

-출처: 위키백과

<br />

# 📜 구현

우선 망보는 친구를 구현해봅시다.

클래스 다이어그램 상으로는 `Subject` 입니다.

<br />

```java
public class LookOut {
    private final List<LittleRascals> friends;

    public LookOut() {
        this.friends = new ArrayList<>();
    }

    public void registerObserver(LittleRascals friend) {
        friends.add(friend);
    }

    public void notifyObservers(boolean teacherIsComing) {
        friends.forEach(friend -> friend.notify(teacherIsComing));
    }
}
```

<br />

이어서 판치기를 하고있는 개구쟁이 친구들을 구현합니다.

<br />

```java
public interface LittleRascals {
    void notify(boolean teacherIsComing);
}

public class Jjanggu implements LittleRascals {
    @Override
    public void notify(boolean teacherIsComing) {
        if (teacherIsComing) {
            System.out.println("짱구가 자리에 앉습니다 !");
        }
    }
}

public class Chulsoo implements LittleRascals {
    @Override
    public void notify(boolean teacherIsComing) {
        if (teacherIsComing) {
            System.out.println("철수가 자리에 앉습니다 !");
        }
    }
}

public class Younghee implements LittleRascals {
    @Override
    public void notify(boolean teacherIsComing) {
        if (teacherIsComing) {
            System.out.println("영희가 자리에 앉습니다 !");
        }
    }
}
```

<br />

구현은 이렇게 아주 간단하게 끝났습니다.

이 객체들을 사용해볼까요?

<br />

```java
public class ConsoleRunner {
    public static void main(String[] args) {
        LookOut lookOut = new LookOut();
        lookOut.registerObserver(new Jjanggu());
        lookOut.registerObserver(new Chulsoo());
        lookOut.registerObserver(new Younghee());

        System.out.println("망보는 친구: 복도에 선생님 출현하지 않음 !");
        lookOut.notifyObservers(false);

        System.out.println("망보는 친구: 복도에 선생님 출현 !");
        lookOut.notifyObservers(true);
    }
}
```

<br />

위 코드를 실행시키면 아래와 같은 결과가 나타납니다.

<br />

```shell
망보는 친구: 복도에 선생님 출현하지 않음 !
망보는 친구: 복도에 선생님 출현 !
짱구가 자리에 앉습니다 !
철수가 자리에 앉습니다 !
영희가 자리에 앉습니다 !
```

<br />

디자인 패턴이 대체로 다 그렇지만, 이 옵저버 패턴도 마찬가지로 응용 방법이 정말 매우 많기 때문에 대략 이런 느낌으로 사용하는구나 ! 라고 감을 잡는게 중요할 것 같습니다.

그리고 실제 사용되는 라이브러리등에서 `리스너`, `핸들러`, `퍼블리셔`, `프로듀서`, `컨슈머` 등의 용어들이 아주 많이 나오는데, 이 옵저버 패턴의 기출 변형이라고 이해하면 되겠습니다.

돋보기를 대고 보면 조금씩 의미는 다르겠지만, 어쨋건 비슷한 내용들입니다.

<br />
