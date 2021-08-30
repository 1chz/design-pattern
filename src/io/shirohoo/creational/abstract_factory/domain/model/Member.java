package io.shirohoo.creational.abstract_factory.domain.model;

import java.util.Objects;

public class Member {
    private final String email;
    private final String password;

    private Member(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    public static Member of(final String email, final String password) {
        return new Member(email, password);
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        final Member member = (Member) o;
        return Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Member{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
