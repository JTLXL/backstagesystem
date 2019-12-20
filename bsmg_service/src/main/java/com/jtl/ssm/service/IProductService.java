package com.jtl.ssm.service;

import com.jtl.ssm.domain.Product;
import com.jtl.ssm.domain.Usera;

import java.util.List;

/**
 * @author JT.L
 * @date 2019/12/19 15:32:36
 * @description
 */
public interface IProductService {
    /**
     * 查询所有的产品信息
     *
     * @return
     * @throws Exception
     */
    public List<Product> findAll() throws Exception;

    public List<Usera> findUser();

    public void save(Product product);

}
