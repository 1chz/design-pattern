package io.github.shirohoo.creational.abstract_factory;

import io.github.shirohoo.creational.abstract_factory.factory.MySQLRepositoryFactory;
import io.github.shirohoo.creational.abstract_factory.factory.OracleRepositoryFactory;
import io.github.shirohoo.creational.abstract_factory.factory.RepositoryFactory;
import io.github.shirohoo.creational.abstract_factory.jdbc.LectureRepository;
import io.github.shirohoo.creational.abstract_factory.jdbc.MemberRepository;
import io.github.shirohoo.creational.abstract_factory.model.DatabaseType;
import io.github.shirohoo.creational.abstract_factory.model.Lecture;
import io.github.shirohoo.creational.abstract_factory.model.Member;
import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;

public class ConsoleRunner {
    private static final String DATABASE = "database";

    public static void main(String[] args) throws Exception {
        RepositoryFactory factory = getFactory(readProperties().getProperty(DATABASE));

        memberCrud(createMember(), factory.createMemberRepository());

        System.out.println();

        lectureCrud(createLecture(), factory.createLectureRepository());
    }

    private static RepositoryFactory getFactory(String database) {
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
        FileInputStream fis = new FileInputStream("src/io/github/shirohoo/creational/abstract_factory/resources/application.properties");
        props.load(fis);
        return props;
    }

    private static Member createMember() {
        return Member.of("member@gmail.com", "password");
    }

    private static Lecture createLecture() {
        return Lecture.of(1L, "teacher", "title");
    }

    private static void lectureCrud(Lecture lecture, LectureRepository lectureRepository) {
        lectureRepository.insert(lecture);
        lectureRepository.select(lecture);
        lectureRepository.update(lecture);
        lectureRepository.delete(lecture);
    }

    private static void memberCrud(Member member, MemberRepository memberRepository) {
        memberRepository.insert(member);
        memberRepository.select(member);
        memberRepository.update(member);
        memberRepository.delete(member);
    }
}
