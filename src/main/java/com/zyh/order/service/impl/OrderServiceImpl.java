package com.zyh.order.service.impl;

import com.zyh.converter.OrderMaster2OrderDTOConverter;
import com.zyh.enums.OrderStatusEnum;
import com.zyh.enums.PayStatusEnum;
import com.zyh.enums.ResultStatusEnum;
import com.zyh.exception.SellException;
import com.zyh.order.dao.OrderDetailRepository;
import com.zyh.order.dao.OrderMasterRepository;
import com.zyh.order.dto.OrderDTO;
import com.zyh.order.entity.OrderDetail;
import com.zyh.order.entity.OrderMaster;
import com.zyh.order.service.OrderService;
import com.zyh.product.dao.ProductInfoRepository;
import com.zyh.product.dto.CartDTO;
import com.zyh.product.entity.ProductInfo;
import com.zyh.product.service.ProductInfoService;
import com.zyh.product.service.impl.ProductServiceImpl;
import com.zyh.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangyanghui
 * @Title OrderServiceImpl
 * @Description
 * @date 2018/7/20 0:24
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository omRepository;
    @Autowired
    private OrderDetailRepository odRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private ProductInfoService productService;


    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {

        String orderId = KeyUtil.getUniqueKey(0);
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        //1.查询商品（数量、价格）
        List<OrderDetail> orderDetails = orderDTO.getOrderDetails();
        for (OrderDetail orderDetail:orderDetails){
            ProductInfo product = productInfoRepository.findOne(orderDetail.getProductId());
            if(null == product){
                throw new SellException(ResultStatusEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价
            amount = product.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(amount);
            //3.1订单详情入库/orderDetail
            orderDetail.setDetailId(KeyUtil.getUniqueKey(4));
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(product,orderDetail);
            odRepository.save(orderDetail);
        }

        //3.2写入订单数据库（orderMaster）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);

        orderMaster.setOrderAmount(amount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        omRepository.save(orderMaster);
        //4.下单成功扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetails().stream()
                .map(e-> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster=omRepository.findOne(orderId);
        if(null==orderMaster){
            throw new SellException(ResultStatusEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetails=odRepository.findByOrderId(orderId);

        if(CollectionUtils.isEmpty(orderDetails)){
            throw new SellException(ResultStatusEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetails(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findAll(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasters=omRepository.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasters.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasters.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断状态
        if(!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())){
            log.info("订单取消：状态 {}, id {}",orderDTO.getOrderStatus(),orderDTO.getOrderId());
            throw new SellException(ResultStatusEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderDTO.setUpdateTime(new Date());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = omRepository.save(orderMaster);
        if(null==updateResult){
            log.info("订单更新失败...");
            throw new SellException(ResultStatusEnum.ORDER_UPDATE_ERROR);
        }
        //加库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetails())){
            log.info("此订单没有商品....");
            throw new SellException(ResultStatusEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetails().stream()
                .map(e->new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //如果已支付，需退款
        if(PayStatusEnum.SUCCESS.getCode().equals(orderDTO.getPayStatus())){
            //TODO
        }

        return orderDTO;
    }

    @Override
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        //判断状态
        if(!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())){
            log.info("订单状态 {}, id {}",orderDTO.getOrderStatus(),orderDTO.getOrderId());
            throw new SellException(ResultStatusEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = omRepository.save(orderMaster);
        if(null==updateResult){
            throw new SellException(ResultStatusEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        if(!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())){
            log.info("订单状态 {}, id {}",orderDTO.getOrderStatus(),orderDTO.getOrderId());
            throw new SellException(ResultStatusEnum.ORDER_STATUS_ERROR);
        }
        if(!PayStatusEnum.WAIT.getCode().equals(orderDTO.getPayStatus())){
            log.info("支付状态 {}, id {}",orderDTO.getPayStatus(),orderDTO.getOrderId());
            throw new SellException(ResultStatusEnum.PAY_STATUS_ERROR);
        }
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = omRepository.save(orderMaster);
        if(null==updateResult){
            throw new SellException(ResultStatusEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        Page<OrderMaster> orderMasters = omRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasters.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasters.getTotalElements());
    }
}
