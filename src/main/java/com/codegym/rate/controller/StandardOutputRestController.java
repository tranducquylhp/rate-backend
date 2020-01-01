package com.codegym.rate.controller;

import com.codegym.rate.model.User;
import com.codegym.rate.service.StandardOutputService;
import com.codegym.rate.service.StudyProgramService;
import com.codegym.rate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

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
}
