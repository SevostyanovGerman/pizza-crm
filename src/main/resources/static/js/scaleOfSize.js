$('.plus').click(function() {
    $(this).parents('.order').nextUntil(".order",'.order_item').toggle();
});