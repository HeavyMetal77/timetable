package ua.tarastom.timetable.entity;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_subject")
    private String nameSubject;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;

    public Subject() {
    }

    public Subject(int id, String nameSubject, SchoolClass schoolClass) {
        this.id = id;
        this.schoolClass = schoolClass;
        this.nameSubject = nameSubject;
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

    @Override
    public String toString() {
        return "Subject{" +
                schoolClass +
                ", " + nameSubject +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
