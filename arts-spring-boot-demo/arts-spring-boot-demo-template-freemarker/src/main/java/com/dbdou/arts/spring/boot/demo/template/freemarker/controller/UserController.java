package com.dbdou.arts.spring.boot.demo.template.freemarker.controller;

import com.dbdou.arts.spring.boot.demo.template.freemarker.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dentalulcer
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public ModelAndView login(User user, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        mv.addObject(user);
        mv.setViewName("redirect:/");

        request.getSession().setAttribute("user", user);
        return mv;
    }


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("page/login");
    }

}
