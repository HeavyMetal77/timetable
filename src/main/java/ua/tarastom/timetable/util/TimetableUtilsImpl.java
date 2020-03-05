package ua.tarastom.timetable.util;

import org.springframework.stereotype.Component;
import ua.tarastom.timetable.entity.Subject;
import ua.tarastom.timetable.entity.SubjectIntMap;
import ua.tarastom.timetable.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

@Component
public class TimetableUtilsImpl implements TimetableUtils {

    @Override
    public List<Teacher> getTeacher(List<Teacher> teacherList, Subject theSubject, int value) {
        List<Teacher> teachers = new ArrayList<>();
        for (Teacher teacher : teacherList) {
            List<SubjectIntMap> subjectIntMaps = teacher.getSubjectIntMaps();
            for (SubjectIntMap map : subjectIntMaps) {
                Subject subject = map.getSubject();
                int value1 = map.getValue();
                if (theSubject.equals(subject) && value1 < value) {
                    teachers.add(teacher);
                }
            }
        }
        return teachers;
        //повертає список вчителів, які можуть викладати даний предмет - subjectIntMap.getSubject()
        //без врахування кількості годин

        //use Stream API
//        teacherList.stream().filter(teacher -> teacher.getSubjectIntMaps().get(subjectIntMap.getSubject())== subjectIntMap.getSubject());
    }
}
