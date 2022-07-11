package io.shirohoo.structural.proxy.lazy_loading.reader;

import io.shirohoo.structural.proxy.cache.model.SecretText;
import io.shirohoo.structural.proxy.cache.reader.RealTextFileReader;
import io.shirohoo.structural.proxy.cache.reader.TextFileReader;
import io.shirohoo.structural.proxy.cache.util.SecretUtil;

public class LazyTextFileReader implements TextFileReader {

    private final String plainText;
    private TextFileReader reader;

    public LazyTextFileReader(String encryptedText) {
        this.plainText = SecretUtil.decode(encryptedText);
    }

    @Override
    public SecretText read() {
        if (reader == null) {
            System.out.println("lazy initialisation");
            reader = new RealTextFileReader(plainText);
        }
        return reader.read();
    }

}
