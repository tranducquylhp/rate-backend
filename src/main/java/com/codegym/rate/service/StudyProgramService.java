package com.codegym.rate.service;

import com.codegym.rate.model.Module;
import com.codegym.rate.model.StudyProgram;

import java.util.List;

public interface StudyProgramService extends GeneralService<StudyProgram> {
    List<Module> findAllByStudyProgram(StudyProgram studyProgram);
    void delete(StudyProgram studyProgram);
}
