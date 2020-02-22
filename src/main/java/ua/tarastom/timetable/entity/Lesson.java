package ua.tarastom.timetable.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Month;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number_lesson")
    private int numberLesson;

    @Column(name ="date_time")
    private String dateTime;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name ="subject_id")
    private Subject subject;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    public Lesson() {

    }

    public Lesson(int id, int numberLesson, String dateTime, Subject subject, SchoolClass schoolClass, Teacher teacher, Classroom classroom) {
        this.id = id;
        this.numberLesson = numberLesson;
        this.dateTime = dateTime;
        this.subject = subject;
        this.schoolClass = schoolClass;
        this.teacher = teacher;
        this.classroom = classroom;
    }

    public Lesson(Subject subject, Teacher teacher, SchoolClass schoolClass) {
        this.subject = subject;
        this.schoolClass = schoolClass;
        this.teacher = teacher;
        dateTime = (LocalDateTime.of(2020, Month.JANUARY, 30, 15, 0, 0)).toString();
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public int getNumberLesson() {
        return numberLesson;
    }

    public void setNumberLesson(int numberLesson) {
        this.numberLesson = numberLesson;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "numberLesson=" + numberLesson +
                ", dateTime=" + dateTime +
                ", subject=" + subject +
                ", schoolClass=" + schoolClass +
                '}' + "\n";
    }
}
