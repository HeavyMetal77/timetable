package ua.tarastom.timetable.service;

import org.springframework.stereotype.Service;
import ua.tarastom.timetable.dao.TeacherRepository;
import ua.tarastom.timetable.dao.TimeTableDAO;
import ua.tarastom.timetable.entity.Teacher;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TimeTableDAO timeTableDAO;
    private TeacherRepository teacherRepository;

    public TeacherServiceImpl(TimeTableDAO timeTableDAO, TeacherRepository teacherRepository) {
        this.timeTableDAO = timeTableDAO;
        this.teacherRepository = teacherRepository;
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
        teacherRepository.save(theTeacher);
    }

    @Override
    public void deleteTeacher(Teacher theTeacher) {
        timeTableDAO.deleteTeacher(theTeacher);
    }

    @Override
    public void deleteById(int theId) {
        teacherRepository.deleteById(theId);
    }
}
