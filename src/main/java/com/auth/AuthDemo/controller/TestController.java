package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoPropertiesForm;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.entity.UserTest;
import com.auth.AuthDemo.entity.UserTestKey;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.ScoreCalculationService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;


/**
 * Controller class related to Test functionality - when users are taking tests,
 * and viewing results after.
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    ScoreCalculationService scoreCalculationService;

    /**
     * Method provides mapping for users test form, when test is set to enable.
     * Test ID is passed by URL and active User in the Session is retrieved using
     * Spring security Principal object. Tests are retrieved from database and are converted to DTO object
     * for processing on HTML side. DtoTestKc is passed to html document as testKC. Also
     * UserTest object is created - for test result storing in database. UserTest object binds
     * together User and TestKC object.
     * @param testId Test ID passed by URL.
     * @param principal Current user of the session.
     * @return ModelAndView bound to test.html
     */
    @GetMapping("/user/test{testId}")
    public ModelAndView getUserData(@PathVariable("testId") Long testId, Principal principal){
        ModelAndView mav = new ModelAndView();
        User user = userService.findByName(principal.getName());
        TestKC testKC = testService.findById(testId);
        boolean completed = user.getUserTests().stream().anyMatch(ut-> ut.getTestKC().equals(testKC));
        if(completed){return new ModelAndView("redirect:/home"); }
        UserTest userTest = new UserTest();
        userTest.setId(new UserTestKey(user.getId(), testKC.getId()));
        userTest.setUser(user);
        userTest.setTestKC(testKC);
        userTest.setScore(BigDecimal.ZERO);
        userTest.setCompleted(false);
        user.addUserTest(userTest);
        userService.update(user);
        DtoTestKC dtoTestKC = DtoConverter.toDto(testKC);

        mav.addObject("question", questionService.findAll());
        mav.addObject("user", user);
        mav.addObject("testKC", dtoTestKC);
        mav.addObject("score", scoreCalculationService.getTestScore(dtoTestKC));
        mav.addObject("dtoQuestion", dtoTestKC.getQuestionList().get(0));

        DtoPropertiesForm form = new DtoPropertiesForm();
        form.setProperties(dtoTestKC.getQuestionList());
        mav.addObject("form", form);
        mav.setViewName("test");
        return mav;
    }

    /**
     * Mapping for POST method of test submit form. Matching User and TestKC objects are retrieved
     * from database (using appropriate services). Submited answers (form parameter) are passed to
     * DTO object. ScoreCalculation service is used for calculating scores. Dto object is converted
     * back to TestKC object and is stored to database.
     * @param form Dto object containing answers submitted by User.
     * @param testId Test ID for test which has been submitted.
     * @param principal User in the session.
     * @return ModelAndView bound to resulttest.html
     */

    @PostMapping("/user/result/test{testId}")
    @ResponseBody
    public ModelAndView getResults(@ModelAttribute("form") DtoPropertiesForm form,
                                   @PathVariable("testId") Long testId,
                                   Principal principal){
        ModelAndView mav = new ModelAndView("resulttest");
        User user = userService.findByName(principal.getName());
        TestKC testKC = testService.findById(testId);
        DtoTestKC dtoTestKC = DtoConverter.toDto(testKC);
        DtoConverter.setAnswers(dtoTestKC, form);
        scoreCalculationService.getTestScore(dtoTestKC);
        testKC = DtoConverter.fromDto(user, dtoTestKC);
        testService.update(testKC);
        mav.addObject("dtoTestKC", dtoTestKC);
        return mav;
    }
}
