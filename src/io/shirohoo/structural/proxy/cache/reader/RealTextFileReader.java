package io.shirohoo.structural.proxy.cache.reader;

import io.shirohoo.structural.proxy.cache.model.SecretText;
import io.shirohoo.structural.proxy.cache.util.SecretUtil;

public class RealTextFileReader implements TextFileReader {
    private final String plainText;

    public RealTextFileReader(final String plainText) {
        this.plainText = SecretUtil.decode(plainText);
    }

    @Override
    public SecretText read() {
        System.out.println("reading text from : " + plainText);
        return new SecretText(plainText);
    }
}

