package com.codegym.rate.controller;

import com.codegym.rate.model.StandardOutput;
import com.codegym.rate.model.Module;
import com.codegym.rate.model.User;
import com.codegym.rate.service.StandardOutputService;
import com.codegym.rate.service.ModuleService;
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
    private ModuleService moduleService;

    @Autowired
    private StandardOutputService standardOutputService;

    @ModelAttribute("userCurrent")
    public User getUserCurrent(){
        return userService.getCurrentUser();
    }

    @GetMapping("/modules/{program_id}/standardOutputs")
    public ResponseEntity<List<StandardOutput>> standardOutputList(@PathVariable Long program_id){
        Module module = moduleService.findById(program_id);
        if (module == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (module.getUser().getId() != getUserCurrent().getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<StandardOutput> standardOutputs = standardOutputService.findAllByModule(module);
        return new ResponseEntity<>(standardOutputs, HttpStatus.OK);
    }

    @PostMapping("/modules/{program_id}/standardOutputs")
    public ResponseEntity<StandardOutput> createStandardOutput(@PathVariable Long program_id, @RequestBody StandardOutput standardOutput, BindingResult bindingResult){
        Module module = moduleService.findById(program_id);
        if (module == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (module.getUser().getId() != getUserCurrent().getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        standardOutput.setModule(module);
        standardOutputService.save(standardOutput);
        return new ResponseEntity<>(standardOutput, HttpStatus.OK);
    }

    @PutMapping("/modules/{program_id}/standardOutputs")
    public ResponseEntity<StandardOutput> editStandardOutput(@PathVariable Long program_id, @RequestBody StandardOutput standardOutput, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Module module = moduleService.findById(program_id);
        if (module == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (module.getUser().getId() != getUserCurrent().getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (standardOutput.getModule().getId() != module.getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        standardOutputService.save(standardOutput);
        return new ResponseEntity<>(standardOutput, HttpStatus.OK);
    }

    @DeleteMapping("/modules/{program_id}/standardOutputs/{output_id}")
    public ResponseEntity<Void> deleteStandardOutput(@PathVariable Long program_id, @PathVariable Long output_id){
        Module module = moduleService.findById(program_id);
        if (module == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (module.getUser().getId() != getUserCurrent().getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        StandardOutput standardOutput = standardOutputService.findById(output_id);
        if (standardOutput == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (standardOutput.getModule().getId() != module.getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        standardOutputService.delete(standardOutput);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/modules/{program_id}/standardOutputs/{output_id}")
    public ResponseEntity<StandardOutput> findStarndardOutputById(@PathVariable Long program_id, @PathVariable Long output_id){
        Module module = moduleService.findById(program_id);
        if (module == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (module.getUser().getId() != getUserCurrent().getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        StandardOutput standardOutput = standardOutputService.findById(output_id);
        if (standardOutput == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (standardOutput.getModule().getId() != module.getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(standardOutput, HttpStatus.OK);
    }
}
