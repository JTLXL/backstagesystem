package com.jtl.ssm.service.impl;

import com.jtl.ssm.dao.IPermissionDao;
import com.jtl.ssm.domain.Permission;
import com.jtl.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/3 16:40:13
 * @description
 */
@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

}
