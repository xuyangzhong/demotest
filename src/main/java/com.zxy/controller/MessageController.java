package com.zxy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: Yeliang
 * @Date: Create in 13:53 2018/9/7
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {
    public static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @RequestMapping(value = "/index")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        return mav;
    }
}
