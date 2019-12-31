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
    @JoinColumn(name = "studyProgram_id")
    private StudyProgram studyProgram;

    public StandardOutput() {
    }

    public StandardOutput(String name, String rate, StudyProgram studyProgram) {
        this.name = name;
        this.rate = rate;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
