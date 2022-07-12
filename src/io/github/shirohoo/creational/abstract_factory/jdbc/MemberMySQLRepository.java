package io.github.shirohoo.creational.abstract_factory.jdbc;

import io.github.shirohoo.creational.abstract_factory.model.Member;

public class MemberMySQLRepository implements MemberRepository {
    @Override
    public void insert(Member member) {
        System.out.println("MySQL :: insert :: " + member);
    }

    @Override
    public void select(Member member) {
        System.out.println("MySQL :: select :: " + member);
    }

    @Override
    public void update(Member member) {
        System.out.println("MySQL :: update :: " + member);
    }

    @Override
    public void delete(Member member) {
        System.out.println("MySQL :: delete :: " + member);
    }
}
