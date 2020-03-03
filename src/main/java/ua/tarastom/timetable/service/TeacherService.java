package ua.tarastom.timetable.service;

import ua.tarastom.timetable.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeachers();

    Teacher findTeacherById(int theId);

    void saveTeacher(Teacher theTeacher);

    void deleteTeacher(Teacher theTeacher);

    void deleteById(int theId);

    long getCountTeachers();

}
