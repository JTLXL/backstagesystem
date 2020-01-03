package com.jtl.ssm.dao;

import com.jtl.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/2 12:50:04
 * @description
 */
public interface IRoleDao {

    /**
     * 根据用户id查询出所有对应的角色
     *
     * @param userId
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.jtl.ssm.dao.IPermissionDao.findPermissionsByRoleId")),
    })
    List<Role> findRoleByUserId(Integer userId);

    @Select("select * from role")
    List<Role> findAll();

    /**
     * 添加角色
     *
     * @param role
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);
}
