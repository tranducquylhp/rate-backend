package com.codegym.rate.service;

import com.codegym.rate.model.StudyProgram;
import com.codegym.rate.model.User;

import java.util.List;

public interface StudyProgramService extends GeneralService<StudyProgram>{
    List<StudyProgram> findAllByUser(User user);
    void delete(StudyProgram studyProgram);
}
