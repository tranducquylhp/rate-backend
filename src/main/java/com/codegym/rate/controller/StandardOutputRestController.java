package com.codegym.rate.controller;

import com.codegym.rate.model.StandardOutput;
import com.codegym.rate.model.StudyProgram;
import com.codegym.rate.model.User;
import com.codegym.rate.service.StandardOutputService;
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
public class StandardOutputRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private StudyProgramService studyProgramService;

    @Autowired
    private StandardOutputService standardOutputService;

    @ModelAttribute("userCurrent")
    public User getUserCurrent(){
        return userService.getCurrentUser();
    }

    @GetMapping("/studyPrograms/{program_id}/standardOutputs")
    public ResponseEntity<List<StandardOutput>> standardOutputList(@PathVariable Long program_id){
        StudyProgram studyProgram = studyProgramService.findById(program_id);
        if (studyProgram.getUser().getId() != getUserCurrent().getId() || studyProgram == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<StandardOutput> standardOutputs = standardOutputService.findAllByStudyProgram(studyProgram);
        return new ResponseEntity<>(standardOutputs, HttpStatus.OK);
    }

    @PostMapping("/studyPrograms/{program_id}/standardOutputs")
    public ResponseEntity<StandardOutput> createStandardOutput(@PathVariable Long program_id, @RequestBody StandardOutput standardOutput, BindingResult bindingResult){
        StudyProgram studyProgram = studyProgramService.findById(program_id);
        if (studyProgram.getUser().getId() != getUserCurrent().getId() || studyProgram == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        standardOutput.setStudyProgram(studyProgram);
        standardOutputService.save(standardOutput);
        return new ResponseEntity<>(standardOutput, HttpStatus.OK);
    }

    @PutMapping("/studyPrograms/{program_id}/standardOutputs")
    public ResponseEntity<StandardOutput> editStandardOutput(@PathVariable Long program_id, @RequestBody StandardOutput standardOutput, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        StudyProgram studyProgram = studyProgramService.findById(program_id);
        if (studyProgram.getUser().getId() != getUserCurrent().getId() || studyProgram == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        standardOutputService.save(standardOutput);
        return new ResponseEntity<>(standardOutput, HttpStatus.OK);
    }
}
