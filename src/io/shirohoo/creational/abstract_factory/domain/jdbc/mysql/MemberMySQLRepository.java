package io.shirohoo.creational.abstract_factory.domain.jdbc.mysql;

import io.shirohoo.creational.abstract_factory.domain.jdbc.MemberRepository;
import io.shirohoo.creational.abstract_factory.domain.model.Member;

public class MemberMySQLRepository implements MemberRepository {
    @Override
    public void insert(final Member member) {
        System.out.println("MySQL :: insert :: " + member);
    }

    @Override
    public void select(final Member member) {
        System.out.println("MySQL :: select :: " + member);
    }

    @Override
    public void update(final Member member) {
        System.out.println("MySQL :: update :: " + member);
    }

    @Override
    public void delete(final Member member) {
        System.out.println("MySQL :: delete :: " + member);
    }
}
