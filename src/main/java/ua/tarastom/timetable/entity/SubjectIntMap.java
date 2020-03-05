package ua.tarastom.timetable.entity;

import javax.persistence.*;

/**
 * Клас зберігає поля
 * клас,
 * предмет
 * кількість годин, виділених на предмет в певному класі
 * id вчителя
 */

@Entity
@Table(name = "subject_int_map")
public class SubjectIntMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "value")
    private int value;

    public SubjectIntMap() {
    }

    public SubjectIntMap(int id, SchoolClass schoolClass, Subject subject, int value) {
        this.id = id;
        this.schoolClass = schoolClass;
        this.subject = subject;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
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
        return schoolClass.getNameClass() +
                "-клас, " + subject.getNameSubject() +
                ", " + value + " годин";
    }
}
