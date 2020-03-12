package ua.tarastom.timetable.service;

import org.springframework.stereotype.Service;
import ua.tarastom.timetable.dao.SchoolClassRepository;
import ua.tarastom.timetable.entity.SchoolClass;

import java.util.List;
import java.util.Optional;

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

    @Override
    public long getCountSchoolClasses() {
        return schoolClassRepository.count();
    }

    @Override
    public void saveSchoolClass(SchoolClass theSchoolClass) {
        schoolClassRepository.save(theSchoolClass);
    }

    @Override
    public SchoolClass getSchoolClassById(int id) {
        Optional<SchoolClass> schoolClassById = schoolClassRepository.findById(id);
        return schoolClassById.get();
    }

    @Override
    public void deleteById(int theId) {
        schoolClassRepository.deleteById(theId);
    }
}
