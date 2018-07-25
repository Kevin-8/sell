package com.zyh.order.dao;

import com.zyh.order.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhangyanghui
 * @Title OrderMasterRepository
 * @Description
 * @date 2018/7/19 23:33
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    //根据买家openID查询
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
