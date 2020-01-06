package com.codegym.rate.repository;

import com.codegym.rate.model.Module;
import com.codegym.rate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findAllByUser(User user);
}
