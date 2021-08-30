package io.shirohoo.creational.abstract_factory.domain.jdbc;

import io.shirohoo.creational.abstract_factory.domain.model.Member;

public interface MemberRepository {
    void insert(final Member member);
    void select(final Member member);
    void update(final Member member);
    void delete(final Member member);
}
