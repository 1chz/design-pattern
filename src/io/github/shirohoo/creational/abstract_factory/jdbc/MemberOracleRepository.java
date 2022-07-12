package io.github.shirohoo.creational.abstract_factory.jdbc;

import io.github.shirohoo.creational.abstract_factory.model.Member;

public class MemberOracleRepository implements MemberRepository {
    @Override
    public void insert(Member member) {
        System.out.println("Oracle :: insert :: " + member);
    }

    @Override
    public void select(Member member) {
        System.out.println("Oracle :: select :: " + member);
    }

    @Override
    public void update(Member member) {
        System.out.println("Oracle :: update :: " + member);
    }

    @Override
    public void delete(Member member) {
        System.out.println("Oracle :: delete :: " + member);
    }
}
