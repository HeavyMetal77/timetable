package ua.tarastom.timetable.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "поле не може бути порожнім")
    @Size(min=2, max=50, message = "Кількість знаків повинна бути від 2 до 50")
    @Column(name ="first_name")
    private String firstName;

    @NotNull(message = "поле не може бути порожнім")
    @Size(min=4, max=50, message = "Кількість знаків повинна бути від 4 до 50")
    @Column(name ="middle_name")
    private String middleName;

    @NotNull(message = "поле не може бути порожнім")
    @Size(min=2, max=50, message = "Кількість знаків повинна бути від 2 до 50")
    @Column(name ="last_name")
    private String lastName;

    @Column(name ="email")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="teacher",
            cascade= {CascadeType.ALL})
    private List<SubjectIntMap> subjectIntMaps;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="teacher",
            cascade= {CascadeType.ALL})
    private List<Lesson> lessons;

    @Digits(integer=2, fraction=0, message = "Не більше 2-х цифр")
    @Column(name ="total_hours_allocated")
    private int totalHoursAllocated;

    public Teacher() {
        lessons = new ArrayList<>();
        subjectIntMaps = new ArrayList<>();
    }

    public Teacher(String firstName, String middleName, String lastName, String email, List<SubjectIntMap> subjectIntMaps, int totalHoursAllocated) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.subjectIntMaps = subjectIntMaps;
        this.totalHoursAllocated = totalHoursAllocated;
    }

    public Teacher(int id, String firstName, String middleName, String lastName, String email, List<Lesson> lessons, int totalHoursAllocated) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.lessons = lessons;
        this.totalHoursAllocated = totalHoursAllocated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<SubjectIntMap> getSubjectIntMaps() {
        return subjectIntMaps;
    }

    public void setSubjectIntMaps(List<SubjectIntMap> subjectIntMaps) {
        subjectIntMaps.forEach(subjectIntMap -> subjectIntMap.setTeacher(this));
        this.subjectIntMaps = subjectIntMaps;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                id +
                ", " + firstName +
                ", " + middleName +
                ", " + lastName +
                ", " + email +
                "\n, " + lessons +
                "\n, " + subjectIntMaps +
                '}';
    }

    public int getTotalHoursAllocated() {
        return totalHoursAllocated;
    }

    public void setTotalHoursAllocated(int totalHoursAllocated) {
        this.totalHoursAllocated = totalHoursAllocated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id &&
                totalHoursAllocated == teacher.totalHoursAllocated &&
                firstName.equals(teacher.firstName) &&
                middleName.equals(teacher.middleName) &&
                lastName.equals(teacher.lastName) &&
                Objects.equals(email, teacher.email) &&
                Objects.equals(subjectIntMaps, teacher.subjectIntMaps) &&
                Objects.equals(lessons, teacher.lessons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, email, subjectIntMaps, lessons, totalHoursAllocated);
    }
}