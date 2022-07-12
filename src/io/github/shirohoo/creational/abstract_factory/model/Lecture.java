package io.github.shirohoo.creational.abstract_factory.model;

import java.util.Objects;

public class Lecture {
    private final Long id;
    private final String teacherName;
    private final String title;

    private Lecture(Long id, String teacherName, String title) {
        this.id = id;
        this.teacherName = teacherName;
        this.title = title;
    }

    public static Lecture of(Long id, String teacherName, String title) {
        return new Lecture(id, teacherName, title);
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lecture lecture = (Lecture) o;
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
