package com.jtl.ssm.service;

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
}
