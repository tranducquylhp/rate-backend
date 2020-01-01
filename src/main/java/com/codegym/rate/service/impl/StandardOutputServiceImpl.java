package com.codegym.rate.service.impl;

import com.codegym.rate.model.StandardOutput;
import com.codegym.rate.model.StudyProgram;
import com.codegym.rate.repository.StandardOutputRepository;
import com.codegym.rate.service.StandardOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StandardOutputServiceImpl implements StandardOutputService {
    @Autowired
    private StandardOutputRepository standardOutputRepository;
    @Override
    public List<StandardOutput> findAll() {
        return standardOutputRepository.findAll();
    }

    @Override
    public void save(StandardOutput standardOutput) {
        standardOutputRepository.save(standardOutput);
    }

    @Override
    public StandardOutput findById(Long id) {
        Optional<StandardOutput> standardOutput = standardOutputRepository.findById(id);
        if (standardOutput.isPresent()){
            return standardOutput.get();
        }
        return null;
    }

    @Override
    public List<StandardOutput> findAllByStudyProgram(StudyProgram studyProgram) {
        return standardOutputRepository.findAllByStudyProgram(studyProgram);
    }
}
