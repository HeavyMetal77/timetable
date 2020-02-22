package ua.tarastom.timetable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.tarastom.timetable.entity.Teacher;


//optinal, give another path - http://localhost:8080/timetable/teachers
@RepositoryRestResource(path = "teachers")
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    //Var.3 using REST API with Spring Data JPA Repository
}
