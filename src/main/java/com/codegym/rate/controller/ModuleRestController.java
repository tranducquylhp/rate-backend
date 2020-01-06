package com.codegym.rate.controller;

import com.codegym.rate.model.Module;
import com.codegym.rate.model.User;
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
public class ModuleRestController {
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private UserService userService;
    @ModelAttribute("userCurrent")
    public User getUserCurrent(){
        return userService.getCurrentUser();
    }

    @GetMapping("modules")
    public ResponseEntity<List<Module>> moduleList(){
        return new ResponseEntity<>(moduleService.findAllByUser(getUserCurrent()), HttpStatus.OK);
    }

    @PostMapping("modules")
    public ResponseEntity<Module> createModule(@RequestBody Module module, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        module.setUser(getUserCurrent());
        moduleService.save(module);
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    @PutMapping("modules")
    public ResponseEntity<Module> editModule(@RequestBody Module module, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (module.getUser().getId() != getUserCurrent().getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        moduleService.save(module);
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    @DeleteMapping("modules/{program_id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long program_id){
        Module module = moduleService.findById(program_id);
        if (module == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (module.getUser().getId() != getUserCurrent().getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        moduleService.delete(module);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("modules/{program_id}")
    public ResponseEntity<Module> findModuleById(@PathVariable Long program_id){
        Module module = moduleService.findById(program_id);
        if (module == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (module.getUser().getId() != getUserCurrent().getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(module, HttpStatus.OK);
    }
}
