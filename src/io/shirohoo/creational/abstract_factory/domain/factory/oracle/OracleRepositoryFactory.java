package io.shirohoo.creational.abstract_factory.domain.factory.oracle;

import io.shirohoo.creational.abstract_factory.domain.factory.RepositoryFactory;
import io.shirohoo.creational.abstract_factory.domain.jdbc.LectureRepository;
import io.shirohoo.creational.abstract_factory.domain.jdbc.MemberRepository;
import io.shirohoo.creational.abstract_factory.domain.jdbc.oracle.LectureOracleRepository;
import io.shirohoo.creational.abstract_factory.domain.jdbc.oracle.MemberOracleRepository;

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
