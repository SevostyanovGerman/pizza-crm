var host = window.location.origin;
let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

function getProduct(name) {
    $("#backward").removeClass("disable");
    $("#category").css({"display": "none"});
    $.ajax({
        type: "POST",
        url: "/get/categoriesdish",
        data: "name=" + name,
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
}

function getCategories() {
    $("#backward").addClass("disable");
    $("#category").css({"display": "block"});
    $("#dish").css({"display": "none"});
}

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
    let total = 0;
    $('.order-table tr').each(function () {
        total += getRowTotal($(this));
    });
    $('#total').html(total)
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
