package com.codegym.rate.service;

import com.codegym.rate.model.StandardOutput;
import com.codegym.rate.model.StudyProgram;

import java.util.List;

public interface StandardOutputService extends GeneralService<StandardOutput> {
    List<StandardOutput> findAllByStudyProgram(StudyProgram studyProgram);
    void delete(StandardOutput standardOutput);
}
