package com.jtl.ssm.service;

import com.jtl.ssm.domain.Role;
import com.jtl.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/1 15:33:17
 * @description
 */
public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll();

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(Integer id);

    /**
     * 根据用户id查询可以添加的角色
     *
     * @param userId
     * @return
     */
    List<Role> findOtherRoles(Integer userId);

    /**
     * 给用户添加角色
     *
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(Integer userId, Integer[] roleIds);
}
