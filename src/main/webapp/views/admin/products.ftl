<#include "../mainTemplate.ftl"/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] >
<#macro page_body>
<div id="all" xmlns="http://java.sun.com/jsf/html">

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
                            <li id="products" class="active">
                                <a href="/admin/products">Товары</a>
                            </li>
                            <li id="storage">
                                <a href="/admin/storage">Склад</a>
                            </li>

                        </ul>
                    </div>
                </div>

            </div>

            <div class="col-md-9">
                <div class="box">
                    <h1>Раздел товары</h1>
                    <p>Добавление и изменение товаров</p>
                </div>
                <div class="box info-bar">
                    <div class="row">
                    <#--<div class="col-sm-12 col-md-4 products-showing">-->
                    <#--Showing <strong>12</strong> of <strong>25</strong> products-->
                    <#--</div>-->

                        <div class="col-sm-12 col-md-8  products-number-sort">

                            <@form.form cssClass="form-inline" action="/admin/products" method="post" commandName="productForm">

                                <@form.input type="text" path="name" cssClass="form-control mb-2 mr-sm-2 mb-sm-0" id="inlineFormInput" placeholder="Название"/>

                                <div class="input-group mb-2 mr-sm-2 mb-sm-0">

                                    <@form.input type="number" cssClass="form-control" path="cost" id="inlineFormInputGroup" placeholder="Цена"/>
                                </div>
                                <@form.textarea path="description"/>

                                <@form.select path="brand" >
                                <@form.options items=allbrands/>
                                </@form.select>
                                <@form.select path="category" >
                                <@form.options items=allcats/>
                                </@form.select>
                                <input type="submit" class="btn btn-primary">
                            </@form.form>

                        </div>
                    </div>
                </div>

                <div class="row products">
                    <#if products??>
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>id</th>
                                    <th>название</th>
                                    <th>цена</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#list products as pr>
                                    <tr id="PR${pr.id}">
                                        <td><a href="#">${pr.id}</a>
                                        </td>
                                        <td>
                                        ${pr.description.name}
                                        </td>
                                        <td>${pr.cost}</td>
                                        <td><a href="#" onclick="loadUpdateModal(${pr.id})"><i class="fa fa-pencil"></i></a></td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>

                        </div>
                        <!-- /.table-responsive -->
                    </#if>


                </div>
                <!-- /.products -->
            </div>
            <!-- /.col-md-9 -->
        </div>
        <!-- /.container -->
    </div>
    <!-- /#content -->
</div>
<script>
    function loadUpdateModal(id) {
        $.ajax({
            url: '/admin/products/update/'+id,
            type: 'GET',
            success: function (modal) {
                $("#updateModal").remove();
                $("#content").append(modal);
                $("#updateModal").modal('show');
            }
        });
    }
</script>
</#macro>
<@display_page></@display_page>
