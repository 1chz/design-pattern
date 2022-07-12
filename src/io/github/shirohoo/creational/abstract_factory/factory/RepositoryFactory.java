package io.github.shirohoo.creational.abstract_factory.factory;

import io.github.shirohoo.creational.abstract_factory.jdbc.LectureRepository;
import io.github.shirohoo.creational.abstract_factory.jdbc.MemberRepository;

public interface RepositoryFactory {
    MemberRepository createMemberRepository();

    LectureRepository createLectureRepository();
}
