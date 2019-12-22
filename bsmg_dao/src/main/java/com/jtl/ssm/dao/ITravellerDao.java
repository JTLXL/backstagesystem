package com.jtl.ssm.dao;

import com.jtl.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author JT.L
 * @date 2019/12/22 15:27:06
 * @description
 */
public interface ITravellerDao {
    /**
     * 通过中间表查询出travellers
     * @param OrdersId
     * @return
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{OrdersId})")
    List<Traveller> findByOrdersId(Integer OrdersId);
}
