package com.lotus.core.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author wyy
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @RequestMapping("/test.do")
    public String test(String name, Date birthday){
        System.out.println(name);
        System.out.println(birthday);

        return "";
    }
}
