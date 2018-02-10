var host = window.location.origin;
var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

function makeRow(name, price) {
    var markup = "<tr><td>" + name + "</td><td>" + price + "</td></tr>";
    $("#result").append(markup)
}

function setCashierTable(name, price) {
    makeRow(name, price);
    sum(price)
}

function sum(data) {
    var price = parseInt($("#sum").text());
    var dPrice = parseInt(data);
    price += dPrice;
    $("#sum").html(price)
}

function getProduct(name) {
    $("#backward").removeClass("disable");
    $("#categories").css({"display": "none"});
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
                $("#dish").append("<a onclick='setCashierTable(\""+value.name+"\", " + value.price + ")' class=\"middle-panel-white\" href=\"#\"><p>" + value.name + "</p>" + value.price + "</a>")
            });
            $("#dish").css({"display": "block"});
        },
        error: function (e) {
        }
    });
}

function getCategories() {
    $("#backward").addClass("disable");
    $("#categories").css({"display": "block"});
    $("#dish").css({"display": "none"});
}

