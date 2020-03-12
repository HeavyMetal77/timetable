package ua.tarastom.timetable.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "school_class")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "поле не може бути порожнім")
    @Column(name = "name_class", unique = true)
    private String nameClass;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolClass", cascade = {CascadeType.ALL})
    private List<Lesson> lessons;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    private List<SubjectIntMap> subjectIntMap;

    public SchoolClass() {
    }

    public SchoolClass(int id, String nameClass, List<Lesson> lessons, List<SubjectIntMap> subjectIntMap) {
        this.id = id;
        this.nameClass = nameClass;
        this.lessons = lessons;
        this.subjectIntMap = subjectIntMap;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<SubjectIntMap> getSubjectIntMap() {
        return subjectIntMap;
    }

    public void setSubjectIntMap(List<SubjectIntMap> subjectIntMap) {
        this.subjectIntMap = subjectIntMap;
    }

    @Override
    public String toString() {
        return nameClass;
    }
}
