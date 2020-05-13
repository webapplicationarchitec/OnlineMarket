jQuery(document).ready(function ($) {
    'use strict';
    $('.btn-cart').on('click', function (evt) {
        evt.preventDefault()
        let jLink = $(evt.currentTarget);
        let productId = parseInt(jLink.attr('data-product-id'));
        addToCart(productId)
    })
})

addToCart = function (productId) {
    $.ajax({
        url: '/add-to-cart/' + productId.toString(),
        type: 'POST',
        contentType: 'application/json'
    }).done(function (result) {
        console.log(result)
    })
        .fail(function (error) {
            console.log(error)
        })
}