let paymentMethods = [];
let totalCash = 0;
let paid = false;
let orderJson;
let order;
let currentPaymentMethod
let paymentMethodCash;

let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

// getAllPaymentTypesForCashbox
/*$(document).ready(function () {

});*/

$(document).ready(function () {
    $('.btn-dialer').click(function () {
        let cash = $('.input-cash');
		if (isFloat(Number(cash.val()))){
            cash.val().toFixed(2);
        }
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
    $('.input-cash').on('input', function ()  {
        var cash = Number($(this).val());
        if (isFloat(cash)){
            return cash.toFixed(2);
        }
        return cash;
    });
});

function isFloat(n){
    let value = 10*n
    return Number(value) === value && value % 1 !== 0;
}

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
            orderJson = $('.order').attr('value');
            order = JSON.parse(orderJson);
            order.dishes.forEach(function (elem) {
                $('.order-table').append($([
                    "<tr>",
                    "<td>" + elem.amount + "</td>",
                    "<td class='text-center'>" + elem.name + "</td>",
                    "<td>" + elem.price + "</td>",
                    "</td>"
                ].join("/n")));
            });
            $('#discount').html(order.discount);
            $('#extraCharge').html(order.id);
            $('#rawTotal').html(order.costNotDiscount);
            $('#total').html(order.costDiscount);
});

$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "/getAllPaymentTypesForCashbox",
        contentType: "application/json; charset=utf-8",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $('#payment-types').append(
                    '<div class="col-3 payment-method-item">' +
                    '<p class="font-weight-bold text-uppercase pt-3">' + data[i].name + '</p></div>');
            }
            $('#payment-types').append(
                '<div class="col text-right"> ' +
                '<a href="#" class="mr-4"><i class="fa fa-bars" aria-hidden="true"></i></a> ' +
                '<a href="/logout" th:href="@{/logout}"><i class="fa fa-lock" aria-hidden="true"></i></a> ' +
                '</div>');

            $('.payment-method-item').click(function () {
                $('.payment-method-item').each(function () {
                    $(this).removeClass('highlight');
                });
                $(this).addClass('highlight');
                currentPaymentMethod = $(this).find('p').text();
                selectedPaymentMethod = currentPaymentMethod;
                paymentMethodCash = 0;
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

        },
        error: function () {}
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
        if (paymentMethods.length === 0 || paid === true) {
            return;
        }
        let cash = parseFloat($('.input-cash').val());
        let total = parseFloat($('#total').text());
        paymentMethodCash += cash;
        if (cash === 0) {
            return;
        }
            $('.payment-method-table tr:last').find('td:last').text(paymentMethodCash);
            $('.deposit').text(cash);
            totalCash += cash;
            if (totalCash > total) {
				let change = totalCash - total;
				if(isFloat(change)) {
					$('#change').text(change.toFixed(2));
				} else {
					$('#change').text(change);
				}
            } else {
                $('#change').text('0.00');
            }
                /*Отправка пост запроса с параметрами оплаты
                 Передаются параметры: id заказа, totalCash, total
                */
                let id = order.id;
                var xhr = new XMLHttpRequest();
                var body = 'id=' + encodeURIComponent(id) +
                              '&total=' + encodeURIComponent(total) +
                              '&cash=' + encodeURIComponent(cash) +
                              '&totalCash=' + encodeURIComponent(totalCash) +
                              '&change=' + encodeURIComponent(parseFloat($('#change').text())) +
                              '&currentPaymentMethod=' + encodeURIComponent(currentPaymentMethod);
                xhr.open("POST", '/cashbox', true);
                xhr.setRequestHeader(csrfHeader, csrfToken);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.send(body);

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
            totalCash = 0;
            paid = false;
            $('.deposit').text(totalCash);
            $(this).closest('tr').remove();
            let currentChange = parseFloat($('#change').text());
            $('#change').text('0.00');
        }
    });
});

