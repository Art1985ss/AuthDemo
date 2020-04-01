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

@RestController
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

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

    @GetMapping("/registration")
    public ModelAndView newUserForm(){
        ModelAndView modelAndView = new ModelAndView("userRegistration");
        DtoUser dtoUser = new DtoUser();
        dtoUser.setName("");
        dtoUser.setPassword("");
        modelAndView.addObject("dtoUser", dtoUser);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(DtoUser dtoUser){
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        dtoUser.setScore(BigDecimal.ZERO);
        User user = DtoConverter.fromDto(dtoUser);
        userService.create(user);
        return modelAndView;
    }

}
