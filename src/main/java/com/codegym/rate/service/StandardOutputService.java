package com.codegym.rate.service;

import com.codegym.rate.model.StandardOutput;
import com.codegym.rate.model.Module;

import java.util.List;

public interface StandardOutputService extends GeneralService<StandardOutput> {
    List<StandardOutput> findAllByModule(Module module);
    void delete(StandardOutput standardOutput);
    boolean isStandardOutputOfModule(Module module, StandardOutput standardOutput);
}
