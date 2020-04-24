package com.dbdou.arts.spring.boot.demo.template.freemarker.controller;

import cn.hutool.core.util.ObjectUtil;
import com.dbdou.arts.spring.boot.demo.template.freemarker.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dentalulcer
 */
@Slf4j
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        User user = (User) request.getSession().getAttribute("user");
        if (ObjectUtil.isNull(user)) {
            mv.setViewName("redirect:/user/login");
        } else {
            mv.setViewName("page/index");
            mv.addObject(user);
        }

        return mv;
    }

}
