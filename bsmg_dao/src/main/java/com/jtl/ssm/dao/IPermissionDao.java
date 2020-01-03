package com.jtl.ssm.dao;

import com.jtl.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/3 13:10:00
 * @description
 */
public interface IPermissionDao {
    /**
     * 通过roleId先去role_permission中间表查出permissionId，最后再通过permissionId去permission表中查出相关信息
     *
     * @param id
     * @return
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findPermissionsByRoleId(Integer id);

    @Select("select * from permission")
    List<Permission> findAll();

    /**
     * 添加一个权限信息
     *
     * @param permission
     */
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
}
