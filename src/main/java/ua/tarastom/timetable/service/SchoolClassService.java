package ua.tarastom.timetable.service;

import ua.tarastom.timetable.entity.SchoolClass;

import java.util.List;

public interface SchoolClassService {
    List<SchoolClass> getAllSchoolClasses();

    long getCountSchoolClasses();

    void saveSchoolClass(SchoolClass theSchoolClass);

    SchoolClass getSchoolClassById(int id);

    void deleteById(int theId);

}
