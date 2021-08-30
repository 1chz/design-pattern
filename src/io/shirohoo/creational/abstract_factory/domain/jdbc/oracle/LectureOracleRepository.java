package io.shirohoo.creational.abstract_factory.domain.jdbc.oracle;

import io.shirohoo.creational.abstract_factory.domain.jdbc.LectureRepository;
import io.shirohoo.creational.abstract_factory.domain.model.Lecture;

public class LectureOracleRepository implements LectureRepository {
    @Override
    public void insert(final Lecture lecture) {
        System.out.println("Oracle :: insert :: " + lecture);
    }

    @Override
    public void select(final Lecture lecture) {
        System.out.println("Oracle :: select :: " + lecture);
    }

    @Override
    public void update(final Lecture lecture) {
        System.out.println("Oracle :: update :: " + lecture);
    }

    @Override
    public void delete(final Lecture lecture) {
        System.out.println("Oracle :: delete :: " + lecture);
    }
}
