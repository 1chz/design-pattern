package io.shirohoo.structural.adpater;

import io.shirohoo.structural.adpater.v110.Adapter;
import io.shirohoo.structural.adpater.v110.HairDryer;
import io.shirohoo.structural.adpater.v110.Socket110V;
import io.shirohoo.structural.adpater.v220.AirConditioner;

public class ConsoleRunner {

    public static void main(String[] args) {
        HairDryer hairDryer = new HairDryer();
        Socket110V.connect(hairDryer);

        /*
        Socket110V.connect()가 110V만 입력받을 수 있기 때문에 이 코드는 컴파일 에러 발생.
        110V와 220V를 호환시켜줄 수 있는 어댑터가 필요하다.
        AirConditioner airConditioner = new AirConditioner();
        Socket110V.connect(airConditioner);
         */

        Adapter adapter = Adapter.from(new AirConditioner());
        Socket110V.connect(adapter);
    }

}
