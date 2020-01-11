package com.codegym.rate.service;

import com.codegym.rate.model.Module;
import com.codegym.rate.model.StudyProgram;
import com.codegym.rate.model.User;

import java.util.List;

public interface ModuleService extends GeneralService<Module>{
    List<Module> findAllByStudyProgram(StudyProgram studyProgram);
    void delete(Module module);
    boolean isModuleOfStudyProgram(StudyProgram studyProgram, Module module);
}
