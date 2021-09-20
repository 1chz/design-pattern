package io.shirohoo.creational.abstract_factory.domain.jdbc.oracle;

import io.shirohoo.creational.abstract_factory.domain.jdbc.MemberRepository;
import io.shirohoo.creational.abstract_factory.domain.model.Member;

public class MemberOracleRepository implements MemberRepository {

    @Override
    public void insert(final Member member) {
        System.out.println("Oracle :: insert :: " + member);
    }

    @Override
    public void select(final Member member) {
        System.out.println("Oracle :: select :: " + member);
    }

    @Override
    public void update(final Member member) {
        System.out.println("Oracle :: update :: " + member);
    }

    @Override
    public void delete(final Member member) {
        System.out.println("Oracle :: delete :: " + member);
    }

}
