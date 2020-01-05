package com.jtl.ssm.service;

import com.jtl.ssm.domain.Permission;
import com.jtl.ssm.domain.Role;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/3 15:54:32
 * @description
 */
public interface IRoleService {
    /**
     * 查询所有角色信息
     * @return
     */
    List<Role> findAll();

    void save(Role role);

    /**
     * 根据roleId查询role
     * @param roleId
     * @return
     */
    Role findById(Integer roleId);

    /**
     * 根据roleId查询可以添加的所有权限
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermissions(Integer roleId);

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionIds
     */
    void addPermissionToRole(Integer roleId, Integer[] permissionIds);
}
