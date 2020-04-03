package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.dto.DtoUser;
import com.auth.AuthDemo.entity.TestKC;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.service.UserService;
import com.auth.AuthDemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class related to User functionality - new user registration, and
 * default page for regular users.
 */
@RestController
public class UserController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    /**
     * Mapping for default entry point for users after login. User for current session is
     * retrieved using Spring Security object Principal. All tests provided by admin are retrieved and passed
     * to user.html as allDtoTests. On HTML page only enabled tests are displayed for user (check is processed by
     * Thymeleaf). Also all user tests are retrieved and total User score.
     * @param principal User of the current session.
     * @return ModelAndView bound to user.html
     */
    @GetMapping("/user")
    public ModelAndView getTestDate (Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findByName(principal.getName());
        user.updateScore();
        List<DtoTestKC> allDtoTests = testService.findAll().stream().map(DtoConverter::toDto).collect(Collectors.toList());
        DtoUser dtoUser = DtoConverter.toDto(user);
        List<DtoTestKC> dtoTestKCList =  user.getUserTests().stream().map(u-> DtoConverter.toDto(user, u.getTestKC())).collect(Collectors.toList());//testService.findAll().stream().map(DtoConverter::toDto).collect(Collectors.toList());
        modelAndView.addObject("user", dtoUser);
        modelAndView.addObject("test", dtoTestKCList);
        modelAndView.addObject("allDtoTests", allDtoTests);
        modelAndView.setViewName("user");
        return modelAndView;
    }

    /**
     * Mapping for user registration. When users click Sign Up on login page, this method is called. Empty DtoUser
     * object is created. Object's fields are mapped to corresponding form inputs in registration form.
     * @return ModelAndView bound to userRegistration.html
     */
    @GetMapping("/registration")
    public ModelAndView newUserForm(){
        ModelAndView modelAndView = new ModelAndView("userRegistration");
        DtoUser dtoUser = new DtoUser();
        dtoUser.setName("");
        dtoUser.setPassword("");
        modelAndView.addObject("dtoUser", dtoUser);
        return modelAndView;
    }

    /**
     * Mapping for POST method of the user registration form. This method retrieves previously created object
     * and stores it to database. After storing user is redirected to home.html
     * @param dtoUser Object passed from user registration form.
     * @return ModelAndView bound to home.html
     */
    @PostMapping("/create")
    public ModelAndView create(DtoUser dtoUser){
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        dtoUser.setScore(BigDecimal.ZERO);
        User user = DtoConverter.fromDto(dtoUser);
        userService.create(user);
        return modelAndView;
    }

}
