package com.jtl.ssm.service.impl;

import com.jtl.ssm.dao.ISysLogDao;
import com.jtl.ssm.domain.SysLog;
import com.jtl.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JT.L
 * @date 2020/1/6 14:24:57
 * @description
 */
@Service
@Transactional
public class SysServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }
}
