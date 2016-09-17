package com.lotus.core.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 模块身体加载
 * @author wyy
 */
@Controller
@RequestMapping("/frame")
public class FrameController {

    /**
     * 商品的身体
     * @return
     */
    @RequestMapping("/product_main.do")
    public String productMain(){
        return "frame/product_main";
    }

    /**
     * 商品的左身体
     * @return
     */
    @RequestMapping("/product_left.do")
    public String productLeft(){
        return "frame/product_left";
    }

    /**
     * 订单的身体
     * @return
     */
    @RequestMapping("/order_main.do")
    public String orderMain(){
        return "frame/order_main";
    }

    /**
     * 订单的左身体
     * @return
     */
    @RequestMapping("/order_left.do")
    public String orderLeft(){
        return "frame/order_left";
    }
}
