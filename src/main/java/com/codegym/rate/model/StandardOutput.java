package com.codegym.rate.model;

import javax.persistence.*;

@Entity
@Table(name = "standardOutput")
public class StandardOutput {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
