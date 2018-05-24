var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

var activeDecree = null;

getDecree();

function create() {
    location.href = "/newDecree"
}

function getDecree() {
    $.ajax({
        type: "POST",
        url: "/get/decree",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            $("#table-body").empty();
            data.forEach(function (value) {
                $("#table-body").append("<tr onclick=\"focusDecree(" + value.id + ")\" id=\"tr-" + value.id + "\"><td>" + value.startTime + "</td><td>" + value.numberDecree + "</td><td>" + value.endTime + "</td><td>Доп.информация</td><td>Торговые предприятия</td><td>" + value.enable + "</td><td>Концепция</td><td>Комментарий</td><td>Расписание</td></tr>");
            });
        },
        error: function (e) {

        }
    });
}

function focusDecree(newActiveDecree) {
    idold = "#tr-" + activeDecree;
    id = "#tr-" + newActiveDecree;

    $(id).css({"background-color": "#fff"});
    $(idold).css({"background-color": "#d5d0d6"});

    activeDecree = newActiveDecree;
}

function deleteAcveDecree() {
    id = "#tr-" + activeDecree;
    $(id).empty();

    $.ajax({
        type: "POST",
        url: "/delete/decree/" + activeDecree,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
        },
        error: function (e) {

        }
    });
}

function getOneDecree() {
    if(activeDecree != null) {
        location.href = "/newDecree?id=" + activeDecree;
    }
}