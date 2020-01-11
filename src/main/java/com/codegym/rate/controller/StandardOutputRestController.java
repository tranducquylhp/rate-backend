package com.codegym.rate.controller;

import com.codegym.rate.model.StandardOutput;
import com.codegym.rate.model.Module;
import com.codegym.rate.model.StudyProgram;
import com.codegym.rate.model.User;
import com.codegym.rate.service.StandardOutputService;
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
@RequestMapping("/studyPrograms/{program_id}/modules/{module_id}/")
public class StandardOutputRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private StandardOutputService standardOutputService;

    @Autowired
    private StudyProgramService studyProgramService;

    @ModelAttribute("userCurrent")
    public User getUserCurrent(){
        return userService.getCurrentUser();
    }

    @GetMapping("standardOutputs")
    public ResponseEntity<List<StandardOutput>> standardOutputList(@PathVariable Long program_id, @PathVariable Long module_id){
        Module module = moduleService.findById(module_id);
        StudyProgram studyProgram = studyProgramService.findById(program_id);
        if (module == null || studyProgram == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            boolean isStandardOutputOfModuleOfStudyProgramOfUser = moduleService.isModuleOfStudyProgram(studyProgram, module)
                    || studyProgramService.isStudyProgramOfUser(getUserCurrent(), studyProgram);
            if (!isStandardOutputOfModuleOfStudyProgramOfUser){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        List<StandardOutput> standardOutputs = standardOutputService.findAllByModule(module);
        return new ResponseEntity<>(standardOutputs, HttpStatus.OK);
    }

    @PostMapping("standardOutputs")
    public ResponseEntity<StandardOutput> createStandardOutput(@PathVariable Long program_id, @PathVariable Long module_id, @RequestBody StandardOutput standardOutput, BindingResult bindingResult){
        Module module = moduleService.findById(module_id);
        StudyProgram studyProgram = studyProgramService.findById(program_id);
        if (module == null || studyProgram == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            boolean isStandardOutputOfModuleOfStudyProgramOfUser = moduleService.isModuleOfStudyProgram(studyProgram, module)
                    || studyProgramService.isStudyProgramOfUser(getUserCurrent(), studyProgram);
            if (!isStandardOutputOfModuleOfStudyProgramOfUser){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        standardOutput.setModule(module);
        standardOutputService.save(standardOutput);
        return new ResponseEntity<>(standardOutput, HttpStatus.OK);
    }

    @PutMapping("standardOutputs")
    public ResponseEntity<StandardOutput> editStandardOutput(@PathVariable Long program_id, @PathVariable Long module_id, @RequestBody StandardOutput standardOutput, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Module module = moduleService.findById(module_id);
        StudyProgram studyProgram = studyProgramService.findById(program_id);
        if (module == null || studyProgram == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            boolean isStandardOutputOfModuleOfStudyProgramOfUser = standardOutputService.isStandardOutputOfModule(module, standardOutput)
                    || moduleService.isModuleOfStudyProgram(studyProgram, module)
                    || studyProgramService.isStudyProgramOfUser(getUserCurrent(), studyProgram);
            if (!isStandardOutputOfModuleOfStudyProgramOfUser){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        standardOutputService.save(standardOutput);
        return new ResponseEntity<>(standardOutput, HttpStatus.OK);
    }

    @DeleteMapping("standardOutputs/{output_id}")
    public ResponseEntity<Void> deleteStandardOutput(@PathVariable Long program_id, @PathVariable Long module_id, @PathVariable Long output_id){
        Module module = moduleService.findById(module_id);
        StudyProgram studyProgram = studyProgramService.findById(program_id);
        StandardOutput standardOutput = standardOutputService.findById(output_id);
        if (module == null || studyProgram == null || standardOutput == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            boolean isStandardOutputOfModuleOfStudyProgramOfUser = standardOutputService.isStandardOutputOfModule(module, standardOutput)
                    || moduleService.isModuleOfStudyProgram(studyProgram, module)
                    || studyProgramService.isStudyProgramOfUser(getUserCurrent(), studyProgram);
            if (!isStandardOutputOfModuleOfStudyProgramOfUser){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        standardOutputService.delete(standardOutput);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("standardOutputs/{output_id}")
    public ResponseEntity<StandardOutput> findStarndardOutputById(@PathVariable Long program_id, @PathVariable Long module_id, @PathVariable Long output_id){
        Module module = moduleService.findById(module_id);
        StudyProgram studyProgram = studyProgramService.findById(program_id);
        StandardOutput standardOutput = standardOutputService.findById(output_id);
        if (module == null || studyProgram == null || standardOutput == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            boolean isStandardOutputOfModuleOfStudyProgramOfUser = standardOutputService.isStandardOutputOfModule(module, standardOutput)
                    || moduleService.isModuleOfStudyProgram(studyProgram, module)
                    || studyProgramService.isStudyProgramOfUser(getUserCurrent(), studyProgram);
            if (!isStandardOutputOfModuleOfStudyProgramOfUser){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(standardOutput, HttpStatus.OK);
    }
}
