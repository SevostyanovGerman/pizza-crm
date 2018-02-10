var host = window.location.origin;
var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

function makeRow(data) {
    var markup = "<tr><td>" + data.name + "</td><td>" + data.price + "</td></tr>";
    $("#result").append(markup)
}

function getInfo(name) {
    $.ajax({
        method: "POST",
        url: "http://127.0.0.1:8080/admin/getinfo",
        data: ({name: name}),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function process(data) {
            makeRow(data);
            sum(data.price);
            return data;
        },
        error: function (data) {
            alert("Error" + data);
        }
    })
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
                var zzz = value.name;
                $("#dish").append("<a onclick='getInfo(\""+value.name+"\")' class=\"middle-panel-white\" href=\"#\">" + value.name + " " + value.price + "</a>")
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
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
