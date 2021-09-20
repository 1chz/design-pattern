package io.shirohoo.creational.abstract_factory.domain.factory;

import io.shirohoo.creational.abstract_factory.domain.jdbc.LectureRepository;
import io.shirohoo.creational.abstract_factory.domain.jdbc.MemberRepository;

public interface RepositoryFactory {

    MemberRepository createMemberRepository();

    LectureRepository createLectureRepository();

}
