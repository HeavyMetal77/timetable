package ua.tarastom.timetable.service;

import ua.tarastom.timetable.entity.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();

    Subject findSubjectById(int theId);

    void saveSubject(Subject theSubject);

    void deleteSubject(Subject theSubject);

    void deleteById(int theId);

    long getCountSubjects();
}
