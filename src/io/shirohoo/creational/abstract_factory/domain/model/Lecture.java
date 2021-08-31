package io.shirohoo.creational.abstract_factory.domain.model;

import java.util.Objects;

public class Lecture {
    private final Long id;
    private final String teacherName;
    private final String title;

    private Lecture(final Long id, final String teacherName, final String title) {
        this.id = id;
        this.teacherName = teacherName;
        this.title = title;
    }

    public static Lecture of(final Long id, final String teacherName, final String title) {
        return new Lecture(id, teacherName, title);
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        final Lecture lecture = (Lecture) o;
        return Objects.equals(id, lecture.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", teacherName='" + teacherName + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
