package io.shirohoo.structural.proxy.cache;

import io.shirohoo.structural.proxy.cache.reader.ProxyTextFileReader;
import io.shirohoo.structural.proxy.cache.reader.RealTextFileReader;
import io.shirohoo.structural.proxy.cache.reader.TextFileReader;

public class ConsoleRunner {
    public static void main(String[] args) {
        // 캐시하지 않음
        final TextFileReader realTextFileReader = new RealTextFileReader("plainText");
        realTextFileReader.read();
        realTextFileReader.read();
        realTextFileReader.read();
        realTextFileReader.read();
        realTextFileReader.read();

        System.out.println("\n=================================================\n");

        // 프록시 패턴을 활용한 캐시 기능 구현
        final TextFileReader proxyTextFileReader = new ProxyTextFileReader("plainText");
        proxyTextFileReader.read();
        proxyTextFileReader.read();
        proxyTextFileReader.read();
        proxyTextFileReader.read();
        proxyTextFileReader.read();
    }
}
