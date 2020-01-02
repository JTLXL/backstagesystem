package com.jtl.ssm.controller;

import com.jtl.ssm.domain.Product;
import com.jtl.ssm.domain.Usera;
import com.jtl.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Properties;

/**
 * @author JT.L
 * @date 2019/12/19 16:32:27
 * @description
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * .do只是个人习惯
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList", ps);
        mv.setViewName("product-list1");
        return mv;
    }

    /**
     * 产品添加
     *
     * @param product
     */
    @RequestMapping("/save.do")
    public String save(Product product) {
        System.out.println("*****" + product.getProductStatus() + "********");
        productService.save(product);
        return "redirect:findAll.do";
    }

    @RequestMapping("/finduser.do")
    public void finduser() throws Exception {
        List<Usera> usera = productService.findUser();
        System.out.println(usera);
        for (Usera usera1 : usera) {
            System.out.println(usera1);
        }

    }

}
