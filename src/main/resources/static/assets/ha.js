jQuery(document).ready(function ($) {
    'use strict';
    $('.btn-cart').on('click', function (evt) {
        evt.preventDefault()
        addToCart(100)
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