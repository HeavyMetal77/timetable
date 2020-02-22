package ua.tarastom.timetable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.tarastom.timetable.entity.Classroom;


//optinal, give another path - http://localhost:8080/timetable/classrooms
@RepositoryRestResource(path = "classrooms")
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
    //Var.3 using REST API with Spring Data JPA Repository
}
