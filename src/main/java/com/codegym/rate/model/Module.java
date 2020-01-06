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
    @JoinColumn(name = "instructor_id")
    private User user;

    @OneToMany(targetEntity = User.class)
    private Set<User> students;

    @OneToMany(targetEntity = StandardOutput.class)
    private Set<StandardOutput> standardOutput;

    public Module() {
    }

    public Module(String name, String description, String goal, LocalDate date, User user, Set<User> students, Set<StandardOutput> standardOutput) {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.date = date;
        this.user = user;
        this.students = students;
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

    public Set<User> getStudents() {
        return students;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }
}
