package ua.tarastom.timetable.service;

import org.springframework.stereotype.Service;
import ua.tarastom.timetable.dao.SubjectRepository;
import ua.tarastom.timetable.entity.Subject;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findSubjectById(int theId) {
        return subjectRepository.findById(theId).get();
    }

    @Override
    public void saveSubject(Subject theSubject) {
        subjectRepository.save(theSubject);
    }

    @Override
    public void deleteSubject(Subject theSubject) {
        subjectRepository.delete(theSubject);
    }

    @Override
    public void deleteById(int theId) {
        subjectRepository.deleteById(theId);
    }

    @Override
    public long getCountSubjects() {
        return subjectRepository.count();
    }
}
