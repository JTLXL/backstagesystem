package com.jtl.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.jtl.ssm.domain.Orders;
import com.jtl.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author JT.L
 * @date 2019/12/20 18:49:00
 * @description
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    /**
     * 查询全部订单 --- 未分页
     *
     * @return
     */
    /*@RequestMapping("findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mv.addObject("ordersList",ordersList);
        mv.setViewName("orders-list");
        return mv;
    }*/
    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "5") int size) {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,size);
        // PageInfo就是一个分页bean 通过其构造方法最后得到的结果集是PageInfo对象中的一个list字段属性
        PageInfo pageInfo=new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

}
