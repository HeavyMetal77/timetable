package ua.tarastom.timetable.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class_room")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name ="name_classroom")
    private String nameClassRoom;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="classroom",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Lesson> lessons;

    public Classroom() {
        lessons = new ArrayList<>();
    }

    public Classroom(int id, String nameClassRoom, List<Lesson> lessons) {
        this.id = id;
        this.nameClassRoom = nameClassRoom;
        this.lessons = lessons;
    }

    public String getNameClassRoom() {
        return nameClassRoom;
    }

    public void setNameClassRoom(String nameClassRoom) {
        this.nameClassRoom = nameClassRoom;
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

    @Override
    public String toString() {
        return "Classroom{" + nameClassRoom + "}";
    }
}
