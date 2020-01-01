package com.codegym.rate.controller;

import com.codegym.rate.model.StudyProgram;
import com.codegym.rate.model.User;
import com.codegym.rate.service.StudyProgramService;
import com.codegym.rate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

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
    public List<StudyProgram> studyProgramList(){
        return studyProgramService.findAllByUser(getUserCurrent());
    }
}
