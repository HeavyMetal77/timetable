package ua.tarastom.timetable.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "school_class")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_class")
    private String nameClass;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="schoolClass",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Lesson> lessons;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="schoolClass",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Subject> subjects;

//    @Type( type = "SerializableType")
//    private Map<Subject, Integer> subjectIntegerMap;

    public SchoolClass() {
    }

    public SchoolClass(String nameClass) {
        this.nameClass = nameClass;
//        this.subjectIntegerMap = subjectIntegerMap;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

//    public Map<Subject, Integer> getSubjectIntegerMap() {
//        return subjectIntegerMap;
//    }
//
//    public void setSubjectIntegerMap(Map<Subject, Integer> subjectIntegerMap) {
//        this.subjectIntegerMap = subjectIntegerMap;
//    }
//
//    public int getHoursForSubject(Subject subject) {
//        int result = -1;
//        for (Map.Entry<Subject, Integer> entry : subjectIntegerMap.entrySet()) {
//            if (subject.equals(entry.getKey())) {
//                result = entry.getValue();
//            }
//        }
//        return result;
//    }
//
//    public void putSubjectInteger(Subject subject, Integer value) {
//        subjectIntegerMap.put(subject, value);
//    }


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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "nameClass='" + nameClass + '\'' +
                '}';
    }
}
