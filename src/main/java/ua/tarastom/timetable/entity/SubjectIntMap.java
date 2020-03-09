package ua.tarastom.timetable.entity;

import javax.persistence.*;

@Entity
@Table(name = "subject_int_map")
public class SubjectIntMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "value")
    private int value;

    public SubjectIntMap() {
    }

    public SubjectIntMap(Subject subject, Teacher teacher) {
        this.subject = subject;
        this.teacher = teacher;
    }

    public SubjectIntMap(int value, Subject subject, Teacher teacher) {
        this.subject = subject;
        this.teacher = teacher;
        this.value = value;
    }

    public SubjectIntMap(int id, Subject subject, int value) {
        this.id = id;
        this.subject = subject;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return subject.getNameSubject() +
                ", " + value + " годин";
    }
}
