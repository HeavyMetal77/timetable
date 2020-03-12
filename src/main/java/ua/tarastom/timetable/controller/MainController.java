package ua.tarastom.timetable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.tarastom.timetable.service.SchoolClassService;
import ua.tarastom.timetable.service.SubjectService;
import ua.tarastom.timetable.service.TeacherService;

@Controller
@RequestMapping()
public class MainController {
    private TeacherService teacherService;
    private SubjectService subjectService;
    private SchoolClassService schoolClassService;

    public MainController(TeacherService teacherService, SubjectService subjectService, SchoolClassService schoolClassService) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.schoolClassService = schoolClassService;
    }

    @RequestMapping("/")
    public String showIndex(Model model) {
        long countTeachers = teacherService.getCountTeachers();
        long countSubjects = subjectService.getCountSubjects();
        long countSchoolClasses = schoolClassService.getCountSchoolClasses();
        model.addAttribute("countSchoolClasses", countSchoolClasses);
        model.addAttribute("countTeachers", countTeachers);
        model.addAttribute("countSubjects", countSubjects);
        return "index";
    }
}
