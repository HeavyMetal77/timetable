package ua.tarastom.timetable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.tarastom.timetable.entity.Subject;
import ua.tarastom.timetable.entity.Teacher;
import ua.tarastom.timetable.service.SubjectService;
import ua.tarastom.timetable.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

    private TeacherService teacherService;
    private SubjectService subjectService;

    public TimetableController(TeacherService teacherService, SubjectService subjectService) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
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

    @RequestMapping("/showFormForAddSubject")
    public String showFormForAddSubject(Model model) {
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "subject/add-subject";
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute ("teacher") Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return "redirect:list-teachers";
    }

    @PostMapping("/saveSubject")
    public String saveSubject(@ModelAttribute ("subject") Subject subject) {
        subjectService.saveSubject(subject);
        return "redirect:list-subjects";
    }

    @GetMapping("/showFormForUpdateTeacher")
    public String showFormForUpdateTeacher(@RequestParam("teacherId") int teacherId, Model model) {
        Teacher theTeacher = teacherService.findTeacherById(teacherId);
        model.addAttribute("teacher", theTeacher);
        return "teacher/add-teacher";
    }

    @GetMapping("/showFormForUpdateSubject")
    public String showFormForUpdateSubject(@RequestParam("subjectId") int subjectId, Model model) {
        Subject theSubject = subjectService.findSubjectById(subjectId);
        model.addAttribute("subject", theSubject);
        return "subject/add-subject";
    }

    @GetMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam("teacherId") int theId) {
        teacherService.deleteById(theId);
        return "redirect:list-teachers";
    }

    @GetMapping("/deleteSubject")
    public String deleteSubject(@RequestParam("subjectId") int theId) {
        subjectService.deleteById(theId);
        return "redirect:list-subjects";
    }

    @RequestMapping("/list-subjects")
    public String listSubjects(Model model) {
        List<Subject> allSubjects = subjectService.getAllSubjects();
        model.addAttribute("allSubjects", allSubjects);
        return "subject/list-subjects";
    }
}
