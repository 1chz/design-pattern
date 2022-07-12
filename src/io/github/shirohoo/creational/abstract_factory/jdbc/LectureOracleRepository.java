package io.github.shirohoo.creational.abstract_factory.jdbc;

import io.github.shirohoo.creational.abstract_factory.model.Lecture;

public class LectureOracleRepository implements LectureRepository {
    @Override
    public void insert(Lecture lecture) {
        System.out.println("Oracle :: insert :: " + lecture);
    }

    @Override
    public void select(Lecture lecture) {
        System.out.println("Oracle :: select :: " + lecture);
    }

    @Override
    public void update(Lecture lecture) {
        System.out.println("Oracle :: update :: " + lecture);
    }

    @Override
    public void delete(Lecture lecture) {
        System.out.println("Oracle :: delete :: " + lecture);
    }
}
