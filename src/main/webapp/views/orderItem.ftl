<div id="updateModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Детали заказа</h4>
            </div>
        <div id="update-modal-body" class="modal-body">
        <#if order??>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th colspan="1">Товар</th>
                        <th>Количество</th>
                        <th>Цена</th>
                        <th>Скидка</th>
                        <th colspan="2">Сумма</th>
                    </tr>
                    </thead>
                    <tbody>
                            <#list order.products as pr>
                            <tr id="PI${pr.product.id}">
                                <#--<td>-->
                                    <#--<a href="#">-->
                                        <#--<img src="/load_image/${pr.product.id}" alt="White Blouse Armani">-->
                                    <#--</a>-->
                                <#--</td>-->
                                <td><a href="#">${pr.product.description.name}</a>
                                </td>
                                <td>
                                    ${pr.number}
                                </td>
                                <td>${pr.product.description.cost}</td>
                                <td>$0.00</td>
                                <td>$246.00</td>

                            </tr>
                            </#list>
                    </tbody>
                    <tfoot>
                    <tr>
                        <th colspan="5">Total</th>
                        <th colspan="2">$446.00</th>
                    </tr>
                    </tfoot>
                </table>

            </div>




        </#if>
        </div>
            <!-- /#content -->

        </div>
    </div>
</div>