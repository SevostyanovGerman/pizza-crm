var host = window.location.origin;
var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

function dinar(data) {
    var markup = "<tr><td>" + data.name + "</td><td>" + data.color + "</td></tr>";
    $("#result").append(markup)
}

function getInfo(name) {
    $.ajax({
        url: "http://127.0.0.1:8080/admin/getinfo",
        data: ({name: name}),
        success: function asd(data) {
            dinar(data)

            return data;
        },
        error: function (data) {
            alert("Error" + data);
        }
    })
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
                $("#dish").append("<a class=\"middle-panel-white\" href=\"#\">" + value.name + "</a>")
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
