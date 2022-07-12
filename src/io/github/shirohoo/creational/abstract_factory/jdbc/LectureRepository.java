package io.github.shirohoo.creational.abstract_factory.jdbc;

import io.github.shirohoo.creational.abstract_factory.model.Lecture;

public interface LectureRepository {
    void insert(Lecture lecture);

    void select(Lecture lecture);

    void update(Lecture lecture);

    void delete(Lecture lecture);
}
