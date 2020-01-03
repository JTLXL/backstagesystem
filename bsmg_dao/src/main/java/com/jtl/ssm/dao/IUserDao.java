package com.jtl.ssm.dao;

import com.jtl.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/1 16:02:44
 * @description
 */
@Repository
public interface IUserDao {
    /**
     * 通过名字查找数据库中的Users对象
     *
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.jtl.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findByUsername(String username);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.jtl.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findById(Integer id);
}
