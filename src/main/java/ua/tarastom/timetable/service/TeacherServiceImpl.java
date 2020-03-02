package ua.tarastom.timetable.service;

import org.springframework.stereotype.Service;
import ua.tarastom.timetable.dao.TimeTableDAO;
import ua.tarastom.timetable.entity.Teacher;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TimeTableDAO timeTableDAO;

    public TeacherServiceImpl(TimeTableDAO timeTableDAO) {
        this.timeTableDAO = timeTableDAO;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return timeTableDAO.getTeacherList();
    }

    @Override
    public Teacher findTeacherById(int theId) {
        return timeTableDAO.findTeacherById(theId);
    }

    @Override
    public void saveTeacher(Teacher theTeacher) {
        timeTableDAO.saveTeacher(theTeacher);
    }

    @Override
    public void deleteTeacher(Teacher theTeacher) {
        timeTableDAO.deleteTeacher(theTeacher);
    }
}
