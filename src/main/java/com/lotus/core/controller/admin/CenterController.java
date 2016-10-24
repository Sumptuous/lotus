package com.lotus.core.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author wyy
 */
@Controller
@RequestMapping("center")
public class CenterController {

    @RequestMapping("/index.do")
    public String index(String name, Date birthday){
        return "index";
    }

    @RequestMapping("/top.do")
    public String top(String name, Date birthday){
        return "top";
    }

    @RequestMapping("/main.do")
    public String main(String name, Date birthday){
        return "main";
    }

    @RequestMapping("/left.do")
    public String left(String name, Date birthday){
        return "left";
    }

    @RequestMapping("/right.do")
    public String right(String name, Date birthday){
        return "right";
    }
}
