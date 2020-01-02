package com.jtl.ssm.dao;

import com.jtl.ssm.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/2 12:50:04
 * @description
 */
public interface IRoleDao {

    /** 根据用户id查询出所有对应的角色
     * @param userId
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    List<Role> findRoleByUserId(Integer userId);
}
