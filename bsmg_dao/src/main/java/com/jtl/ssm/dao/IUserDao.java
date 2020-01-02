package com.jtl.ssm.dao;

import com.jtl.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author JT.L
 * @date 2020/1/1 16:02:44
 * @description
 */
@Repository
public interface IUserDao {
    /**
     * 通过名字查找数据库中的Users对象
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many=@Many(select = "com.jtl.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username);
}
