package ua.tarastom.timetable.entity;

import javax.persistence.*;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number_lesson")
    private int numberLesson;

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name ="day_week_id")
    private DayOfWeekUtil dayOfWeek;

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

    public Lesson(int id, int numberLesson, DayOfWeekUtil dayOfWeek, Subject subject, SchoolClass schoolClass, Teacher teacher, Classroom classroom) {
        this.id = id;
        this.numberLesson = numberLesson;
        this.dayOfWeek = dayOfWeek;
        this.subject = subject;
        this.schoolClass = schoolClass;
        this.teacher = teacher;
        this.classroom = classroom;
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


    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public DayOfWeekUtil getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeekUtil dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "\nnumberLesson=" + numberLesson +
                ", dayOfWeek=" + dayOfWeek +
                ", \nsubject=" + subject +
                ", \nschoolClass=" + schoolClass +
                '}' + "\n";
    }
}
