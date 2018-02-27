let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");
let colonToggler = false;

function displayDateTime() {
    let dt = new Date().toLocaleTimeString('ru-RU', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit'
    });
    console.log(dt);
    let dateTimeParts = dt.split(',');
    let dateParts = dateTimeParts[0].split('.');
    let timeParts = dateTimeParts[1].split(':');
    $('.clock-days').text(dateParts[0]);
    $('.clock-months').text(dateParts[1]);
    $('.clock-years').text(dateParts[2]);
    $('.clock-hours').text(timeParts[0]);
    $('.clock-minutes').text(timeParts[1]);
    $(".clock-colon").css({ visibility: colonToggler ? 'visible' : 'hidden'});
    colonToggler = !colonToggler;
}

$(document).ready(function () {
    displayDateTime();
    setInterval(displayDateTime, 1000);
});

$(document).ready(function () {
    let dt = new Date();
    let time = dt.getHours() + ":" + dt.getMinutes();
    $('#orderTime').html(time);
});

$(document).ready(function () {
    $(".category-item").click(function () {
        $("#backward").removeClass("disable");
        $("#category").css({"display": "none"});
        $.ajax({
            type: "POST",
            url: "/get/categoriesdish",
            data: "name=" + $(this).text(),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {
                $("#dish").empty().css({"display": "block"});
                $.each(data, function (key, value) {
                    $("#dish").append($([
                        "<a href='#' class='order-item middle-panel-white'",
                        "data-item-name=\"" + value.name + "\"",
                        "data-quantity='1'",
                        "data-price=" + value.price,
                        ">",
                        "<p>" + value.name + "</p>",
                        "<p>" + value.price + "</p>",
                        "</a>"
                    ].join("\n")));
                });
            },
            error: function (e) {
            }
        });
    });
});

$(document).ready(function () {
    $("#backward").click(function () {
        $(this).addClass("disable");
        $("#category").css({"display": "block"});
        $("#dish").css({"display": "none"});
    })
});

$(document).ready(function () {
    $('.discount-extraCharge-modal-show').click(function () {
        $("#discount-extraCharge-modal").modal('show');
        $("#discountForm").val($("#discount").text());
        $("#extraChargeForm").val($("#extraCharge").text());
    });
});

$(document).ready(function () {
    $('.discount-extraCharge-modal-save').click(function () {
        let discount = parseFloat($('input[name="discountForm"]').val());
        let extraCharge = parseFloat($('input[name="extraChargeForm"]').val());
        $("#discount").html(discount).val(discount);
        $("#extraCharge").html(extraCharge).val(extraCharge);
        $("#discount-extraCharge-modal").modal('hide');
        updateTotal();
    });
});

$(document).ready(function () {
    $('.order-table').on('click', 'tr', function () {
        $(this).addClass('highlight').siblings().removeClass('highlight');
    });
});

$(document).ready(function () {
    $('.add-quantity').click(function () {
        let tr = getSelectedRow();
        let quantity = parseFloat(tr.find('td:eq(0)').text());
        quantity++;
        tr.find('td:eq(0)').text(quantity);
        updateTotal();
    });
});

$(document).ready(function () {
    $('.subtract-quantity').click(function () {
        let tr = getSelectedRow();
        let quantity = parseFloat(tr.find('td:eq(0)').text());
        if (--quantity <= 0) {
            tr.remove();
            updateTotal();
            return;
        }
        tr.find('td:eq(0)').text(quantity);
        updateTotal();
    });
});

$(document).ready(function () {
    $('.remove-selected-dish').click(function () {
        let tr = getSelectedRow();
        tr.prev().addClass('highlight').siblings().removeClass('highlight');
        updateTotal();
        tr.remove();
    });
});

$(document).ready(function () {
    $('#dish').on('click', '.order-item', function (e) {
        e.preventDefault();
        $('.order-table').append($([
            "<tr>",
            "<td>" + $(this).data('quantity') + "</td>",
            "<td>" + $(this).data('itemName') + "</td>",
            "<td>" + $(this).data('price') + "</td>",
        ].join("/n")));
        updateTotal();
    });
});

function getSelectedRow() {
    let tr = $('.order-table tr.highlight');
    if (tr.length === 0) {
        tr = $('.order-table tr:last');
        tr.addClass('highlight').siblings().removeClass('highlight');
    }
    return tr;
}

function updateTotal() {
    let rawTotal = 0;
    $('.order-table tr').each(function () {
        rawTotal += getRowTotal($(this));
    });
    let discount = parseFloat($("#discount").val());
    let extraCharge = parseFloat($("#extraCharge").val());
    let total = rawTotal;
    if (!isNaN(discount) && discount > 0) {
        total = rawTotal - rawTotal * discount / 100;
    } else if (!isNaN(extraCharge) && extraCharge > 0) {
        total = rawTotal + rawTotal * extraCharge / 100;
    }
    $('#rawTotal').html(rawTotal);
    $('#total').html(total);
}

function getRowTotal(row) {
    let quantity = parseFloat(row.find('td:eq(0)').text());
    if (isNaN(quantity)) {
        quantity = 0;
    }
    let price = parseFloat(row.find('td:eq(2)').text());
    if (isNaN(price)) {
        price = 0;
    }
    return quantity * price;
}
