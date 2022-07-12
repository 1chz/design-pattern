package io.github.shirohoo.creational.abstract_factory.jdbc;

import io.github.shirohoo.creational.abstract_factory.model.Lecture;

public class LectureMySQLRepository implements LectureRepository {
    @Override
    public void insert(Lecture lecture) {
        System.out.println("MySQL :: insert :: " + lecture);
    }

    @Override
    public void select(Lecture lecture) {
        System.out.println("MySQL :: select :: " + lecture);
    }

    @Override
    public void update(Lecture lecture) {
        System.out.println("MySQL :: update :: " + lecture);
    }

    @Override
    public void delete(Lecture lecture) {
        System.out.println("MySQL :: delete :: " + lecture);
    }
}
