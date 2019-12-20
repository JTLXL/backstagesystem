package com.jtl.ssm.dao;

import com.jtl.ssm.domain.Product;
import com.jtl.ssm.domain.Usera;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JT.L
 * @date 2019/12/19 15:29:17
 * @description
 */
@Repository
public interface IProductDao {
    /**
     * 查询所有的产品信息
     *
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    @Select("select * from user")
    public List<Usera> findUser();

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product);
}
