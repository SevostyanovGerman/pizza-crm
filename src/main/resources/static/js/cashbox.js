let paymentMethods = [];
let totalCash = 0;

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
        return;
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

$(document).ready(function () {
    $('.payment-method-item').click(function () {
        $('.payment-method-item').each(function () {
            $(this).removeClass('highlight');
        });
        $(this).addClass('highlight');
        let currentPaymentMethod = $(this).find('p').text();
        let index = paymentMethods.indexOf(currentPaymentMethod);
        if (index === -1) {
            paymentMethods.push(currentPaymentMethod);
            console.log(index);
            $('.payment-method-table').append($([
                "<tr>",
                "<td id='remove-payment-method'>" +
                "<span class='fa-stack fa-lg' style='cursor: pointer'>" +
                "<i class='fa fa-circle-o fa-stack-2x'></i>" +
                "<i class='fa fa-times fa-stack-1x'></i></span>" +
                "</td>",
                "<td class='text-left'>" + currentPaymentMethod + "</td>",
                "<td class='text-right'>0</td>",
                "</td>"
            ].join("/n")));
        }
    });
});

$(document).ready(function () {
    $('.exact-sum').click(function () {
        $('.input-cash').val($('#total').text());
        $('.input-cash').text($('#total').text());
    });
});

$(document).ready(function () {
    $('.do-pay').click(function () {
        if (paymentMethods.length === 0) {
            return;
        }
        let cash = parseFloat($('.input-cash').val());
        $('.payment-method-table tr:last').find('td:last').text(cash);
        $('.deposit').text(cash);
        totalCash += cash;
        let total = parseFloat($('#total').text());
        if (totalCash > total) {
            $('#change').text(totalCash - total);
        } else {
            $('#change').text('0,00');
        }

    });
});

$(document).ready(function () {
    $('.btn-back').click(function () {
        window.history.back();
    });
});

$(document).ready(function () {
    $('.payment-method-table').on('click', 'td', function () {
        if ($(this).attr('id') === 'remove-payment-method') {
            let currentTr = $(this).closest('tr');
            let currentPayment = parseFloat(currentTr.find('td:last').text());
            paymentMethods.splice(currentTr.find('td').eq(1).text(), 1);
            totalCash -= currentPayment;
            $('.deposit').text(totalCash);
            $(this).closest('tr').remove();
            let currentChange = parseFloat($('#change').text());
            if (currentChange > currentPayment) {
                $('#change').text(currentChange - currentPayment);
            } else {
                $('#change').text('0,00');
            }
        }
    });
});