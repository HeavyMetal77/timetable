package ua.tarastom.timetable.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tarastom.timetable.entity.Teacher;
import ua.tarastom.timetable.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/timetable")
public class TimetableRestController {

    private final TeacherService teacherService;

    public TimetableRestController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/hello")
    public String hello() {
        List<Teacher> teacherList = teacherService.getAllTeachers();
        return teacherList.toString();
    }
}
