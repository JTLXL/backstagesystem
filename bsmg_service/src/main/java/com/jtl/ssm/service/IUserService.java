package com.jtl.ssm.service;

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
}
