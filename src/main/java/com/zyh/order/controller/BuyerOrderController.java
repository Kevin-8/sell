package com.zyh.order.controller;

import com.zyh.converter.OrderForm2OrderDTOConverter;
import com.zyh.enums.ResultStatusEnum;
import com.zyh.exception.SellException;
import com.zyh.order.dto.OrderDTO;
import com.zyh.order.form.OrderForm;
import com.zyh.order.service.BuyerService;
import com.zyh.order.service.OrderService;
import com.zyh.utils.ResultVOUtil;
import com.zyh.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyanghui
 * @Title BuyerOrderController
 * @Description
 * @date 2018/7/20 13:19
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO createOrder(@Valid OrderForm orderForm, BindingResult result) {
        //getDefaultMessage为form表单具体返回的错误信息
        if (result.hasErrors()) {
            log.error("[创建订单]参数不正确,OrderForm={}", orderForm);
            throw new SellException(ResultStatusEnum.PARAM_ERROR.getCode()
                    , result.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.conver(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())) {
            log.info("购物车是空...");
            throw new SellException(ResultStatusEnum.CATE_EMPTY);
        }
        OrderDTO createResult = orderService.createOrder(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO getOrderList(@RequestParam("openid") String openid,
                                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "size",defaultValue = "5") Integer size){

        if(StringUtils.isEmpty(openid)){
            log.error("【订单列表】买家微信openid不能为空...");
            throw new SellException(ResultStatusEnum.OPENID_EMPTY);
        }
        PageRequest pageRequest=new PageRequest(page,size);
        Page<OrderDTO> orderDTOList = orderService.findAll(openid,pageRequest);
        return ResultVOUtil.success(orderDTOList.getContent());
    }


    //订单详情
    @GetMapping("/detail")
    public ResultVO getOrderDetail(@RequestParam("openid") String openid,
                                   @RequestParam("orderId") String orderId){
        if(StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)){
            log.info("【订单详情】买家微信openid或者订单号不能为空");
            throw new SellException(ResultStatusEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
        return ResultVOUtil.success(orderDTO);
    }
    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancelOrder(@RequestParam("openid") String openid,
                                @RequestParam("orderId") String orderId){

        if(StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)){
            log.info("【订单取消】买家微信openid或者订单号不能为空");
            throw new SellException(ResultStatusEnum.PARAM_ERROR);
        }
        buyerService.cancelOrder(openid,orderId);
        return ResultVOUtil.success();
    }


}
