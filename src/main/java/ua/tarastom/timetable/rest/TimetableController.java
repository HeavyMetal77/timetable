package ua.tarastom.timetable.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tarastom.timetable.entity.*;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/timetable")
public class TimetableController {

    private final EntityManager entityManager;

    public TimetableController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/hello")
    public String hello() {
        System.out.println("\nlesson: " + entityManager.find(Lesson.class, 2));
        System.out.println("\nteacher: " + entityManager.find(Teacher.class, 2));
        System.out.println("\nclass: " + entityManager.find(SchoolClass.class, 2));
        System.out.println("\nroom: " + entityManager.find(Classroom.class, 2));
        System.out.println("\nSubject: " + entityManager.find(Subject.class, 2));

        return "Hello Teacher!";
    }

}
