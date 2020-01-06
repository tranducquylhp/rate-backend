package com.codegym.rate.repository;

import com.codegym.rate.model.StandardOutput;
import com.codegym.rate.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandardOutputRepository extends JpaRepository<StandardOutput, Long> {
    List<StandardOutput> findAllByModule(Module module);
}
