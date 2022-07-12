package io.github.shirohoo.creational.abstract_factory.factory;

import io.github.shirohoo.creational.abstract_factory.jdbc.LectureOracleRepository;
import io.github.shirohoo.creational.abstract_factory.jdbc.LectureRepository;
import io.github.shirohoo.creational.abstract_factory.jdbc.MemberOracleRepository;
import io.github.shirohoo.creational.abstract_factory.jdbc.MemberRepository;

public class OracleRepositoryFactory implements RepositoryFactory {
    @Override
    public MemberRepository createMemberRepository() {
        return new MemberOracleRepository();
    }

    @Override
    public LectureRepository createLectureRepository() {
        return new LectureOracleRepository();
    }
}
