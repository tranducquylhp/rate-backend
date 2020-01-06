package com.codegym.rate.model;

import javax.persistence.*;

@Entity
@Table(name = "standardOutput")
public class StandardOutput {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String rate;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    public StandardOutput() {
    }

    public StandardOutput(String name, String rate, Module module) {
        this.name = name;
        this.rate = rate;
        this.module = module;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
