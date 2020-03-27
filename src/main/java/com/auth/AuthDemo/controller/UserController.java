package com.auth.AuthDemo.controller;

import com.auth.AuthDemo.dto.DtoConverter;
import com.auth.AuthDemo.dto.DtoTestKC;
import com.auth.AuthDemo.dto.DtoUser;
import com.auth.AuthDemo.entity.User;
import com.auth.AuthDemo.service.UserService;
import com.auth.AuthDemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ModelAndView getTestDate (Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findByName(principal.getName());
        DtoUser dtoUser = DtoConverter.toDto(user);
        List<DtoTestKC> dtoTestKCList =  user.getUserTests().stream().map(u-> DtoConverter.toDto(user, u.getTestKC())).collect(Collectors.toList());//testService.findAll().stream().map(DtoConverter::toDto).collect(Collectors.toList());
        modelAndView.addObject("user", dtoUser);
        modelAndView.addObject("test", dtoTestKCList);
        modelAndView.setViewName("user");
        return modelAndView;
    }

}
