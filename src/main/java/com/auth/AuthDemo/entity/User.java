package com.auth.AuthDemo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "score")
    private BigDecimal score;
    @ManyToMany
    @JoinTable(name = "user_tests",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    private List<TestKC> testKCList;
    @OneToMany(mappedBy = "user")
    private List<UserTests> userTests;
    @OneToMany(mappedBy = "user")
    private List<UserAnswer> answers;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserTests> getUserTests() {
        return userTests;
    }

    public void setUserTests(List<UserTests> userTests) {
        this.userTests = userTests;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userTests=" + userTests +
//                ", answers=" + answers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }
}
