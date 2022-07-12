package io.github.shirohoo.creational.abstract_factory.factory;

import io.github.shirohoo.creational.abstract_factory.jdbc.LectureMySQLRepository;
import io.github.shirohoo.creational.abstract_factory.jdbc.LectureRepository;
import io.github.shirohoo.creational.abstract_factory.jdbc.MemberMySQLRepository;
import io.github.shirohoo.creational.abstract_factory.jdbc.MemberRepository;

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
