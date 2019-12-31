package com.codegym.rate.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "studyProgram")
public class StudyProgram {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(targetEntity = StandardOutput.class)
    private StandardOutput standardOutput;

    public StudyProgram() {
    }

    public StudyProgram(String name, LocalDate date, Instructor instructor, StandardOutput standardOutput) {
        this.name = name;
        this.date = date;
        this.instructor = instructor;
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

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public StandardOutput getStandardOutput() {
        return standardOutput;
    }

    public void setStandardOutput(StandardOutput standardOutput) {
        this.standardOutput = standardOutput;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
