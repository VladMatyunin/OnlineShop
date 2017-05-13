<div id="updateModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Детали товара</h4>
            </div>
        <div id="update-modal-body" class="modal-body">
        <#if product??>

        <form action="/admin/products/update/${product.id}" method="post">
            <div class="row" id="productMain">
                <div class="col-sm-6">
                    <div id="mainImage">
                        <img src="/load_image/${product.id}" alt="" class="img-responsive">
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="box">
                        <h1 class="text-center">Название</h1>
                        <p class="goToDescription">
                            <input type="text" value="${product.description.name}" class="form-control" name="name">
                        </p>
                        <h1 class="text-center">Цена</h1>
                        <p class="price">
                            <input value="${product.description.cost}" class="form-control" name="cost">
                        </p>
                    </div>
                </div>

            </div>


            <div class="box" id="details">
                <p>
                <h4>Описание</h4>
                <p><textarea class="form-control" name="description">${product.description.description}</textarea></p>
            </div>
            <input type="submit" class="btn btn-info" value="Сохранить">
        </div>
            <!-- /#content -->
            </form>
        </#if>
        </div>
    </div>
</div>