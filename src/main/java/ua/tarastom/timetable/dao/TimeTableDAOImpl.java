package ua.tarastom.timetable.dao;

import org.springframework.stereotype.Component;
import ua.tarastom.timetable.entity.*;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class TimeTableDAOImpl implements TimeTableDAO {

    private final EntityManager entityManager;

    public TimeTableDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Teacher> getTeacherList() {
        return entityManager.createQuery("SELECT l from Teacher l order by l.id").getResultList();
    }

    @Override
    public List<Subject> getSubjectList() {
        return entityManager.createQuery("SELECT l from Subject l order by l.id").getResultList();
    }

    @Override
    public List<SchoolClass> getSchoolClassList() {
        return entityManager.createQuery("SELECT l from SchoolClass l order by l.id").getResultList();
    }

    @Override
    public List<Lesson> getLessonList() {
        return entityManager.createQuery("SELECT l from Lesson l order by l.dayOfWeek.id").getResultList();
    }

    @Override
    public List<DayOfWeekUtil> getDayOfWeekList() {
        return entityManager.createQuery("SELECT l from DayOfWeekUtil l order by l.id").getResultList();
    }

    @Override
    public List<SubjectIntMap> getSubjectIntMapList() {
        return entityManager.createQuery("SELECT l from SubjectIntMap l order by l.id").getResultList();
    }
}
