<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏sidebar-->
<#include "../common/nav.ftl">
<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fliud">
            <div class="row clearfix">
            <#--订单信息-->
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单号</th>
                            <th>总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.orderAmount}元</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            <#--订单详情-->
                <div class="col-md-12 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>商品名称</th>
                            <th>单价</th>
                            <th>数量</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTO.getOrderDetails() as details>
                        <tr>
                            <td>${details.productId}</td>
                            <td>${details.productName}</td>
                            <td>${details.productPrice}元</td>
                            <td>${details.productQuantity}</td>
                            <td>${details.productPrice * details.productQuantity}元</td>

                        </tr>
                        </#list>

                        </tbody>
                    </table>
                </div>
            <#--操作-->
                <div class="col-md-12 column">
                <#if orderDTO.getOrderStatusEnum().getMessage() == "新订单">
                    <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
                </#if>

                </div>

        </div>
    </div>
</div>

</body>
</html>