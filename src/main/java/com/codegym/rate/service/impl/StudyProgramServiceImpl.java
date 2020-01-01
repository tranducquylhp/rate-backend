package com.codegym.rate.service.impl;

import com.codegym.rate.model.StudyProgram;
import com.codegym.rate.model.User;
import com.codegym.rate.repository.StudyProgramRepository;
import com.codegym.rate.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {
    @Autowired
    private StudyProgramRepository studyProgramRepository;
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
        if (studyProgram.isPresent()){
            return studyProgram.get();
        }
        return null;
    }

    @Override
    public List<StudyProgram> findAllByUser(User user) {
        return studyProgramRepository.findAllByUser(user);
    }
}
