package com.jtl.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.jtl.ssm.domain.Orders;
import com.jtl.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "5") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page, size);
        // PageInfo就是一个分页bean 通过其构造方法最后得到的结果集是PageInfo对象中的一个list字段属性
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    /*@RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id") Integer ordersId) {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }*/

    /**
     * 此处用的是restful风格
     * 使用这种请求方式记得修改web.xml里面的拦截配置为/不能用*.do
     * 因为/findById.do/${orders.id}请求的结尾是一个数字不是*.do
     * @param ordersId
     * @return
     */
    @RequestMapping("/findById.do/{id}")
    public ModelAndView findById(@PathVariable("id") Integer ordersId) {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
