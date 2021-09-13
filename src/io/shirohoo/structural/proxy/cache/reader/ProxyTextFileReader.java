package io.shirohoo.structural.proxy.cache.reader;

import io.shirohoo.structural.proxy.cache.model.SecretText;
import io.shirohoo.structural.proxy.cache.util.SecretUtil;

import java.util.Objects;

import static java.util.Objects.*;

public class ProxyTextFileReader implements TextFileReader {
    private String plainText;
    private SecretText secretText;

    public ProxyTextFileReader(final String encryptedText) {
        this.plainText = SecretUtil.decode(encryptedText);
    }

    @Override
    public SecretText read() {
        // 가지고 있는 파일이 없거나, 가지고 있는 파일과 요청받은 파일이 다른 경우 새로운 파일을 생성하여 캐시
        if(isNull(secretText) || !Objects.equals(secretText.getPlainText(), this.plainText)) {
            System.out.println("reading text from : " + plainText);
            this.secretText = new SecretText(plainText);
            return this.secretText;
        }

        System.out.println("using cached text.");
        return new SecretText(plainText);
    }
}
