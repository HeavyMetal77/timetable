package ua.tarastom.timetable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.tarastom.timetable.service.TeacherService;

@Controller
@RequestMapping()
public class MainController {
    private TeacherService teacherService;

    public MainController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping("/")
    public String showIndex(Model model) {
        long countTeachers = teacherService.getCountTeachers();
        model.addAttribute("countTeachers", countTeachers);
        return "index";
    }
}
