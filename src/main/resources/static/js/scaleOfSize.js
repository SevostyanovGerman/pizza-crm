var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {


    $('.plus').click(function () {
        $(this).parents('.order').nextUntil(".order", '.order_item').toggle();
    });

});