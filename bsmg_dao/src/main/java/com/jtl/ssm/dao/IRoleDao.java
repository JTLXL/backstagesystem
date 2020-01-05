package com.jtl.ssm.dao;

import com.jtl.ssm.domain.Permission;
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

    /**
     * 根据roleId查询role
     *
     * @param roleId
     * @return
     */
    @Select("select * from role where id=#{roleId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.jtl.ssm.dao.IPermissionDao.findPermissionsByRoleId"))
    })
    Role findById(Integer roleId);

    /**
     * 根据roleId查询可以添加的所有权限
     *
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(Integer roleId);

    /**
     * 给角色添加权限
     *
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
}
