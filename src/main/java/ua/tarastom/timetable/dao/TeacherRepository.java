package ua.tarastom.timetable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.tarastom.timetable.entity.Teacher;

import java.util.List;


//optinal, give another path - http://localhost:8080/timetable/teachers
@RepositoryRestResource(path = "teachers")
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    //Var.3 using REST API with Spring Data JPA Repository

    // that's it ... no need to write any code LOL!

    // add a method to sort by last name
    List<Teacher> findAllByOrderByLastNameAsc();
}
