package io.shirohoo.structural.proxy.cache.model;

public class SecretText {
    private final String plainText;

    public SecretText(final String plainText) {
        this.plainText = plainText;
    }

    public String getPlainText() {
        return this.plainText;
    }
}
