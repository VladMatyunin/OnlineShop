<#include "mainTemplate.ftl"/>
<#macro page_body>
<div id="all">

    <div id="content">
        <div class="container">

            <div class="col-md-12">

                <ul class="breadcrumb">
                    <li><a href="#">Home</a>
                    </li>
                    <li>My orders</li>
                </ul>

            </div>

            <div class="col-md-3">
                <!-- *** CUSTOMER MENU ***
_________________________________________________________ -->
                <div class="panel panel-default sidebar-menu">

                    <div class="panel-heading">
                        <h3 class="panel-title">Customer section</h3>
                    </div>

                    <div class="panel-body">

                        <ul class="nav nav-pills nav-stacked">
                            <li class="active">
                                <a href="customer-orders.html"><i class="fa fa-list"></i> My orders</a>
                            </li>
                            <li>
                                <a href="customer-wishlist.html"><i class="fa fa-heart"></i> My wishlist</a>
                            </li>
                            <li>
                                <a href="customer-account.html"><i class="fa fa-user"></i> My account</a>
                            </li>
                            <li>
                                <a href="index.html"><i class="fa fa-sign-out"></i> Logout</a>
                            </li>
                        </ul>
                    </div>

                </div>
                <!-- /.col-md-3 -->

                <!-- *** CUSTOMER MENU END *** -->
            </div>

            <div class="col-md-9" id="customer-orders">
                <div class="box">
                    <h1>My orders</h1>

                    <p class="lead">Your orders on one place.</p>
                    <p class="text-muted">If you have any questions, please feel free to <a href="contact.html">contact us</a>, our customer service center is working for you 24/7.</p>

                    <hr>

                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Номер</th>
                                <th>Дата</th>
                                <th>Сумма</th>
                                <th>Статус</th>
                                <th>Действие</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if orders??>
                            <#list orders as ord>
                            <tr>
                                <th>${ord.id}</th>
                                <td>${ord.dateCreated}</td>
                                <td>$ 150.00</td>
                                <td><span class="label label-info">${ord.orderStatus}</span>
                                </td>
                                <td><a href="#" onclick="loadDetails(${ord.id})" class="btn btn-primary btn-sm">Детали</a>
                                </td>
                            </tr>
                            </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.container -->
    </div>
    <script>
    function loadDetails(id) {
    $.ajax({
    url: "/order/"+id,
    type: "GET",
    success: function(modal){
    $("#updateModal").remove();
    $("#content").append(modal);
    $("#updateModal").modal('show');
    }
    })

    }
    </script>
    <!-- /#content -->
</#macro>
<@display_page></@display_page>