# 📜 퍼사드 패턴(Facade Pattern)

`퍼사드 패턴`은 `구조 패턴` 중 하나이며, 외부로 노출하는 인터페이스를 단순화 하기 위한 목적을 갖는 디자인 패턴입니다.

`Facade`의 사전적 정의는 `건물의 정면`을 의미하며, 개발에서 퍼사드 패턴은 `은행 창구`와 비슷하다고 생각하시면 될 것 같습니다.

은행 창구에서 행원이 고객을 응대하고 임의의 작업을 요청받으면 뒤편 사무실에서는 많은 일들이 일어나죠.

<br />

개발로 예를 들자면, 개발자들은 개발할 때 IDE를 사용합니다.

그리고 코드를 작성한 후 작성 한 코드를 실행할 때 `Run` 과 관련된 명령어를 입력하거나, `초록색 화살표`와 같은 GUI 인터페이스를 한번 클릭하는 등의 행위를 합니다.

혹은 자바 코드를 작성하고 `CLI` 환경에서 컴파일 할 경우 `javac` 명령어를 통해 자바 컴파일러를 이용하기도 하죠.

즉, 이렇게 우리는 어떤 행위를 하기 위해 단 하나의 인터페이스만 알면 됩니다. 

우리가 이 인터페이스를 사용하게 되면 눈에 보이지 않는 곳에서 굉장히 복잡한, 많은 일들이 일어나게 되지만 우리는 그러한 작업들에 대해 몰라도 시스템을 사용하는데 아무런 문제가 없습니다.

<br />

정리하자면 퍼사드 패턴은 사용자 경험에 큰 도움이 되는 디자인 패턴이라고 할 수 있겠습니다.

이런 개념적인 부분을 먼저 이해하신 후 예제 코드를 보시면 좋을 것 같은데, 예제 코드가 비록 추상적이긴 하지만 [위키백과의 예시](https://ko.wikipedia.org/wiki/%ED%8D%BC%EC%82%AC%EB%93%9C_%ED%8C%A8%ED%84%B4)가 아주 적절하다고 생각해 해당 코드를 첨부합니다.

<br />

# 🛠 예제 코드

아래 `Java` 코드 예제는 `사용자(You)`가 `퍼사드(Computer)`를 통해 컴퓨터 내부의 부품(CPU, HDD)에 접근한다는 내용의 추상적인 예제이다.

<br />

```java
/* Complex parts */

class CPU {
	public void freeze() { ... }
	public void jump(long position) { ... }
	public void execute() { ... }
}

class Memory {
	public void load(long position, byte[] data) {
		...
	}
}

class HardDrive {
	public byte[] read(long lba, int size) {
		...
	}
}

/* Façade */

class Computer {
	public void startComputer() {
        CPU cpu = new CPU();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();
		cpu.freeze();
		memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));
		cpu.jump(BOOT_ADDRESS);
		cpu.execute();
	}
}

/* Client */

class You {
	public static void main(String[] args) throws ParseException {
		Computer facade = /* grab a facade instance */;
		facade.startComputer();
	}
}/* Complex parts */

class CPU {
	public void freeze() { ... }
	public void jump(long position) { ... }
	public void execute() { ... }
}

class Memory {
	public void load(long position, byte[] data) {
		...
	}
}

class HardDrive {
	public byte[] read(long lba, int size) {
		...
	}
}

/* Façade */

class Computer {
	public void startComputer() {
        CPU cpu = new CPU();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();
		cpu.freeze();
		memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));
		cpu.jump(BOOT_ADDRESS);
		cpu.execute();
	}
}

/* Client */

class You {
	public static void main(String[] args) throws ParseException {
		Computer facade = /* grab a facade instance */;
		facade.startComputer(); // 사용자는 퍼사드 객체가 제공하는 인터페이스만 호출하면 된다. 
	}
}
```

<br />