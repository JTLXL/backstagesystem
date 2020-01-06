package com.jtl.ssm.service;

import com.jtl.ssm.domain.SysLog;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/6 14:24:01
 * @description
 */
public interface ISysLogService {
    /**
     * 保存日志信息
     *
     * @param sysLog
     */
    void save(SysLog sysLog);

    /**
     * 查询所有日志信息
     * @return
     */
    List<SysLog> findAll();

}
