package com.codegym.rate.repository;

import com.codegym.rate.model.StandardOutput;
import com.codegym.rate.model.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandardOutputRepository extends JpaRepository<StandardOutput, Long> {
    List<StandardOutput> findAllByStudyProgram(StudyProgram studyProgram);
}
