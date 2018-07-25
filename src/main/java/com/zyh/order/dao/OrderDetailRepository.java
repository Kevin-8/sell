package com.zyh.order.dao;

import com.zyh.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhangyanghui
 * @Title OrderDetailRepository
 * @Description
 * @date 2018/7/19 23:35
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);
}
