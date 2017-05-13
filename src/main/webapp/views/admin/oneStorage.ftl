<#include "../mainTemplate.ftl"/>
<#macro page_body>
<div id="all">

    <div id="content">
        <div class="container">

            <div class="col-md-12">
                <ul class="breadcrumb">
                    <li><a href="#">Home</a>
                    </li>
                    <li>Пользователи</li>
                </ul>
            </div>

            <div class="col-md-3">
                <!-- *** MENUS AND FILTERS ***
_________________________________________________________ -->
                <div class="panel panel-default sidebar-menu">

                    <div class="panel-heading">
                        <h3 class="panel-title">Меню</h3>
                    </div>
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked category-menu">

                            <li id="users" >
                                <a href="/admin/users">Пользователи</a>
                            </li>
                            <li id="products" >
                                <a href="/admin/products">Товары</a>
                            </li>
                            <li id="storage" class="active">
                                <a href="/admin/storage">Склад</a>
                            </li>

                        </ul>
                    </div>
                </div>

            </div>

            <div class="col-md-9">
                <div class="box">
                    <h1>склад № ${storage.id}</h1>
                    <p>...</p>
                </div>
                <div class="box info-bar">
                    <div class="row">
                    <#--<div class="col-sm-12 col-md-4 products-showing">-->
                    <#--Showing <strong>12</strong> of <strong>25</strong> products-->
                    <#--</div>-->

                        <div class="col-sm-12 col-md-8  products-number-sort">

                            <form class="form-inline" action="/admin/storage/${storage.id}/add_product" method="post">

                                <input type="number" name="product_id" class="form-control mb-2 mr-sm-2 mb-sm-0" id="inlineFormInput" placeholder="Id продукта"/>

                                <div class="input-group mb-2 mr-sm-2 mb-sm-0">

                                    <input type="number" class="form-control" name="product_num" id="inlineFormInputGroup" placeholder="Количество"/>
                                </div>

                                <input type="submit" class="btn btn-primary" value="Добавить">
                            </form>

                        </div>
                    </div>
                </div>

                <div class="row products">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Название продукта</th>
                                    <th>количество продукта</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#list storage.productItems as pr_items>
                                    <tr id="ST${pr_items.product.id}">
                                        <td><a href="#">${pr_items.product.id}</a>
                                        </td>
                                        <td>
                                        ${pr_items.product.description.name}
                                        </td>
                                        <td><input id="pr_count" class="form-control" value="${pr_items.number}"></td>
                                        <td><a onclick="saveProductItem(${pr_items.id})"><i class="fa fa-save"></i></a></td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>

                        </div>
                        <!-- /.table-responsive -->


                </div>
                <!-- /.products -->
            </div>
            <!-- /.col-md-9 -->
        </div>
        <!-- /.container -->
    </div>
    <!-- /#content -->
</div>
</#macro>
<script>
    function saveProductItem(itemId) {
        $.ajax({
            url: "/admin/storage/save/"+itemId,
            type: 'POST',
            data: {
                num: $("#pr_count").val()
            },
            success: function () {
                alert('Сохранено')
            }
        });
    }
</script>
<@display_page></@display_page>
