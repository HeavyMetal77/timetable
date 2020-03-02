package ua.tarastom.timetable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.tarastom.timetable.entity.Teacher;
import ua.tarastom.timetable.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

    private TeacherService teacherService;

    public TimetableController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping("/list-teachers")
    public String listTeachers(Model model) {
        List<Teacher> teacherList = teacherService.getAllTeachers();
        model.addAttribute("teacherList", teacherList);
        return "list-teachers";
    }
}
