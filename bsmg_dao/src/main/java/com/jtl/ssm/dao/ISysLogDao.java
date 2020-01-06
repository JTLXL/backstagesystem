package com.jtl.ssm.dao;

import com.jtl.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/6 14:26:32
 * @description
 */
public interface ISysLogDao {

    /**
     * 保存日志信息
     *
     * @param sysLog
     */
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    /**
     * 查询所有日志信息
     *
     * @return
     */
    @Select("select * from syslog")
    List<SysLog> findAll();
}
