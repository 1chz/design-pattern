package io.github.shirohoo.creational.abstract_factory.jdbc;

import io.github.shirohoo.creational.abstract_factory.model.Member;

public interface MemberRepository {
    void insert(Member member);

    void select(Member member);

    void update(Member member);

    void delete(Member member);
}
