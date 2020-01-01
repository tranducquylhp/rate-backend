package com.codegym.rate.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person")
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String confirmPassword;

    @OneToMany(targetEntity = StudyProgram.class)
    private Set<StudyProgram> studyProgram;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    public User() {
    }

    public User(String name, String username, String password, String confirmPassword, Set<StudyProgram> studyProgram, Role role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.studyProgram = studyProgram;
        this.role = role;
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

    public Set<StudyProgram> getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(Set<StudyProgram> studyProgram) {
        this.studyProgram = studyProgram;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role
    ) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
