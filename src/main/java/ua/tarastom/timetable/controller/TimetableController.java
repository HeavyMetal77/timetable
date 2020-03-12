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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

    private TimetableUtils timetableUtils;
    private TeacherService teacherService;
    private SubjectService subjectService;
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
    public String saveSubject(@ModelAttribute("subject") Subject subject, Model model) {
        List<SchoolClass> allSchoolClasses = schoolClassService.getAllSchoolClasses();
        if (subject.getSchoolClass().getNameClass() == null || subject.getNameSubject() == null) {
            model.addAttribute("allSchoolClasses", allSchoolClasses);
            return "subject/add-subject";
        }
        subjectService.saveSubject(subject);
        return "redirect:list-subjects";
    }

    @GetMapping("/showFormForUpdateSubject")
    public String showFormForUpdateSubject(@RequestParam("subjectId") int subjectId, Model model) {
        Subject theSubject = subjectService.findSubjectById(subjectId);
        List<SchoolClass> allSchoolClasses = schoolClassService.getAllSchoolClasses();
        model.addAttribute("allSchoolClasses", allSchoolClasses);
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

    @GetMapping("/deleteSchoolClass")
    public String deleteSchoolClass(@RequestParam("schoolClassId") int theId) {
        schoolClassService.deleteById(theId);
        return "redirect:list-schoolClasses";
    }

    @RequestMapping("/list-subjects")
    public String listSubjects(Model model) {
        List<Subject> allSubjects = subjectService.getAllSubjects();
        model.addAttribute("allSubjects", allSubjects);
        return "subject/list-subjects";
    }
    @RequestMapping("/list-schoolClasses")
    public String listSchoolClasses(Model model) {
        List<SchoolClass> allSchoolClasses = schoolClassService.getAllSchoolClasses();
        model.addAttribute("allSchoolClasses", allSchoolClasses);
        return "schoolClass/list-schoolClasses";
    }

    @RequestMapping("/showFormForAddSubject")
    public String showFormForAddSubject(Model model) {
        Subject subject = new Subject();
        List<SchoolClass> allSchoolClasses = schoolClassService.getAllSchoolClasses();
        model.addAttribute("allSchoolClasses", allSchoolClasses);
        model.addAttribute("subject", subject);
        return "subject/add-subject";
    }

    @RequestMapping("/showFormForAddSchoolClass")
    public String showFormForAddSchoolClass(Model model) {
        SchoolClass schoolClass = new SchoolClass();
        model.addAttribute("schoolClass", schoolClass);
        return "schoolClass/add-schoolClass";
    }

    @RequestMapping("/showFormForUpdateSchoolClass")
    public String showFormForUpdateSchoolClass(@RequestParam("schoolClassId") int schoolClassId, Model model) {
        SchoolClass schoolClassById = schoolClassService.getSchoolClassById(schoolClassId);
        model.addAttribute("schoolClass", schoolClassById);
        return "schoolClass/add-schoolClass";
    }

    @RequestMapping("/showFormForAddTeacher")
    public String showFormForAddTeacher(Model model) {
        Teacher theTeacher = new Teacher();
        List<SchoolClass> allSchoolClasses = schoolClassService.getAllSchoolClasses();
        List<Subject> allSubjects = subjectService.getAllSubjects();
        theTeacher.getSubjectIntMaps().add(createEmptySubjectIntMap(theTeacher));
        setModelAttributes(model, theTeacher, allSubjects, allSchoolClasses);
        return "teacher/add-teacher";
    }

    //для обрезки пробелов (препроцессор)
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/saveSchoolClass")
    public String saveSchoolClass(@Valid @ModelAttribute("schoolClass") SchoolClass theSchoolClass,
                                  BindingResult bindingResult) {
        List<SchoolClass> allSchoolClasses = schoolClassService.getAllSchoolClasses();
        for (SchoolClass tempSchoolClass : allSchoolClasses) {
            if (tempSchoolClass.getNameClass().equals(theSchoolClass.getNameClass())) {
                bindingResult.rejectValue("nameClass", "not_unique_schoolClass", "Клас з такою назвою вже існує!");
                break;
            }
        }
        if (bindingResult.hasErrors()) {
            return "schoolClass/add-schoolClass";
        }
        schoolClassService.saveSchoolClass(theSchoolClass);
        return "redirect:list-schoolClasses";
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@Valid @ModelAttribute("teacher") Teacher theTeacher, BindingResult bindingResult,
                              Model model, @Valid String[] selectClass, @Valid String[] selectSubject,
                              @RequestParam(value = "action") String action) {
        List<Subject> allSubjects = subjectService.getAllSubjects();
        List<SchoolClass> allSchoolClasses = schoolClassService.getAllSchoolClasses();
        //TODO проблема збереження неіснуючого предмета
        if (bindingResult.hasErrors()) {
            theTeacher.getSubjectIntMaps().add(createEmptySubjectIntMap(theTeacher));
            setModelAttributes(model, theTeacher, allSubjects, allSchoolClasses);
            return "teacher/add-teacher";
        }

        List<Integer> selectClassList = new ArrayList<>();
        List<Integer> selectSubjectList = new ArrayList<>();
        if (selectClass.length != 0 && selectSubject.length != 0) {
            for (int i = 0; i < selectClass.length; i++) {
                if (!selectClass[i].equals("") && !selectSubject[i].equals("")) {
                    selectClassList.add(Integer.parseInt(selectClass[i]));
                    selectSubjectList.add(Integer.parseInt(selectSubject[i]));
                }
            }
        }

        if (action.equals("addRow")) {
            createListUniqueData(theTeacher, allSubjects, allSchoolClasses, selectClassList, selectSubjectList);
            theTeacher.getSubjectIntMaps().add(createEmptySubjectIntMap(theTeacher));
            setModelAttributes(model, theTeacher, allSubjects, allSchoolClasses);
            return "teacher/add-teacher";
        }

        if (action.startsWith("deleteRow")) {
            int rowIdInt = Integer.parseInt(action.substring(9));
            if (rowIdInt == 0 && (selectClassList.size() == 1 || selectClassList.size() == 0)) {
                theTeacher.getSubjectIntMaps().add(createEmptySubjectIntMap(theTeacher));
            } else {
                for (int i = 0; i < selectClassList.size(); i++) {
                    if (i != rowIdInt) {
                        SubjectIntMap subjectIntMap = createEmptySubjectIntMap(theTeacher);
                        subjectIntMap.setSubject(allSubjects.get(selectSubjectList.get(i) - 1));
                        subjectIntMap.getSubject().setSchoolClass(allSchoolClasses.get(selectClassList.get(i) - 1));
                        theTeacher.getSubjectIntMaps().add(subjectIntMap);
                    }
                }
            }
            setModelAttributes(model, theTeacher, allSubjects, allSchoolClasses);
            return "teacher/add-teacher";
        }

        if (action.equals("save")) {
            if (theTeacher.getId() != 0) {
                teacherService.deleteById(theTeacher.getId());
            }
            createListUniqueData(theTeacher, allSubjects, allSchoolClasses, selectClassList, selectSubjectList);
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
        setModelAttributes(model, theTeacher, subjectService.getAllSubjects(), schoolClassService.getAllSchoolClasses());
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

    private void setModelAttributes(Model model, Teacher theTeacher, List<Subject> allSubjects,
                                    List<SchoolClass> allSchoolClasses) {
        model.addAttribute("allSchoolClasses", allSchoolClasses);
        model.addAttribute("allSubjects", allSubjects);
        model.addAttribute("teacher", theTeacher);
    }

    private void createListUniqueData(@ModelAttribute("teacher") @Valid Teacher theTeacher,
                                      List<Subject> allSubjects, List<SchoolClass> allSchoolClasses,
                                      List<Integer> selectClassList, List<Integer> selectSubjectList) {
        Set<SubjectIntMap> subjectIntMapSet = new LinkedHashSet<>();
        for (int i = 0; i < selectClassList.size(); i++) {
            SubjectIntMap subjectIntMap = new SubjectIntMap();
            subjectIntMap.setTeacher(theTeacher);
            subjectIntMap.setSubject(allSubjects.get(selectSubjectList.get(i) - 1));
            subjectIntMap.getSubject().setSchoolClass(allSchoolClasses.get(selectClassList.get(i) - 1));
            subjectIntMapSet.add(subjectIntMap);
        }
        List<SubjectIntMap> subjectIntMaps = new ArrayList<>(subjectIntMapSet);
        theTeacher.setSubjectIntMaps(subjectIntMaps);
    }
}