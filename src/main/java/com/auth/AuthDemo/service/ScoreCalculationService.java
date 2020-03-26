package com.auth.AuthDemo.service;

import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ScoreCalculationService {
    private UserService userService;
    private TestService testService;

    @Autowired
    public ScoreCalculationService(UserService userService, TestService testService) {
        this.userService = userService;
        this.testService = testService;
    }

    public void updateUserScore(User user, TestKC testKC){
        BigDecimal score = testKC.updateUserScore(user);
        System.out.println(score);
    }

}
