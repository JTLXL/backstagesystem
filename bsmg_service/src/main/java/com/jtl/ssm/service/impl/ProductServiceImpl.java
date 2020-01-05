package com.jtl.ssm.service.impl;

import com.jtl.ssm.dao.IProductDao;
import com.jtl.ssm.domain.Product;
import com.jtl.ssm.domain.Usera;
import com.jtl.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JT.L
 * @date 2019/12/19 15:33:18
 * @description
 */
@Service
@Transactional //开启事务 for save操作
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    @Override
    public List<Usera> findUser() {
        return productDao.findUser();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
