<#include "mainTemplate.ftl"/>
<#include "category.ftl"/>
<#macro page_body>
<div id="all">

    <div id="content">
        <div class="container">

            <div class="col-md-12">
                <ul class="breadcrumb">
                    <li><a href="#">Home</a>
                    </li>
                    <li>${currentCategory.name}</li>
                </ul>
            </div>

            <div class="col-md-3">
                <!-- *** MENUS AND FILTERS ***
_________________________________________________________ -->
                <div class="panel panel-default sidebar-menu">

                    <div class="panel-heading">
                        <h3 class="panel-title">Категории</h3>
                    </div>


                    <@categ currentCategory.id></@categ>
                </div>

                <div class="panel panel-default sidebar-menu">

                    <div class="panel-heading">
                        <h3 class="panel-title">Брэнды</h3>
                    </div>

                    <div class="panel-body">

                        <form action="/catalog/search_brands" method="post">
                            <div class="form-group">
                                <#if brands??>
                                <#list brands as b>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="brands" id="b${b.id}" value="${b.id}">${b.name} (10)
                                    </label>
                                    <input type="number" hidden name="currentCategory" value="${currentCategory.id}">
                                </div>
                                </#list>
                                </#if>
                                <script>
                                    var brands = [<#list brandIds as b>${b},</#list>];
                                    for(var i=0; i<brands.length; i++){
                                        $("#b"+brands[i])[0].checked = true;
                                    }
                                </script>
                            </div>

                            <button class="btn btn-default btn-sm btn-primary" type="submit"><i class="fa fa-pencil"></i> Apply</button>

                        </form>

                    </div>
                </div>
                <!-- *** MENUS AND FILTERS END *** -->

                <div class="banner">
                    <a href="#">
                        <img src="/img/banner.jpg" alt="sales 2014" class="img-responsive">
                    </a>
                </div>
            </div>

            <div class="col-md-9">
                <div class="box">
                    <h1>${currentCategory.name}</h1>
                    <p>${currentCategory.description}</p>
                </div>

                <div class="box info-bar">
                    <div class="row">
                        <#--<div class="col-sm-12 col-md-4 products-showing">-->
                            <#--Showing <strong>12</strong> of <strong>25</strong> products-->
                        <#--</div>-->

                        <div class="col-sm-12 col-md-8  products-number-sort">
                            <form class="form-inline" action="/catalog/search" method="post">

                                <input type="text" name="minCost" class="form-control mb-2 mr-sm-2 mb-sm-0" id="inlineFormInput" placeholder="Цена от" value="${minPrice}">

                                <div class="input-group mb-2 mr-sm-2 mb-sm-0">

                                    <input type="text" class="form-control" name="maxCost" id="inlineFormInputGroup" value="${maxPrice}" placeholder="Цена до">
                                </div>
                                <input type="number" hidden name="currentCategory" value="${currentCategory.id}">



                                <button type="submit" class="btn btn-primary">Поиск</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="row products">
                    <#if catalog??>
                <#list catalog as pr>
                    <div class="col-md-4 col-sm-6">
                        <div class="product">
                            <div class="flip-container">
                                <div class="flipper">
                                    <div class="front">
                                        <a href="/catalog${pr.product.id}/details">
                                            <img src="/load_image/${pr.product.id}" alt="" class="img-responsive">
                                        </a>
                                    </div>
                                    <div class="back">
                                        <a href="/catalog${pr.product.id}/details">
                                            <img src="/load_image/${pr.product.id}" alt="" class="img-responsive">
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <a href="#" onclick="loadDetails(${pr.product.id})" class="invisible">
                                <img src="/img/product1.jpg" alt="" class="img-responsive">
                            </a>
                            <div class="text">
                                <h3><a href="#" onclick="loadDetails(${pr.product.id})">${pr.product.description.name}</a></h3>
                                <p class="price">${pr.product.cost}|<#if pr.number gt 0>${pr.number}<#else>Нет в наличии</#if></p>
                                <p class="buttons">
                                    <a onclick="loadDetails(${pr.product.id})" class="btn btn-default">View detail</a>
                                    <a href="#" class="btn btn-primary" onclick="addToCart(${pr.product.id})"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                </p>
                            </div>
                            <!-- /.text -->
                        </div>
                        <!-- /.product -->
                    </div>
                </#list>
                    </#if>


                </div>
                <!-- /.products -->

                <div class="pages">

                    <#--<p class="loadMore">-->
                        <#--<a href="#" class="btn btn-primary btn-lg"><i class="fa fa-chevron-down"></i> Load more</a>-->
                    <#--</p>-->

                    <#--<ul class="pagination">-->
                        <#--<li><a href="#">&laquo;</a>-->
                        <#--</li>-->
                        <#--<li class="active"><a href="#">1</a>-->
                        <#--</li>-->
                        <#--<li><a href="#">2</a>-->
                        <#--</li>-->
                        <#--<li><a href="#">3</a>-->
                        <#--</li>-->
                        <#--<li><a href="#">4</a>-->
                        <#--</li>-->
                        <#--<li><a href="#">5</a>-->
                        <#--</li>-->
                        <#--<li><a href="#">&raquo;</a>-->
                        <#--</li>-->
                    <#--</ul>-->
                </div>


            </div>
            <!-- /.col-md-9 -->
        </div>
        <!-- /.container -->
    </div>
    <!-- /#content -->
    <script>
        function addToCart(id) {
            $.ajax({
                url: "/catalog/add_product",
                type: "POST",
                data: {productId:id},
                success: function (num) {
                    $("#cartNum").html(num);
                }
            });
        }
        function loadDetails(id) {
            $.ajax({
                url: "/catalog/"+id+"/details",
                type: "GET",
                success: function(modal){
                    $("#updateModal").remove();
                    $("#content").append(modal);
                    $("#updateModal").modal('show');
                }
            })

        }
    </script>
</#macro>
<@display_page></@display_page>
