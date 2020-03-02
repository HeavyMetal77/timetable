package ua.tarastom.timetable.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tarastom.timetable.dao.TimeTableDAO;
import ua.tarastom.timetable.entity.SubjectIntMap;
import ua.tarastom.timetable.entity.Teacher;
import ua.tarastom.timetable.util.TimetableUtils;

import java.util.List;

@RestController
@RequestMapping("/timetable")
public class TimetableRestController {

    private final TimeTableDAO timeTableDAO;
    private final TimetableUtils timetableUtils;

    public TimetableRestController(TimeTableDAO timeTableDAO, TimetableUtils timetableUtils) {
        this.timeTableDAO = timeTableDAO;
        this.timetableUtils = timetableUtils;
    }

    @GetMapping("/hello")
    public String hello() {
//        System.out.println("\nlesson: " + entityManager.find(Lesson.class, 2));
//        System.out.println("\nteacher: " + entityManager.find(Teacher.class, 2));
//        System.out.println("\nclass: " + entityManager.find(SchoolClass.class, 2));
//        System.out.println("\nroom: " + entityManager.find(Classroom.class, 2));
//        System.out.println("\nSubject: " + entityManager.find(Subject.class, 2));
//        System.out.println();

        List<Teacher> teacherList = timeTableDAO.getTeacherList();
        SubjectIntMap subjectIntMap = timeTableDAO.getSubjectIntMapList().get(3);
        List<Teacher> teachers = timetableUtils.getTeacher(teacherList, subjectIntMap);

        return teachers.toString();
    }

//    @PostMapping("/helloPost")
//    public String helloPost(@ModelAttribute("teacher") Teacher teacher) {
//
//        return null;
//    }

}
