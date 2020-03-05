package ua.tarastom.timetable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.tarastom.timetable.entity.SubjectIntMap;


//optinal, give another path - http://localhost:8080/timetable/subjectIntMaps
@RepositoryRestResource(path = "subjectIntMaps")
public interface SubjectIntMapRepository extends JpaRepository<SubjectIntMap, Integer> {
    //Var.3 using REST API with Spring Data JPA Repository
}
