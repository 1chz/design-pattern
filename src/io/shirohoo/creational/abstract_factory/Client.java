package io.shirohoo.creational.abstract_factory;

import io.shirohoo.creational.abstract_factory.domain.factory.RepositoryFactory;
import io.shirohoo.creational.abstract_factory.domain.factory.mysql.MySQLRepositoryFactory;
import io.shirohoo.creational.abstract_factory.domain.factory.oracle.OracleRepositoryFactory;
import io.shirohoo.creational.abstract_factory.domain.jdbc.LectureRepository;
import io.shirohoo.creational.abstract_factory.domain.jdbc.MemberRepository;
import io.shirohoo.creational.abstract_factory.domain.model.Lecture;
import io.shirohoo.creational.abstract_factory.domain.model.Member;
import io.shirohoo.creational.abstract_factory.type.DatabaseType;
import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;

public class Client {

    private static final String DATABASE = "database";

    public static void main(String[] args) throws Exception {
        RepositoryFactory factory = getFactory(readProperties().getProperty(DATABASE));

        memberCRUD(createMember(), factory.createMemberRepository());

        System.out.println();

        lectureCRUD(createLecture(), factory.createLectureRepository());
    }

    private static RepositoryFactory getFactory(final String database) {
        if (Objects.equals(database, DatabaseType.MYSQL.getDatabase())) {
            return new MySQLRepositoryFactory();
        }

        if (Objects.equals(database, DatabaseType.ORACLE.getDatabase())) {
            return new OracleRepositoryFactory();
        }

        throw new IllegalArgumentException("database not found !");
    }

    private static Properties readProperties() throws Exception {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("src/io/shirohoo/creational/abstract_factory/resources/application.properties");
        props.load(fis);
        return props;
    }

    private static Member createMember() {
        return Member.of("member@gmail.com", "password");
    }

    private static Lecture createLecture() {
        return Lecture.of(1L, "teacher", "title");
    }

    private static void lectureCRUD(final Lecture lecture, final LectureRepository lectureRepository) {
        lectureRepository.insert(lecture);
        lectureRepository.select(lecture);
        lectureRepository.update(lecture);
        lectureRepository.delete(lecture);
    }

    private static void memberCRUD(final Member member, final MemberRepository memberRepository) {
        memberRepository.insert(member);
        memberRepository.select(member);
        memberRepository.update(member);
        memberRepository.delete(member);
    }

}
