package com.jtl.ssm.controller;

import com.jtl.ssm.domain.Permission;
import com.jtl.ssm.domain.Role;
import com.jtl.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/3 15:52:11
 * @description
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    /**
     * 根据roleId查询role，并查询出可以添加的权限
     *
     * @return
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam("id") Integer roleId) {
        ModelAndView mv = new ModelAndView();
        // 根据roleId查询role
        Role role = roleService.findById(roleId);
        // 根据roleId查询可以添加的权限
        List<Permission> otherPermissions = roleService.findOtherPermissions(roleId);
        mv.addObject("role", role);
        mv.addObject("permissionList", otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    /**
     * 给角色添加权限
     *
     * @return
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam("roleId") Integer roleId, @RequestParam("ids") Integer[] permissionIds) {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }


}
