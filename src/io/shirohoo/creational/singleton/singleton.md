# 📜 싱글턴 패턴(Singleton Pattern)

<br />

`생성 패턴` 중 하나인 싱글턴 패턴은 메모리에 단 한개만 존재할 수 있는 객체를 정의합니다.

단 한개의 객체를 모든 쓰레드가 공유하여 사용함으로써 메모리를 효율적으로 사용할 수 있게 됩니다.

다만 중요하게 생각해야 할 것은, 싱글턴 패턴으로 구현된 객체는 여러 쓰레드에서 공유되며 사용되기 때문에 불변객체여야만 합니다.

만약 이러한 사항을 위반 할 경우 심각한 버그를 마주칠 수도 있습니다.

또한 객체를 더 이상 확장할 수 없기 때문에 디자인 패턴에서는 안티 패턴에 속하는 패턴이기도 합니다.

이러한 장단점이 존재하니 잘 고려하여 사용하는 것이 중요하겠습니다.

<br />

싱글턴 패턴은 다음과 같은 규칙으로 구현됩니다.

1. 모든 생성자의 접근 레벨을 `private`으로 제한하여 외부에서 해당 객체를 더 이상 생성하지 못하게 합니다.
2. 객체가 `클래스 로더`에 의해 초기화 되는 시점에 생성자를 스스로 호출하여 반환된 인스턴스를 자기 자신이 갖습니다.
3. `클래스 로더`에 의해 초기화 된 단 하나의 인스턴스를 공개된 정적 메서드를 통해 외부에 제공합니다.

<br />

---

<br />

# ⚙ Eager Initialization

<br />

```java
public class EagerInitialization {
    private static final EagerInitialization instance = new EagerInitialization();

    private EagerInitialization() {}

    public static EagerInitialization getInstance() {
        return instance;
    }
}
```

<br />

어떻게 사용되어도 무방한 가장 기본적인 형태의 싱글턴 패턴입니다.

클래스 로더에 의해 클래스가 최초로 로딩되는 시점에 인스턴스가 생성되기 때문에 `Thread-safe`를 보장합니다.

<br />

# ⚙ Synchronized Lazy Initialization

<br />

```java
public class SyncLazyInitialization {
    private static SyncLazyInitialization instance;

    private SyncLazyInitialization() {}

    public static synchronized SyncLazyInitialization getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SyncLazyInitialization();
        }
        return instance;
    }
}
```

<br />

싱글턴 패턴에서 약간 더 메모리를 절약하기 위해 고안 된 방법입니다.

하지만 동시성 문제를 해결하기 위해 `synchronized`구문을 사용 함으로써 속도가 어마어마하게 느려진다는 치명적인 단점을 갖습니다.

이를 해결하기 위해 인스턴스가 `null`인 경우에만 `synchronized`구문을 사용하는 개선 방안도 존재하지만, 코드의 가독성이 떨어진다는 단점을 갖습니다.

<br />

# ⚙ Lazy Holder

<br />

```java
public class LazyHolderInitialization {
    private LazyHolderInitialization() {}

    public static LazyHolderInitialization getInstance() {
        return LazyHolder.instance;
    }

    private static class LazyHolder {
        private static final LazyHolderInitialization instance = new LazyHolderInitialization();
    }
}
```

<br />

`synchronized`같은 구문을 사용하지 않더라도 동시성 문제를 해결 할 수 있습니다.

동시에 `지연로딩`까지 가능하기 때문에 가장 권장되는 싱글턴 패턴 구현 방법입니다.

`정적 내부 클래스`와 `동적바인딩`을 극한으로 활용한 예입니다.

<br />

# 🛠 실제 사용 예제

<br />

현재 프로그램에서 여러가지 `Random`한 값들을 사용해야 합니다.

하지만 별 생각 없이 필요할 때마다 `new Random`을 매번 호출해서 사용한다면 이는 메모리의 낭비라고 볼 수 있을 것입니다.

따라서 내부적으로 `Random`을 생성하여 갖고 이 `Random`을 통해 외부에 각종 랜덤한 값을 제공하는 유틸리티 싱글턴 객체를 작성해보겠습니다.

<br />

```java
public class RandomValueGenerator {
    private final Random random;

    private RandomValueGenerator(final Random random) {
        this.random = random;
    }

    public static RandomValueGenerator getInstance() {
        return RandomValueGeneratorHolder.instance;
    }

    public int nextInt(final int bound) {
        return random.nextInt(bound);
    }

    public boolean nextBoolean() {
        return random.nextBoolean();
    }

    private static class RandomValueGeneratorHolder {
        private static final RandomValueGenerator instance = new RandomValueGenerator(new Random());
    }
}
```

<br />

이제부터 프로그램에서는 `RandomValueGenerator.getInstance()`를 통해 `Random`의 인터페이스를 사용할 수 있으면서 메모리도 아낄 수 있게 됩니다.

그리고 `Random`의 어떤 인터페이스를 외부에 공개할지에 대한 판단 또한 개발자의 제어하에 들어옵니다. (`Encapsulation`)

<br />
