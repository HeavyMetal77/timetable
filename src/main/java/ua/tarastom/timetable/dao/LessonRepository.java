package ua.tarastom.timetable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.tarastom.timetable.entity.Lesson;


//optinal, give another path - http://localhost:8080/timetable/lessons
@RepositoryRestResource(path = "lessons")
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    //Var.3 using REST API with Spring Data JPA Repository
}
