package com.dbdou.spring.boot.demo.controller;

import com.dbdou.spring.boot.demo.constant.Status;
import com.dbdou.spring.boot.demo.exception.JsonException;
import com.dbdou.spring.boot.demo.exception.PageException;
import com.dbdou.spring.boot.demo.model.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dentalulcer
 */
@RestController
public class TestController {

    @GetMapping("/json")
    @ResponseBody
    public ApiResponse jsonException() {
        throw new JsonException(Status.UNKNOWN_ERROR);
    }

    @GetMapping("/page")
    public ModelAndView pageException() {
        throw new PageException(Status.UNKNOWN_ERROR);
    }

}
