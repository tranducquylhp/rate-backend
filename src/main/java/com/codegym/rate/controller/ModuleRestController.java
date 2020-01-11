package com.codegym.rate.controller;

import com.codegym.rate.model.Module;
import com.codegym.rate.model.StudyProgram;
import com.codegym.rate.model.User;
import com.codegym.rate.service.ModuleService;
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
@RequestMapping("/studyPrograms/{program_id}")
public class ModuleRestController {
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudyProgramService studyProgramService;
    @ModelAttribute("userCurrent")
    public User getUserCurrent(){
        return userService.getCurrentUser();
    }

    @GetMapping("/modules")
    public ResponseEntity<List<Module>> moduleList(@PathVariable Long program_id){
        StudyProgram studyProgram = studyProgramService.findById(program_id);
        if (studyProgram == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!studyProgramService.isStudyProgramOfUser(getUserCurrent(), studyProgram)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Module> modules = moduleService.findAllByStudyProgram(studyProgram);
        return new ResponseEntity<>(modules, HttpStatus.OK);
    }

    @PostMapping("modules")
    public ResponseEntity<Module> createModule(@PathVariable Long program_id, @RequestBody Module module, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        StudyProgram studyProgram = studyProgramService.findById(program_id);
        if (studyProgram == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!studyProgramService.isStudyProgramOfUser(getUserCurrent(), studyProgram)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        module.setStudyProgram(studyProgram);
        moduleService.save(module);
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    @PutMapping("modules")
    public ResponseEntity<Module> editModule(@PathVariable Long program_id, @RequestBody Module module, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        StudyProgram studyProgram = studyProgramService.findById(program_id);
        if (studyProgram == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!studyProgramService.isStudyProgramOfUser(getUserCurrent(), studyProgram)
                    || !moduleService.isModuleOfStudyProgram(studyProgram, module)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        moduleService.save(module);
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    @DeleteMapping("modules/{module_id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long module_id, @PathVariable Long program_id){
        Module module = moduleService.findById(module_id);
        if (module == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (module.getStudyProgram().getId() != program_id){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        moduleService.delete(module);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("modules/{module_id}")
    public ResponseEntity<Module> findModuleById(@PathVariable Long program_id, @PathVariable Long module_id){
        Module module = moduleService.findById(module_id);
        StudyProgram studyProgram = studyProgramService.findById(program_id);
        if (studyProgram == null || module == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!studyProgramService.isStudyProgramOfUser(getUserCurrent(), studyProgram)
                    || !moduleService.isModuleOfStudyProgram(studyProgram, module)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(module, HttpStatus.OK);
    }
}
