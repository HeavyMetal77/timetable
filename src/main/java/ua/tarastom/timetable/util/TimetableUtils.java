package ua.tarastom.timetable.util;

import ua.tarastom.timetable.entity.SubjectIntMap;
import ua.tarastom.timetable.entity.Teacher;

import java.util.List;

public interface TimetableUtils {

    //методи для обробки даних
    List<Teacher> getTeacher(List<Teacher> teacherList, SubjectIntMap subjectIntMap);


}
