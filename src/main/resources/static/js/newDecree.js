var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

getDish();

var dish = [];
var dishdb;
var dishSend = [];


function SaveDecree() {
    var numberDecree = $("#id").val();
    var startTime = $("#beginDate").val();
    var endTime = $("#endDate").val();
    var comment = $("#comment").val();
    var nameForIikoFront = $("#nameForIikoFront").val();
    var enable = $("#decreeTrue").prop('checked');

    dishSend = [];

    dishdb.forEach(function (value) {
        if (dish.indexOf(value.id) != -1) {
            dishSend.push(value);
        }
    });

    var validities = [];
    var nameValidity = $("#activePeriod").find('option:selected').text();
    var validity = {nameValidity: nameValidity};
    validities.push(validity);

    var decree = {
        id: id,
        comment: comment,
        dishes: dishSend,
        startTime: startTime + "T00:00:00",
        endTime: endTime + "T23:59:59",
        nameForIikoFront: nameForIikoFront,
        numberDecree: numberDecree,
        enable: enable,
        validities: validities
    };

    console.log(decree);

    $.ajax({
        type: "POST",
        url: "/save/decree",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(decree),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
        },
        error: function (e) {
        }
    });

    location.href = "/getDecree";

}

function Cancel() {
    location.href = "/getDecree";
}

function getDish() {
    $.ajax({
        type: "POST",
        url: "/get/dish",
        contentType: "application/json; charset=utf-8",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            dishdb = data;
            $("#table-body").empty();
            data.forEach(function (value) {
                $("#table-body").append("<tr><td>" + value.id + "</td><td><input type=\"checkbox\" onclick=\"addRemuveDish(" + value.id + ")\" class=\"trr-" + value.id + "\"></td><td>" + value.code + "</td><td>" + value.name + "</td><td>290,00</td><td>" + value.price + "</td><td>-40,00</td><td>0</td>\<td>807,44</td><td>27,55</td><td>11,02</td><td> <input type=\"checkbox\"></td><td><input type=\"checkbox\"></td></tr>");
            });
            getDecree();
        },
        error: function (e) {
        }
    });
}

function addRemuveDish(idDish) {
    position = dish.indexOf(idDish);
    if (position == -1) {
        dish.push(idDish);
    } else {
        dish.splice(position, 1)
    }
    console.log(dish);
}

function getDecree() {
    id = getUrlVars()["id"];
    if (id != null) {
        $.ajax({
            type: "POST",
            url: "/get/decree/" + id,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {
                console.log(data)
                $("#id").val(data.numberDecree);
                $("#beginDate").val(data.startTime.substr(0, 10));
                $("#endDate").val(data.startTime.substr(0, 10));
                $("#comment").val(data.comment);
                $("#nameForIikoFront").val(data.nameForIikoFront);
                $("#decreeTrue").prop('checked', data.enable);
                dishData = data.dishes;
                dishData.forEach(function (value) {
                    dish.push(value.id);
                    $(".trr-" + value.id).prop('checked', true);
                });
            },
            error: function (e) {

            }
        });
    }
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}
