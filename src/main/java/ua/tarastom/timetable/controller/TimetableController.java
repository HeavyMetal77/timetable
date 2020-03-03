package ua.tarastom.timetable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        return "teacher/list-teachers";
    }

    @RequestMapping("/showFormForAddTeacher")
    public String showFormForAddTeacher(Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "teacher/add-teacher";
    }

    @PostMapping("/save")
    public String saveTeacher(@ModelAttribute ("teacher") Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return "redirect:list-teachers";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {
        teacherService.deleteById(theId);
        return "redirect:list-teachers";
    }
}
