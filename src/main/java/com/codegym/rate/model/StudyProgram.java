package com.codegym.rate.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "studyProgram")
public class StudyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User user;

    @OneToMany(targetEntity = Module.class)
    private Set<Module> modules;

    public StudyProgram() {
    }

    public StudyProgram(String name, String image, User user, Set<Module> modules) {
        this.name = name;
        this.image = image;
        this.user = user;
        this.modules = modules;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }
}
