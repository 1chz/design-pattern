package io.shirohoo.creational.abstract_factory.domain.jdbc.mysql;

import io.shirohoo.creational.abstract_factory.domain.jdbc.LectureRepository;
import io.shirohoo.creational.abstract_factory.domain.model.Lecture;

public class LectureMySQLRepository implements LectureRepository {

    @Override
    public void insert(final Lecture lecture) {
        System.out.println("MySQL :: insert :: " + lecture);
    }

    @Override
    public void select(final Lecture lecture) {
        System.out.println("MySQL :: select :: " + lecture);
    }

    @Override
    public void update(final Lecture lecture) {
        System.out.println("MySQL :: update :: " + lecture);
    }

    @Override
    public void delete(final Lecture lecture) {
        System.out.println("MySQL :: delete :: " + lecture);
    }

}
