package ua.tarastom.timetable.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tarastom.timetable.dao.TimeTableDAO;
import ua.tarastom.timetable.entity.SubjectIntMap;
import ua.tarastom.timetable.entity.Teacher;
import ua.tarastom.timetable.service.TeacherService;
import ua.tarastom.timetable.util.TimetableUtils;

import java.util.List;

@RestController
@RequestMapping("/timetable")
public class TimetableRestController {

    private final TimeTableDAO timeTableDAO;
    private final TimetableUtils timetableUtils;
    private final TeacherService teacherService;

    public TimetableRestController(TimeTableDAO timeTableDAO, TimetableUtils timetableUtils, TeacherService teacherService) {
        this.timeTableDAO = timeTableDAO;
        this.timetableUtils = timetableUtils;
        this.teacherService = teacherService;
    }

    @GetMapping("/hello")
    public String hello() {
        List<Teacher> teacherList = teacherService.getAllTeachers();
        SubjectIntMap subjectIntMap = timeTableDAO.getSubjectIntMapList().get(3);
        List<Teacher> teachers = timetableUtils.getTeacher(teacherList, subjectIntMap);
        return teachers.toString();
    }
}
