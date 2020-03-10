package ua.tarastom.timetable.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.tarastom.timetable.entity.SchoolClass;
import ua.tarastom.timetable.entity.Subject;
import ua.tarastom.timetable.entity.SubjectIntMap;
import ua.tarastom.timetable.entity.Teacher;
import ua.tarastom.timetable.service.SchoolClassService;
import ua.tarastom.timetable.service.SubjectIntMapService;
import ua.tarastom.timetable.service.SubjectService;
import ua.tarastom.timetable.service.TeacherService;
import ua.tarastom.timetable.util.TimetableUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

    private TeacherService teacherService;
    private SubjectService subjectService;
    private TimetableUtils timetableUtils;
    private SubjectIntMapService subjectIntMapService;
    private SchoolClassService schoolClassService;

    public TimetableController(TeacherService teacherService, SubjectService subjectService,
                               TimetableUtils timetableUtils, SubjectIntMapService subjectIntMapService,
                               SchoolClassService schoolClassService) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.timetableUtils = timetableUtils;
        this.subjectIntMapService = subjectIntMapService;
        this.schoolClassService = schoolClassService;
    }

    @RequestMapping("/list-teachers")
    public String listTeachers(Model model) {
        List<Teacher> teacherList = teacherService.getAllTeachers();
        model.addAttribute("teacherList", teacherList);
        return "teacher/list-teachers";
    }

    @PostMapping("/saveSubject")
    public String saveSubject(@ModelAttribute("subject") Subject subject) {
        subjectService.saveSubject(subject);
        return "redirect:list-subjects";
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

    @RequestMapping("/showFormForAddSubject")
    public String showFormForAddSubject(Model model) {
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "subject/add-subject";
    }

    @RequestMapping("/showFormForAddTeacher")
    public String showFormForAddTeacher(Model model) {
        Teacher theTeacher = new Teacher();
        List<SchoolClass> allSchoolClasses = schoolClassService.getAllSchoolClasses();
        List<Subject> allSubjects = subjectService.getAllSubjects();
        theTeacher.getSubjectIntMaps().add(createEmptySubjectIntMap(theTeacher));
        model.addAttribute("allSchoolClasses", allSchoolClasses);
        model.addAttribute("allSubjects", allSubjects);
        model.addAttribute("teacher", theTeacher);
        return "teacher/add-teacher";
    }

    //для обрезки пробелов (препроцессор)
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@Valid @ModelAttribute("teacher") Teacher theTeacher, BindingResult bindingResult, Model model,
                              @Valid String[] selectClass, @Valid String[] selectSubject,
                              @RequestParam(value="action") String action) {
        List<Subject> allSubjects = subjectService.getAllSubjects();
        List<SchoolClass> allSchoolClasses = schoolClassService.getAllSchoolClasses();

        if (bindingResult.hasErrors()) {
            theTeacher.getSubjectIntMaps().add(createEmptySubjectIntMap(theTeacher));
            model.addAttribute("allSchoolClasses", allSchoolClasses);
            model.addAttribute("allSubjects", allSubjects);
            model.addAttribute("teacher", theTeacher);
            return "teacher/add-teacher";
        }

        if (action.equals("addRow")) {
                List<SubjectIntMap> subjectIntMaps = new ArrayList<>();
                for (int i = 0; i < selectClass.length; i++) {
                    if (!selectClass[i].equals("") || !(selectSubject[i].equals(""))) {
                        subjectIntMaps.add(new SubjectIntMap());
                        subjectIntMaps.get(i).setSubject(allSubjects.get(Integer.parseInt(selectSubject[i])-1));
                        subjectIntMaps.get(i).getSubject().setSchoolClass(allSchoolClasses.get(Integer.parseInt(selectClass[i])-1));
                    }
                }
                subjectIntMaps.add(createEmptySubjectIntMap(theTeacher));
                theTeacher.setSubjectIntMaps(subjectIntMaps);
                model.addAttribute("allSchoolClasses", allSchoolClasses);
                model.addAttribute("allSubjects", allSubjects);
                model.addAttribute("teacher", theTeacher);
                return "teacher/add-teacher";
            }

            if (action.startsWith("deleteRow")) {
                String substring = action.substring(9);
                int rowIdInt = Integer.parseInt(substring);
                if ((rowIdInt == 0 && (selectClass.length == 1 || selectSubject.length == 1)) ||
                        (rowIdInt == 0 && (selectClass.length == 0 || selectSubject.length == 0))) {
                    theTeacher.getSubjectIntMaps().add(createEmptySubjectIntMap(theTeacher));
                } else {
                    for (int i = 0; i < selectClass.length; i++) {
                        if (i != rowIdInt) {
                            SubjectIntMap subjectIntMap = createEmptySubjectIntMap(theTeacher);
                            if (!selectSubject[i].equals("") || !(selectClass[i].equals(""))) {
                                subjectIntMap.setSubject(allSubjects.get(Integer.parseInt(selectSubject[i]) - 1));
                                subjectIntMap.getSubject().setSchoolClass(allSchoolClasses.get(Integer.parseInt(selectClass[i])-1));
                            } else {
                                subjectIntMap = createEmptySubjectIntMap(theTeacher);
                            }
                            theTeacher.getSubjectIntMaps().add(subjectIntMap);
                        }
                    }
                }
                model.addAttribute("allSchoolClasses", allSchoolClasses);
                model.addAttribute("allSubjects", allSubjects);
                model.addAttribute("teacher", theTeacher);
                return "teacher/add-teacher";
            }

        if (action.equals("save")) {
            if (theTeacher.getId() != 0) {
                teacherService.deleteById(theTeacher.getId());
            }
            for (int i = 0; i < selectClass.length; i++) {
                SubjectIntMap subjectIntMap = null;
                    if (!selectSubject[i].equals("") || !(selectClass[i].equals(""))) {
                        subjectIntMap = createEmptySubjectIntMap(theTeacher);
                        subjectIntMap.setSubject(allSubjects.get(Integer.parseInt(selectSubject[i]) - 1));
                        subjectIntMap.getSubject().setSchoolClass(allSchoolClasses.get(Integer.parseInt(selectClass[i])-1));
                    }
                if (subjectIntMap != null) {
                    theTeacher.getSubjectIntMaps().add(subjectIntMap);
                }
            }
            teacherService.saveTeacher(theTeacher);
        }
        return "redirect:list-teachers";
    }

    @GetMapping("/showFormForUpdateTeacher")
    public String showFormForUpdateTeacher(@RequestParam("teacherId") int teacherId, Model model) {
        Teacher theTeacher = teacherService.findTeacherById(teacherId);
        if (theTeacher.getSubjectIntMaps().size() == 0) {
            theTeacher.getSubjectIntMaps().add(createEmptySubjectIntMap(theTeacher));
        }
        List<Subject> allSubjects = subjectService.getAllSubjects();
        List<SchoolClass> allSchoolClasses = schoolClassService.getAllSchoolClasses();
        model.addAttribute("allSchoolClasses", allSchoolClasses);
        model.addAttribute("allSubjects", allSubjects);
        model.addAttribute("teacher", theTeacher);
        return "teacher/add-teacher";
    }

    @RequestMapping("/test-list-need-teacher")
    public String listNeedTeacher(Model model) {
        Subject subject = subjectIntMapService.getAllSubjectIntMaps().get(0).getSubject();
        List<Teacher> teacherList = timetableUtils.getTeacher(teacherService.getAllTeachers(), subject, 12);
        model.addAttribute("teacherList", teacherList);
        return "teacher/list-teachers";
    }

    private SubjectIntMap createEmptySubjectIntMap(Teacher theTeacher) {
        Subject subject = new Subject();
        SchoolClass schoolClass = new SchoolClass();
        subject.setSchoolClass(schoolClass);
        SubjectIntMap subjectIntMap = new SubjectIntMap();
        subjectIntMap.setSubject(subject);
        subjectIntMap.setTeacher(theTeacher);
        return subjectIntMap;
    }
}
