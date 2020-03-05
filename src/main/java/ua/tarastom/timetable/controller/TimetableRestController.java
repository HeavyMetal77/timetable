package ua.tarastom.timetable.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tarastom.timetable.entity.Teacher;
import ua.tarastom.timetable.service.SubjectIntMapService;
import ua.tarastom.timetable.service.TeacherService;
import ua.tarastom.timetable.util.TimetableUtils;

import java.util.List;

@RestController
@RequestMapping("/timetable")
public class TimetableRestController {

    private final TimetableUtils timetableUtils;
    private final TeacherService teacherService;
    private final SubjectIntMapService subjectIntMapService;

    public TimetableRestController(TimetableUtils timetableUtils, TeacherService teacherService, SubjectIntMapService subjectIntMapService) {
        this.timetableUtils = timetableUtils;
        this.teacherService = teacherService;
        this.subjectIntMapService = subjectIntMapService;
    }

    @GetMapping("/hello")
    public String hello() {
        List<Teacher> teacherList = teacherService.getAllTeachers();
        return teacherList.toString();
    }
}
