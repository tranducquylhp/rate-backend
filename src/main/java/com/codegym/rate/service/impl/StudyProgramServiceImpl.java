package com.codegym.rate.service.impl;

import com.codegym.rate.model.Module;
import com.codegym.rate.model.StudyProgram;
import com.codegym.rate.model.User;
import com.codegym.rate.repository.ModuleRepository;
import com.codegym.rate.repository.StudyProgramRepository;
import com.codegym.rate.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {
    @Autowired
    private StudyProgramRepository studyProgramRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Override
    public List<Module> findAllByStudyProgram(StudyProgram studyProgram) {
        return moduleRepository.findAllByStudyProgram(studyProgram);
    }

    @Override
    public void delete(StudyProgram studyProgram) {
        studyProgramRepository.delete(studyProgram);
    }

    @Override
    public List<StudyProgram> findAllByUser(User user) {
        return studyProgramRepository.findAllByUser(user);
    }

    @Override
    public boolean isStudyProgramOfUser(User user, StudyProgram studyProgram) {
        if (studyProgram.getUser().getId() == user.getId()){
            return true;
        }
        return false;
    }

    @Override
    public void addStudent(User user, StudyProgram studyProgram) {
        Set<User> students = studyProgram.getStudents();
        students.add(user);
        studyProgram.setStudents(students);
        studyProgramRepository.save(studyProgram);
    }

    @Override
    public List<StudyProgram> findAll() {
        return studyProgramRepository.findAll();
    }

    @Override
    public void save(StudyProgram studyProgram) {
        studyProgramRepository.save(studyProgram);
    }

    @Override
    public StudyProgram findById(Long id) {
        Optional<StudyProgram> studyProgram = studyProgramRepository.findById(id);
        if (studyProgram.isPresent()) {
            return studyProgram.get();
        }
        return null;
    }
}
