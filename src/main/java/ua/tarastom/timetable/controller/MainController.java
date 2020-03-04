package ua.tarastom.timetable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.tarastom.timetable.service.SubjectService;
import ua.tarastom.timetable.service.TeacherService;

@Controller
@RequestMapping()
public class MainController {
    private TeacherService teacherService;
    private SubjectService subjectService;

    public MainController(TeacherService teacherService, SubjectService subjectService) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
    }

    @RequestMapping("/")
    public String showIndex(Model model) {
        long countTeachers = teacherService.getCountTeachers();
        long countSubjects = subjectService.getCountSubjects();
        model.addAttribute("countTeachers", countTeachers);
        model.addAttribute("countSubjects", countSubjects);
        return "index";
    }
}
