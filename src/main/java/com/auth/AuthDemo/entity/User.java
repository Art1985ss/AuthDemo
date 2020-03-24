package com.auth.AuthDemo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @ManyToMany
    @JoinTable(name = "user_tests",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    private List<Test> testList;


}
