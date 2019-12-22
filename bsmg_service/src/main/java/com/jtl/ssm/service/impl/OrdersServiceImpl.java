package com.jtl.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.jtl.ssm.dao.IOrdersDao;
import com.jtl.ssm.domain.Orders;
import com.jtl.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JT.L
 * @date 2019/12/20 18:52:43
 * @description
 */
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) {
        //参数pageNum是页码值 参数pageSize是每页显示条数
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(Integer ordersId) {
        return ordersDao.findById(ordersId);
    }
}
