package com.restapi.springbootrestbackend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="employees")
public class Employee {

    @Id // primary key anotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;


}
