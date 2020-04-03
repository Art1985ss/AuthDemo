package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoQuestion;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.dto.ListCreationDto;
import com.auth.AuthDemo.entity.Question;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.entity.UserTest;
import com.auth.AuthDemo.repository.QuestionRepository;
import com.auth.AuthDemo.service.QuestionService;
import com.auth.AuthDemo.service.TestService;
import com.auth.AuthDemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;


class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private TestService testService;
    @Mock
    private UserService userService;
    @Mock
    private Principal principal;
    @Mock
    private QuestionService questionService;

    @Mock
    private DtoConverter dtoConverter;

//    @Mock
    private ListCreationDto listCreationDto;

    final private Long testId = 16l;
    final private Long questionId = 16l;
    final private Long userId = 10l;
    final private Integer ansCount = 2;

    private List<String> answers = new ArrayList<>();
    private DtoQuestion dtoQuestion;
    private List<User> users = new ArrayList<>();

    private TestKC testKC;
    private User user;
    private DtoTestKC dtoTestKC;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setId(1L);
        user.setUserTests(new HashSet<UserTest>());
        testKC = new TestKC();
        testKC.setId(testId);
        testKC.setDurationMinutes(10);
        testKC.setName("First Test");
        testKC.setEnabled(false);
        testKC.setQuestionList(new ArrayList<>());
        testKC.setUserTests(new HashSet<>());

        dtoQuestion = new DtoQuestion();
        dtoQuestion.setId(1L);
        dtoQuestion.setQuestion("Question1");
        dtoQuestion.setAnswers(answers);
        dtoQuestion.setCorrectAnswer("First");
        dtoQuestion.setUserAnswer("Second");

        answers.add("First");
        answers.add("Second");

        listCreationDto = new ListCreationDto(2);
        listCreationDto.setAnswers(answers);

    }

    @Test
    void getAdminData() {

        UserTest userTest = new UserTest();
        Set<UserTest> userTestList = new HashSet<>();
        userTestList.add(userTest);

        User user = new User();
        user.setId(10L);
        user.setScore(BigDecimal.ONE);
        user.setName("John");
        user.setPassword("123");
        user.setUserTests(userTestList);

        List<User> userList = new ArrayList<>();
        userList.add(user);

        List<TestKC> testsList = new ArrayList<>();
        testsList.add(testKC);

        when(userService.findAll()).thenReturn(userList);
        when(testService.findAll()).thenReturn(testsList);

        final ModelAndView actual = adminController.getAdminData();
        assertViewName(actual, "admin");
    }

    @Test
    void deleteUser() {
        userService = Mockito.mock(UserService.class);
        final ModelAndView actual = adminController.deleteUser(userId);
        assertViewName(actual, "redirect:/admin/");
    }

    @Test
    void testGetAdminData() {
        when(testService.findById(testId)).thenReturn(testKC);
        final ModelAndView actual = adminController.getAdminData(testId);
        assertViewName(actual, "testmanage");
    }

    @Test
    void testNew() {
        final ModelAndView actual = adminController.testNew();
        assertViewName(actual, "createtest");
    }

    @Test
    void testCreate() {
        testService = Mockito.mock(TestService.class);
        dtoTestKC = new DtoTestKC();
        final ModelAndView actual = adminController.testCreate(dtoTestKC);
        assertViewName(actual, "testmanage");
    }

    @Test
    void deleteTest() {
        testService = Mockito.mock(TestService.class);
        final ModelAndView actual = adminController.deleteTest(testId);
        assertViewName(actual, "redirect:/admin/");
    }

    @Test
    void questionForm() {
        when(testService.findById(testId)).thenReturn(testKC);

        final ModelAndView actual = adminController.questionForm(testId, ansCount );
        assertViewName(actual, "question");
    }


    @Test
    void questionCreate() {


        questionService = Mockito.mock(QuestionService.class);
        when(testService.findById(testId)).thenReturn(testKC);

        final ModelAndView actual = adminController.questionCreate(dtoQuestion ,testId, listCreationDto);
        assertViewName(actual, "testmanage");

    }

    @Test
    void deleteQuestion() {
        questionService = Mockito.mock(QuestionService.class);
        final ModelAndView actual = adminController.deleteQuestion(testId , questionId);
        assertViewName(actual, "redirect:/admin/testmanage/test" + testId);
    }

    @Test
    void enableTest() {
        when(testService.findById(testId)).thenReturn(testKC);
        final ModelAndView actual = adminController.enableTest(testId);
        assertViewName(actual, "redirect:/admin");
    }

    @Test
    void disableTest() {

        when(testService.findById(testId)).thenReturn(testKC);
        final ModelAndView actual = adminController.disableTest(testId);
        assertViewName(actual, "redirect:/admin");
    }
}