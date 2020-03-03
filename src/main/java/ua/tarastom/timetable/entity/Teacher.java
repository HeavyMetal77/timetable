package ua.tarastom.timetable.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="middle_name")
    private String middleName;

    @Column(name ="last_name")
    private String lastName;

    @Column(name ="email")
    private String email; //TODO реалізувати функцію відправки кожному вчителю розкладу занять на емейл

    //TODO перевірити правильність каскадного видалення (всіх каскадних операцій) для пов'язаних об'єктів
    @OneToMany(fetch = FetchType.LAZY, mappedBy="teacher",
            cascade= {CascadeType.ALL})
    private List<SubjectIntMap> subjectIntMaps;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="teacher",
            cascade= {CascadeType.ALL})
    private List<Lesson> lessons;

    public Teacher() {
        lessons = new ArrayList<>();
    }

    public Teacher(int id, String firstName, String middleName, String lastName, String email, List<Lesson> lessons) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.lessons = lessons;
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
}

/*
если IDEA подчеркивает красным аннотированный класс (from Student) ("Cannot resolve simbol")
- https://ru.stackoverflow.com/questions/763151/%D0%A0%D0%B0%D0%B1%D0%BE%D1%82%D0%B0-%D1%81-hql-hibernate

Скорее всего у вас не включена интеграции идеи и БД. Идея подчеркивает потому,
что ничего не знает про схему вашей базы.
В левом столбце снизу (левее дерева файлов) у вас должны быть вкладка Persistence.
Откройте её, там должно быть имя ваше БД. ПКМ на ней и "Assign Data Sources..."
В открывшемся окне слева надо указать коннект к БД и сохранить (Если коннекта нет - справа на вкладке под мавеном
Database - подключить соединение к базе, тогда появится)
Если вкладки Persistence нет, тогда :
Ctrl + Shift + Alt + S
Facets
добавить jpa и внизу выбрать Default JPA provider - Hibernate
По факту даже не делая этого у вас все должно работать, если все сделали правильно.
Т.к. это ошибка интеграции Intellij IDEA а не проекта
 */