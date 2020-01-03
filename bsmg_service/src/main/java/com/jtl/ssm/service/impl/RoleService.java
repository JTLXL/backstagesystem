package com.jtl.ssm.service.impl;

import com.jtl.ssm.dao.IRoleDao;
import com.jtl.ssm.domain.Role;
import com.jtl.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/3 15:54:42
 * @description
 */
@Service
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
}
