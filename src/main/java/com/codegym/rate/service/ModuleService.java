package com.codegym.rate.service;

import com.codegym.rate.model.Module;
import com.codegym.rate.model.User;

import java.util.List;

public interface ModuleService extends GeneralService<Module>{
    List<Module> findAllByUser(User user);
    void delete(Module module);
}
