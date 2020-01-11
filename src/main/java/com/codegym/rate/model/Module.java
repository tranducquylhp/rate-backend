package com.codegym.rate.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String goal;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "studyProgram_id")
    private StudyProgram studyProgram;


    @OneToMany(targetEntity = StandardOutput.class)
    private Set<StandardOutput> standardOutput;

    public Module() {
    }

    public Module(String name, String description, String goal, LocalDate date, StudyProgram studyProgram, Set<StandardOutput> standardOutput) {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.date = date;
        this.studyProgram = studyProgram;
        this.standardOutput = standardOutput;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }

    public Set<StandardOutput> getStandardOutput() {
        return standardOutput;
    }

    public void setStandardOutput(Set<StandardOutput> standardOutput) {
        this.standardOutput = standardOutput;
    }
}
