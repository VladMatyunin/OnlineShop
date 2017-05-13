<#include "mainTemplate.ftl"/>
<#macro page_body>
<style>
    .badCount{
        border-color: red;
    }
</style>
<div id="all">
    <div id="content">
        <div class="container">

            <div class="col-md-12">
                <ul class="breadcrumb">
                    <li><a href="#">Home</a>
                    </li>
                    <li>Корзина</li>
                </ul>
            </div>

            <div class="col-md-9" id="basket">

                <div class="box">



                        <h1>Корзина</h1>
                        <p class="text-muted">У вас (<#if cart??>${cart.getTotalCount()}<#else>0</#if>) товаров в
                            корзине</p>
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th colspan="2">Товар</th>
                                    <th>Количество</th>
                                    <th>Цена</th>
                                    <th>Скидка</th>
                                    <th colspan="2">Сумма</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if cart??>
                                        <#list cart.productItems as pr>
                                        <tr id="PI${pr.product.id}">
                                            <td>
                                                <a href="#">
                                                    <img src="/load_image/${pr.product.id}" alt="White Blouse Armani">
                                                </a>
                                            </td>
                                            <td><a href="#">${pr.product.description.name}</a>
                                            </td>
                                            <td>
                                                <button class="btn btn-default"
                                                        onclick="changeNumber(${pr.product.id},-1)"><i
                                                        class="fa fa-chevron-left"></i></button>
                                                <input type="number" value="${pr.number}" class="form-control" disabled
                                                       id="pr${pr.product.id}">
                                                <button class="btn btn-default"
                                                        onclick="changeNumber(${pr.product.id},1)"><i
                                                        class="fa fa-chevron-right"></i></button>
                                            </td>
                                            <td>${pr.product.description.cost}</td>
                                            <td>$0.00</td>
                                            <td>$246.00</td>
                                            <td><a href="" onclick="deleteProduct(${pr.product.id})"><i class="fa fa-trash-o"></i></a>
                                            </td>
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th colspan="5">Total</th>
                                    <th colspan="2">$446.00</th>
                                </tr>
                                </tfoot>
                            </table>

                        </div>
                        <!-- /.table-responsive -->

                        <div class="box-footer">
                            <div class="pull-left">
                                <a href="/catalog/1" class="btn btn-default"><i class="fa fa-chevron-left"></i>Продолжить
                                    покупки</a>
                            </div>
                            <div class="pull-right">
                                <button class="btn btn-default"onclick="location.href='/cart'"><i class="fa fa-refresh"></i> Обновить корзину</button>
                                <button type="submit" class="btn btn-primary" onclick="location.href='/order'">Сделать заказ <i
                                        class="fa fa-chevron-right"></i>
                                </button>
                            </div>
                        </div>


                </div>
                <!-- /.box -->


                <div class="row same-height-row">
                    <div class="col-md-3 col-sm-6">
                        <div class="box same-height">
                            <h3>Вам могут понравиться эти товары...</h3>
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="product same-height">
                            <div class="flip-container">
                                <div class="flipper">
                                    <div class="front">
                                        <a href="detail.html">
                                            <img src="img/product2.jpg" alt="" class="img-responsive">
                                        </a>
                                    </div>
                                    <div class="back">
                                        <a href="detail.html">
                                            <img src="img/product2_2.jpg" alt="" class="img-responsive">
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <a href="detail.html" class="invisible">
                                <img src="img/product2.jpg" alt="" class="img-responsive">
                            </a>
                            <div class="text">
                                <h3>Fur coat</h3>
                                <p class="price">$143</p>
                            </div>
                        </div>
                        <!-- /.product -->
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="product same-height">
                            <div class="flip-container">
                                <div class="flipper">
                                    <div class="front">
                                        <a href="detail.html">
                                            <img src="img/product1.jpg" alt="" class="img-responsive">
                                        </a>
                                    </div>
                                    <div class="back">
                                        <a href="detail.html">
                                            <img src="img/product1_2.jpg" alt="" class="img-responsive">
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <a href="detail.html" class="invisible">
                                <img src="img/product1.jpg" alt="" class="img-responsive">
                            </a>
                            <div class="text">
                                <h3>Fur coat</h3>
                                <p class="price">$143</p>
                            </div>
                        </div>
                        <!-- /.product -->
                    </div>


                    <div class="col-md-3 col-sm-6">
                        <div class="product same-height">
                            <div class="flip-container">
                                <div class="flipper">
                                    <div class="front">
                                        <a href="detail.html">
                                            <img src="img/product3.jpg" alt="" class="img-responsive">
                                        </a>
                                    </div>
                                    <div class="back">
                                        <a href="detail.html">
                                            <img src="img/product3_2.jpg" alt="" class="img-responsive">
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <a href="detail.html" class="invisible">
                                <img src="img/product3.jpg" alt="" class="img-responsive">
                            </a>
                            <div class="text">
                                <h3>Fur coat</h3>
                                <p class="price">$143</p>

                            </div>
                        </div>
                        <!-- /.product -->
                    </div>

                </div>


            </div>
            <!-- /.col-md-9 -->

            <div class="col-md-3">
                <div class="box" id="order-summary">
                    <div class="box-header">
                        <h3>Order summary</h3>
                    </div>
                    <p class="text-muted">Shipping and additional costs are calculated based on the values you have
                        entered.</p>

                    <div class="table-responsive">
                        <table class="table">
                            <tbody>
                            <tr>
                                <td>Order subtotal</td>
                                <th>$446.00</th>
                            </tr>
                            <tr>
                                <td>Shipping and handling</td>
                                <th>$10.00</th>
                            </tr>
                            <tr>
                                <td>Tax</td>
                                <th>$0.00</th>
                            </tr>
                            <tr class="total">
                                <td>Total</td>
                                <th>$456.00</th>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                </div>


                <div class="box">
                    <div class="box-header">
                        <h4>Coupon code</h4>
                    </div>
                    <p class="text-muted">If you have a coupon code, please enter it in the box below.</p>
                    <form>
                        <div class="input-group">

                            <input type="text" class="form-control">

                            <span class="input-group-btn">

					<button class="btn btn-primary" type="button"><i class="fa fa-gift"></i></button>

				    </span>
                        </div>
                        <!-- /input-group -->
                    </form>
                </div>

            </div>
            <!-- /.col-md-3 -->

        </div>
        <!-- /.container -->
    </div>
    <!-- /#content -->

</div>
<script>
    function changeNumber(id, count) {

        $("#pr"+id).removeClass("badCount");
        $.ajax({
            url: "/cart/count",
            type: "POST",
            data: {
                productId: id,
                count: count
            },
            success: function (status) {
                if (!status) {
                    $("#pr"+id).addClass("badCount");
                }
                else {
                    $("#pr"+id).val(function(i, oldval) {
                        return parseInt( oldval, 10)+count;
                    });
                }
            }

        });
    }
    function deleteProduct(prId) {
        $("#PI"+prId).remove();
        $.ajax({
            url: "/cart/remove",
            type: "POST",
            data:{prId: prId}
        });
    }
</script>
<!-- /#all -->
</#macro>

<@display_page></@display_page>