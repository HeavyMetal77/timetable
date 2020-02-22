package ua.tarastom.timetable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.tarastom.timetable.entity.Subject;


//optinal, give another path - http://localhost:8080/timetable/subjects
@RepositoryRestResource(path = "subjects")
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    //Var.3 using REST API with Spring Data JPA Repository
}
