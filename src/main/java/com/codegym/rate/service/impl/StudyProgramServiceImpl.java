package com.codegym.rate.service.impl;

import com.codegym.rate.model.Module;
import com.codegym.rate.model.StudyProgram;
import com.codegym.rate.repository.ModuleRepository;
import com.codegym.rate.repository.StudyProgramRepository;
import com.codegym.rate.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
