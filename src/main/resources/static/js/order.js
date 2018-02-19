var host = window.location.origin;
var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");
var discount = 0.00;
var extraCharge = 0.00;
var cost = 0;
var price;

function makeRow(name, price) {
    var markup = "<tr><td>" + name + "</td><td>" + price + "</td></tr>";
    $("#result").append(markup)
}

function orderTime() {
    var dt = new Date();
    var time = dt.getHours() + ":" + dt.getMinutes();
    if ($('#orderTime').is(':empty')){
        $('#orderTime').html(time)
    }
}

function setCashierTable(name, price) {
    orderTime();
    makeRow(name, price);
    sum(price)
}

function sum(data) {
    var dPrice = parseInt(data);
    cost += dPrice;
    if (discount > 0) {
        price = cost - (cost * discount / 100);
    } else {
        price = cost + (cost * extraCharge / 100);
    }
    $("#sum").html(cost);
    $("#lastPrice").html(price);
}

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
            $("#dish").empty();
            $.each(data, function (key, value) {
                $("#dish").append("<a onclick='setCashierTable(\"" + value.name + "\", " + value.price + ")' class=\"middle-panel-white\" href=\"#\"><p>" + value.name + "</p>" + value.price + "</a>")
            });
            $("#dish").css({"display": "block"});
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

function ModalShow() {
    $("#exampleModal").modal('show');
    $("#discountForm").val($("#discount").text());
    $("#extraChargeForm").val($("#extraCharge").text());
}

function ChangeDiscount() {
    $("#extraChargeForm").val("0.00");
}

function ChangeExtraCharge() {
    $("#discountForm").val("0.00");
}

function SaveChange() {
    discount = parseFloat($('input[name="discountForm"]').val());
    extraCharge = parseFloat($('input[name="extraChargeForm"]').val());
    $("#discount").html(discount);
    $("#extraCharge").html(extraCharge);
    $("#exampleModal").modal('hide');
    if (discount > 0) {
        price = cost - (cost * discount / 100);

    } else {
        price = cost + (cost * extraCharge / 100);
    }
    $("#lastPrice").html(price);
}
