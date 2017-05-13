<div id="updateModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Детали товара</h4>
                </div>
            <div id="update-modal-body" class="modal-body">
            <#if product??>


                <div class="row" id="productMain">
                    <div class="col-sm-6">
                        <div id="mainImage">
                            <img src="/load_image/${product.id}" alt="" class="img-responsive">
                        </div>

                        <div class="ribbon sale">
                            <div class="theribbon">SALE</div>
                            <div class="ribbon-background"></div>
                        </div>
                        <!-- /.ribbon -->

                        <div class="ribbon new">
                            <div class="theribbon">NEW</div>
                            <div class="ribbon-background"></div>
                        </div>
                        <!-- /.ribbon -->

                    </div>
                    <div class="col-sm-6">
                        <div class="box">
                            <h1 class="text-center">${product.description.name}</h1>
                            <p class="goToDescription"><a href="#details" class="scroll-to"></a>
                            </p>
                            <p class="price">${product.description.cost}</p>

                            <p class="text-center buttons">
                                <a href="#" class="btn btn-primary" onclick="addToCart(${product.id})"><i
                                        class="fa fa-shopping-cart" ></i> Add to
                                    cart</a>

                            </p>


                        </div>
                    </div>

                </div>


                <div class="box" id="details">
                    <p>
                    <h4>Product details</h4>
                    <p>${product.description.description}</p>
                    <h4>Material & care</h4>
                    <ul>
                        <li>Polyester</li>
                        <li>Machine wash</li>
                    </ul>
                    <h4>Size & Fit</h4>
                    <ul>
                        <li>Regular fit</li>
                        <li>The model (height 5'8" and chest 33") is wearing a size S</li>
                    </ul>
                    <hr>
                    <div class="social">
                        <h4>Show it to your friends</h4>
                        <p>
                            <a href="#" class="external facebook" data-animate-hover="pulse"><i
                                    class="fa fa-facebook"></i></a>
                            <a href="#" class="external gplus" data-animate-hover="pulse"><i
                                    class="fa fa-google-plus"></i></a>
                            <a href="#" class="external twitter" data-animate-hover="pulse"><i
                                    class="fa fa-twitter"></i></a>
                            <a href="#" class="email" data-animate-hover="pulse"><i
                                    class="fa fa-envelope"></i></a>
                        </p>
                    </div>
                </div>
            </div>
                <!-- /#content -->
            </#if>
        </div>
    </div>
</div>