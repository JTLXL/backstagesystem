package com.jtl.ssm.service;

import com.jtl.ssm.domain.Orders;

import java.util.List;

/**
 * @author JT.L
 * @date 2019/12/20 18:51:25
 * @description
 */
public interface IOrdersService {

    /**
     *
     * @param page
     * @param size
     * @return
     */
    public List<Orders> findAll(int page ,int size);
}
