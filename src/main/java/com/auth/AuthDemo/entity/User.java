package com.auth.AuthDemo.entity;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

@Entity(name = "user")
@SecondaryTable(name = "authorities", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SelectBeforeUpdate(false)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "score")
    private BigDecimal score;
    @Column(name = "authority", table = "authorities")
    private String role = "ROLE_STUDENT";
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL ,orphanRemoval = true)
    private Set<UserTest> userTests = new HashSet<>();

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

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.updateScore();
        this.score = score;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<UserTest> getUserTests() {
        return userTests;
    }

    public void setUserTests(Set<UserTest> userTests) {
        this.userTests = userTests;
    }

    public void addTest(TestKC testKC){
        UserTest userTest = new UserTest();
        userTest.setTestKC(testKC);
        userTest.setUser(this);
        userTest.setCompleted(false);
        userTest.setScore(BigDecimal.ZERO);
        this.addUserTest(userTest);
    }

    public boolean addUserTest(UserTest userTest){
        this.userTests.remove(userTest);
        return this.userTests.add(userTest);
    }

    public UserTest findUserTest(TestKC testKC){
        return this.userTests.stream().filter(userTest -> userTest.getTestKC().equals(testKC)).findFirst()
                .orElseThrow(()-> new NoSuchElementException(String.format("No test %s were registered for user %s", testKC.getName(), this.name)));
    }

    public BigDecimal updateScore(){
        if (userTests.isEmpty()){
            score = BigDecimal.ZERO;
//            System.out.println("Empty user tests list");
            return score;
        }
        score =  userTests.stream().map(UserTest::getScore).reduce(BigDecimal.ZERO, BigDecimal::add).divide(new BigDecimal(userTests.size()), new MathContext(2));
//        System.out.println("Calculated user score");
        return score;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "name='" + name + '\'' +
                ", userTests=" + userTests +
                ", role=" + role +
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
