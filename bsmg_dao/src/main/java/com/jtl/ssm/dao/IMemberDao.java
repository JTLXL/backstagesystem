package com.jtl.ssm.dao;

import com.jtl.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @author JT.L
 * @date 2019/12/22 15:09:32
 * @description
 */
public interface IMemberDao {

    @Select("select * from member where id = #{id}")
    public Member findById(Integer id);
}
