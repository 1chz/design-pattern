package io.shirohoo.creational.abstract_factory.domain.factory.mysql;

import io.shirohoo.creational.abstract_factory.domain.factory.RepositoryFactory;
import io.shirohoo.creational.abstract_factory.domain.jdbc.LectureRepository;
import io.shirohoo.creational.abstract_factory.domain.jdbc.MemberRepository;
import io.shirohoo.creational.abstract_factory.domain.jdbc.mysql.LectureMySQLRepository;
import io.shirohoo.creational.abstract_factory.domain.jdbc.mysql.MemberMySQLRepository;

public class MySQLRepositoryFactory implements RepositoryFactory {
    @Override
    public MemberRepository createMemberRepository() {
        return new MemberMySQLRepository();
    }

    @Override
    public LectureRepository createLectureRepository() {
        return new LectureMySQLRepository();
    }
}
