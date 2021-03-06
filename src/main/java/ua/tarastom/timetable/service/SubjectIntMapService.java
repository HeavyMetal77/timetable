package ua.tarastom.timetable.service;

import ua.tarastom.timetable.entity.SubjectIntMap;

import java.util.List;

public interface SubjectIntMapService {
    List<SubjectIntMap> getAllSubjectIntMaps();

    void save(SubjectIntMap subjectIntMap);

    void delete(SubjectIntMap subjectIntMap);
}
