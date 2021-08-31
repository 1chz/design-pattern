package io.shirohoo.creational.builder;

public class Member {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final int age;
    private final String country;

    private Member(final String firstName, final String middleName, final String lastName, final int age, final String country) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
    }

    public static MemberBuilder builder() {
        return new MemberBuilder();
    }

    public static class MemberBuilder {
        private String firstName;
        private String middleName;
        private String lastName;
        private int age;
        private String country;

        private MemberBuilder() {}

        public MemberBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public MemberBuilder middleName(final String middleName) {
            this.middleName = middleName;
            return this;
        }

        public MemberBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public MemberBuilder age(final int age) {
            this.age = age;
            return this;
        }

        public MemberBuilder country(final String country) {
            this.country = country;
            return this;
        }

        public Member build() {
            return new Member(this.firstName, this.middleName, this.lastName, this.age, this.country);
        }
    }
}
