package com.codegym.rate.model;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(targetEntity = StudyProgram.class)
    private StudyProgram studyProgram;

    public User() {
    }

    public User(String name, StudyProgram studyProgram) {
        this.name = name;
        this.studyProgram = studyProgram;
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

    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }
}
