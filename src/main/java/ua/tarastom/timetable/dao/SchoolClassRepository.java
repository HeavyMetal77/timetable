package ua.tarastom.timetable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.tarastom.timetable.entity.SchoolClass;


//optinal, give another path - http://localhost:8080/timetable/schoolclasses
@RepositoryRestResource(path = "schoolclasses")
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
    //Var.3 using REST API with Spring Data JPA Repository
}
