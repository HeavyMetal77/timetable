package ua.tarastom.timetable.service;

import org.springframework.stereotype.Service;
import ua.tarastom.timetable.dao.SubjectIntMapRepository;
import ua.tarastom.timetable.entity.SubjectIntMap;

import java.util.List;

@Service
public class SubjectIntMapServiceImpl implements SubjectIntMapService {

    private final SubjectIntMapRepository subjectIntMapRepository;

    public SubjectIntMapServiceImpl(SubjectIntMapRepository subjectIntMapRepository) {
        this.subjectIntMapRepository = subjectIntMapRepository;
    }

    @Override
    public List<SubjectIntMap> getAllSubjectIntMaps() {
        return subjectIntMapRepository.findAll();
    }


}
