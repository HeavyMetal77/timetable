package ua.tarastom.timetable.entity;

import javax.persistence.*;

@Entity
@Table(name = "day_of_week")
public class DayOfWeekUtil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "day_week")
    private String dayOfWeek;

    public DayOfWeekUtil() {
    }

    public DayOfWeekUtil(int id, String dayOfWeek) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public String toString() {
        return "DayOfWeekUtil{" +
                "id=" + id +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                '}';
    }
}
