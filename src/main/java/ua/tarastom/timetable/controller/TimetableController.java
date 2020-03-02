package ua.tarastom.timetable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.tarastom.timetable.dao.TimeTableDAO;
import ua.tarastom.timetable.entity.Teacher;
import ua.tarastom.timetable.util.TimetableUtils;

import java.util.List;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

    private final TimeTableDAO timeTableDAO;
    private final TimetableUtils timetableUtils;

    public TimetableController(TimeTableDAO timeTableDAO, TimetableUtils timetableUtils) {
        this.timeTableDAO = timeTableDAO;
        this.timetableUtils = timetableUtils;
    }

    @RequestMapping("/")
    public String listTeachers(Model model) {
        List<Teacher> teacherList = timeTableDAO.getTeacherList();
        model.addAttribute("teacherList", teacherList);
        return "list-teachers";
    }
}
