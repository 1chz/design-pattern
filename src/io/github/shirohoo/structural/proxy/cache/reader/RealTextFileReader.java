package io.github.shirohoo.structural.proxy.cache.reader;

import io.github.shirohoo.structural.proxy.cache.model.SecretText;
import io.github.shirohoo.structural.proxy.cache.util.SecretUtil;

public class RealTextFileReader implements TextFileReader {
    private final String plainText;

    public RealTextFileReader(String encryptedText) {
        this.plainText = SecretUtil.decode(encryptedText);
    }

    @Override
    public SecretText read() {
        System.out.println("reading text from : " + plainText);
        return new SecretText(plainText);
    }
}

