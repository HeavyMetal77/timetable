package ua.tarastom.timetable.dao;

import ua.tarastom.timetable.entity.*;

import java.util.List;

public interface TimeTableDAO {
    //методи для роботи з БД
    List<Teacher> getTeacherList();

    Teacher findTeacherById(int theId);

    void deleteTeacher(Teacher theTeacher);

    List<Subject> getSubjectList();

    List<SchoolClass> getSchoolClassList();

    List<Lesson> getLessonList();

    List<DayOfWeekUtil> getDayOfWeekList();

    List<SubjectIntMap> getSubjectIntMapList();
}
