package com.codegym.rate.service.impl;

import com.codegym.rate.model.Module;
import com.codegym.rate.model.StudyProgram;
import com.codegym.rate.model.User;
import com.codegym.rate.repository.ModuleRepository;
import com.codegym.rate.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;
    @Override
    public List<Module> findAll() {
        return moduleRepository.findAll();
    }

    @Override
    public void save(Module module) {
        moduleRepository.save(module);
    }

    @Override
    public Module findById(Long id) {
        Optional<Module> module = moduleRepository.findById(id);
        if (module.isPresent()){
            return module.get();
        }
        return null;
    }

    @Override
    public List<Module> findAllByStudyProgram(StudyProgram studyProgram) {
        return moduleRepository.findAllByStudyProgram(studyProgram);
    }

    @Override
    public void delete(Module module) {
        moduleRepository.delete(module);
    }
}
