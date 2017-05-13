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

                            <li id="users" class="active">
                                <a href="/admin/users">Пользователи</a>
                            </li>
                            <li id="products">
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
                    <h1>Раздел пользователи</h1>
                    <p>Удаление-подтверждение пользователей</p>
                </div>

                <div class="row products">
                    <#if users??>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>id</th>
                                        <th>имя</th>
                                        <th>фамилия</th>
                                        <th>email</th>
                                        <th>подтвержден</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                            <#list users as us>
                                            <tr id="US${us.id}">
                                                <td><a href="#">${us.id}</a>
                                                </td>
                                                <td>
                                                    ${us.name}
                                                </td>
                                                <td>${us.surname}</td>
                                                <td>${us.email}</td>
                                                <td><input type="checkbox" ${us.accepted?then('checked','')} onchange="confirmUser(${us.id})"></td>
                                                <td><a href="#" onclick="deleteUser(${us.id})"><i class="fa fa-trash-o"></i></a></td>
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
    function deleteUser(id) {
        $("#US"+id).remove();
        $.ajax({
            url: '/admin/users/delete/'+id,
            type: 'GET'
        });
    }
    function confirmUser(id) {
        $.ajax({
            url: '/admin/users/confirm/'+id,
            type: 'GET'
        });
    }
</script>
</#macro>
<@display_page></@display_page>
