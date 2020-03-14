package ua.tarastom.timetable.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "поле не може бути порожнім")
    @Column(name = "name_subject")
    private String nameSubject;

    @NotNull(message = "поле не може бути порожнім")
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;

    private int valueHours;

    public Subject() {
    }

    public Subject(int id, String nameSubject, SchoolClass schoolClass, int valueHours) {
        this.id = id;
        this.schoolClass = schoolClass;
        this.nameSubject = nameSubject;
        this.valueHours = valueHours;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValueHours() {
        return valueHours;
    }

    public void setValueHours(int valueHours) {
        this.valueHours = valueHours;
    }

    @Override
    public String toString() {
        return "Subject{" +
                schoolClass +
                ", " + nameSubject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;

        return subject.getNameSubject().equals(this.getNameSubject())
                &&subject.getSchoolClass().getNameClass().equals(this.getSchoolClass().getNameClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameSubject, schoolClass);
    }
}
