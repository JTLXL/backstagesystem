package com.jtl.ssm.dao;

import com.jtl.ssm.domain.Member;
import com.jtl.ssm.domain.Orders;
import com.jtl.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JT.L
 * @date 2019/12/20 18:53:49
 * @description
 */
@Repository
public interface IOrdersDao {

    /**
     * 查询订单所有信息（关联查询还需要查到产品的信息设计到两个表）
     *
     * @return
     */
    @Select("select * from orders")
    @Results({@Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", javaType = Product.class, one = @One(select = "com.jtl.ssm.dao.IProductDao.findById"))
    })
    public List<Orders> findAll();

    @Select("select * from orders where id=#{ordersId}")
    @Results({@Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", javaType = Product.class, one = @One(select = "com.jtl.ssm.dao.IProductDao.findById")),
            @Result(column = "memberId", property = "member", javaType = Member.class, one = @One(select = "com.jtl.ssm.dao.IMemberDao.findById")),
            @Result(column = "id", property = "travellers", javaType = java.util.List.class, many = @Many(select = "com.jtl.ssm.dao.ITravellerDao.findByOrdersId")),
    })
    public Orders findById(Integer ordersId);
}
