package com.jtl.ssm.service;

import com.jtl.ssm.domain.Permission;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/3 16:40:00
 * @description
 */
public interface IPermissionService {
    List<Permission> findAll();

    void save(Permission permission);
}
