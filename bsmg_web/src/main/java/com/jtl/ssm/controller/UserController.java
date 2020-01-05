package com.jtl.ssm.controller;

import com.jtl.ssm.domain.Role;
import com.jtl.ssm.domain.UserInfo;
import com.jtl.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/2 16:30:25
 * @description
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos = userService.findAll();
        mv.addObject("userList", userInfos);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 得到当前正在操作用户的username,只有名为jtl用户能做save操作
     * 且username是authentication.principal的关键字，不能随便写成其他的字段
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username == 'jtl' ")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 查询指定id的用户
     * 这里也采用的restful风格
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById.do/{id}")
    public ModelAndView findById(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show1");
        return mv;
    }

    /**
     * 查询用户以及用户可以添加的角色
     *
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) Integer userId) {
        ModelAndView mv = new ModelAndView();
        // 1.根据用户id查询 用户
        UserInfo userInfo = userService.findById(userId);
        // 2.根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userId);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 给用户添加角色
     *
     * @return
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam("userId") Integer userId, @RequestParam("ids") Integer[] roleIds) {
        ModelAndView mv = new ModelAndView();
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }

    /**
     * 可以通过String+Model来改写ModelAndView方法
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/testModel")
    public String test(Model model, Integer id) {
        model.addAttribute("id", id);
        model.addAttribute("username", "jtl");
        return "testModel";
    }
}
