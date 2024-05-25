function updateLineQuantity() {

    $.ajax({
        type    : "GET",
        url     : "/cart/temp_cart_lines_quantity",
        dataType: 'json',
        data    : {
            quantity: "quantity"
        },
        success:function(data) {
            $('#line_quantity').text(data.quantity);
        }
    });
}