package com.codegym.rate.controller;

import com.codegym.rate.model.StudyProgram;
import com.codegym.rate.model.User;
import com.codegym.rate.service.StudyProgramService;
import com.codegym.rate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class StudyProgramRestController {
    @Autowired
    private StudyProgramService studyProgramService;
    @Autowired
    private UserService userService;
    @ModelAttribute("userCurrent")
    public User getUserCurrent(){
        return userService.getCurrentUser();
    }

    @GetMapping("studyPrograms")
    public ResponseEntity<List<StudyProgram>> studyProgramList(){
        return new ResponseEntity<>(studyProgramService.findAllByUser(getUserCurrent()), HttpStatus.OK);
    }

    @PostMapping("studyPrograms")
    public ResponseEntity<StudyProgram> createStudyProgram(@RequestBody StudyProgram studyProgram, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        studyProgram.setUser(getUserCurrent());
        studyProgramService.save(studyProgram);
        return new ResponseEntity<>(studyProgram, HttpStatus.OK);
    }

    @PutMapping("studyPrograms")
    public ResponseEntity<StudyProgram> editStudyProgram(@RequestBody StudyProgram studyProgram, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (studyProgram.getUser().getId() != getUserCurrent().getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studyProgramService.save(studyProgram);
        return new ResponseEntity<>(studyProgram, HttpStatus.OK);
    }

    @DeleteMapping("studyPrograms/{program_id}")
    public ResponseEntity<Void> deleteStudyProgram(@PathVariable Long program_id){
        StudyProgram studyProgram = studyProgramService.findById(program_id);
        if (studyProgram.getUser().getId() != getUserCurrent().getId() || studyProgram == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studyProgramService.delete(studyProgram);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
