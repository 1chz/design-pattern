package io.github.shirohoo.structural.proxy.cache;

import io.github.shirohoo.structural.proxy.cache.reader.ProxyTextFileReader;
import io.github.shirohoo.structural.proxy.cache.reader.RealTextFileReader;
import io.github.shirohoo.structural.proxy.cache.reader.TextFileReader;

public class ConsoleRunner {
    public static void main(String[] args) {
        // 캐시하지 않음
        TextFileReader realTextFileReader = new RealTextFileReader("plainText");
        realTextFileReader.read();
        realTextFileReader.read();
        realTextFileReader.read();
        realTextFileReader.read();
        realTextFileReader.read();

        // 프록시 패턴을 활용한 캐시 기능 구현
        TextFileReader proxyTextFileReader = new ProxyTextFileReader("plainText");
        proxyTextFileReader.read();
        proxyTextFileReader.read();
        proxyTextFileReader.read();
        proxyTextFileReader.read();
        proxyTextFileReader.read();
    }
}
