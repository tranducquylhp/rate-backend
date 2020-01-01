package com.codegym.rate.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

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
    private User user;

    @OneToMany(targetEntity = StandardOutput.class)
    private Set<StandardOutput> standardOutput;

    public StudyProgram() {
    }

    public StudyProgram(String name, LocalDate date, User user, Set<StandardOutput> standardOutput) {
        this.name = name;
        this.date = date;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<StandardOutput> getStandardOutput() {
        return standardOutput;
    }

    public void setStandardOutput(Set<StandardOutput> standardOutput) {
        this.standardOutput = standardOutput;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
