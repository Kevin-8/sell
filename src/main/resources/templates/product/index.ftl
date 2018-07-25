<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏sidebar-->
<#include "../common/nav.ftl">
<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">

                <div class="col-md-2 column">
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label>商品名称</label>
                            <input type="text" name="productName" class="form-control" value="${(productInfo.productName)!""}" />
                            <label>商品单价</label>
                            <input type="text" name="productPrice" class="form-control" value="${(productInfo.productPrice)!""}" />
                            <label>库存</label>
                            <input type="number" name="productStock" class="form-control" value="${(productInfo.productStock)!""}" />
                            <label>描述</label>
                            <input type="text" name="productDescription" class="form-control" value="${(productInfo.productDescription)!""}" />
                            <label>图片</label><br>
                            <img width="130" height="130" src="${(productInfo.productIcon)!""}" alt="">
                            <input type="text" name="productIcon" class="form-control" value="${(productInfo.productIcon)!""}" />
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                                <#list productCategory as category>
                                    <option value="${category.categoryType}"
                                        <#if (productInfo.categoryType)?? &&
                                productInfo.categoryType == category.categoryType>
                                            selected>
                                    </#if>
                                    ${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <input type="hidden" name="productId" value="${(productInfo.productId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>