package ua.tarastom.timetable.service;

import org.springframework.stereotype.Service;
import ua.tarastom.timetable.dao.SchoolClassRepository;
import ua.tarastom.timetable.entity.SchoolClass;

import java.util.List;

@Service
public class SchoolClassServiceImpl implements SchoolClassService{

    private SchoolClassRepository schoolClassRepository;

    public SchoolClassServiceImpl(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    @Override
    public List<SchoolClass> getAllSchoolClasses() {
        return schoolClassRepository.findAll();
    }
}
