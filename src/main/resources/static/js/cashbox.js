$(document).ready(function () {
    $('.btn-dialer').click(function () {
        let cash = $('.input-cash');
        if (cash.val() === '0.00') {
            if ($(this).val() === '0') {
                return;
            }
            cash.val($(this).val());
        } else {
            cash.val(cash.val() + $(this).val());
        }
    });
});

$(document).ready(function () {
    $('.btn-dialer-plus').click(function () {
        let cash = $('.input-cash');
        cash.val(parseFloat(cash.val()) + parseFloat($(this).val()));
    });
});

$(document).ready(function () {
    $('.btn-dialer-delimiter').click(function () {
        let cash = $('.input-cash');
        if (cash.val().includes('.')) {
            return;
        }
        cash.val(cash.val() + '.');
    });
});

$(document).ready(function () {
    $('.btn-dialer-clear').click(function () {
        $('.input-cash').val('0.00');
    });
});

$(document).ready(function () {
    let orderList = JSON.parse(sessionStorage.getItem('order-list'));
    if (orderList === undefined) {
        return
    }
    orderList.orderItems.forEach(function (elem) {
        $('.order-table').append($([
            "<tr>",
            "<td>" + elem.quantity + "</td>",
            "<td class='text-center'>" + elem.dishName + "</td>",
            "<td>" + elem.price + "</td>",
            "</td>"
        ].join("/n")));
    });
    $('#discount').html(orderList.discount);
    $('#extraCharge').html(orderList.extraCharge);
    $('#rawTotal').html(orderList.rawTotal);
    $('#total').html(orderList.total);
});