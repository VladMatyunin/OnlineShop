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
                    <h1>Раздел склады</h1>
                    <p>Операции с доступными складами, изменение количества товара</p>
                </div>

                <div class="row products">
                    <#if storage??>
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>id</th>
                                    <th>Город</th>
                                    <th>Адрес</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#list storage as st>
                                    <tr id="ST${st.id}">
                                        <td><a href="#">${st.id}</a>
                                        </td>
                                        <td>
                                        ${st.city}
                                        </td>
                                        <td>${st.address}</td>
                                        <td><a href="/admin/storage/${st.id}"><i class="fa fa-pencil"></i></a></td>
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
</#macro>
<@display_page></@display_page>
