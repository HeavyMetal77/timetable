package ua.tarastom.timetable.service;

import org.springframework.stereotype.Service;
import ua.tarastom.timetable.dao.TeacherRepository;
import ua.tarastom.timetable.entity.Teacher;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Teacher findTeacherById(int theId) {
        Optional<Teacher> tempTeacher = teacherRepository.findById(theId);
        return tempTeacher.get();
    }

    @Override
    public void saveTeacher(Teacher theTeacher) {
        teacherRepository.save(theTeacher);
    }

    @Override
    public void deleteTeacher(Teacher theTeacher) {
        teacherRepository.delete(theTeacher);
    }

    @Override
    public void deleteById(int theId) {
        teacherRepository.deleteById(theId);
    }

    @Override
    public long getCountTeachers() {
        return teacherRepository.count();
    }
}
