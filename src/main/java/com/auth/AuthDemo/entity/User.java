package com.auth.AuthDemo.entity;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

/**
 * Class for basic structure of User object to be stored in database. All necessary fields are marked with
 * corresponding JPA annotations and appropriate getters and setters are generated.
 * User object is bound to UserTests entity using one-to-many relationship.
 */
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

    /**
     * Method for adding new UserTests to current user.
     * @param userTest UserTest object to be added to user
     * @return Boolean value if operation succeeded.
     */
    public boolean addUserTest(UserTest userTest){
        this.userTests.remove(userTest);
        return this.userTests.add(userTest);
    }

    /**
     * Method for finding corresponding UserTest object given Test object.
     * @param testKC Object for UserTest to be found.
     * @return
     */
    public UserTest findUserTest(TestKC testKC){
        return this.userTests.stream().filter(userTest -> userTest.getTestKC().equals(testKC)).findFirst()
                .orElseThrow(()-> new NoSuchElementException(String.format("No test %s were registered for user %s", testKC.getName(), this.name)));
    }


    /**
     * Method for Updating user score from test results stored in UserTest object.
     * @return BigDecimal value - test score.
     */
    public BigDecimal updateScore(){
        if (userTests.isEmpty()){
            score = BigDecimal.ZERO;
            return score;
        }
        score =  userTests.stream().map(UserTest::getScore).reduce(BigDecimal.ZERO, BigDecimal::add).divide(new BigDecimal(userTests.size()), new MathContext(2));
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
