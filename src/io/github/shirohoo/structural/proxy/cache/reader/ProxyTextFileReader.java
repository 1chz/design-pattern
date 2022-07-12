package io.github.shirohoo.structural.proxy.cache.reader;

import io.github.shirohoo.structural.proxy.cache.model.SecretText;
import io.github.shirohoo.structural.proxy.cache.util.SecretUtil;
import java.util.Objects;

public class ProxyTextFileReader implements TextFileReader {
    private final String plainText;
    private SecretText secretText;

    public ProxyTextFileReader(String encryptedText) {
        this.plainText = SecretUtil.decode(encryptedText);
    }

    @Override
    public SecretText read() {
        // 가지고 있는 파일이 없거나, 가지고 있는 파일과 요청받은 파일이 다른 경우 새로운 파일을 생성하여 캐시
        if (secretText == null || !Objects.equals(secretText.getPlainText(), this.plainText)) {
            System.out.println("reading text from : " + plainText);
            this.secretText = new SecretText(plainText);
            return this.secretText;
        }

        System.out.println("using cached text.");
        return new SecretText(plainText);
    }
}
