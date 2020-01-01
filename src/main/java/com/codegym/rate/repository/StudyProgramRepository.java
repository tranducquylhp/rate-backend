package com.codegym.rate.repository;

import com.codegym.rate.model.StudyProgram;
import com.codegym.rate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyProgramRepository extends JpaRepository<StudyProgram, Long> {
    List<StudyProgram> findAllByUser(User user);
}
