package io.shirohoo.structural.proxy.lazy_loading;

import io.shirohoo.structural.proxy.cache.reader.TextFileReader;
import io.shirohoo.structural.proxy.lazy_loading.reader.LazyTextFileReader;

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
