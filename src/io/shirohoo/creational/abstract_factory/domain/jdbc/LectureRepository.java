package io.shirohoo.creational.abstract_factory.domain.jdbc;

import io.shirohoo.creational.abstract_factory.domain.model.Lecture;

public interface LectureRepository {
    void insert(final Lecture lecture);
    void select(final Lecture lecture);
    void update(final Lecture lecture);
    void delete(final Lecture lecture);
}
