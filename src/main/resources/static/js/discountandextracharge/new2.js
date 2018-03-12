var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function() {
    var id = $('#id').val();
    $.ajax({
        type: "POST",
        url: "/discountandextracharge/get",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(id),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            $("#manualInput").prop("checked", data.manualInput);
            $("#setAuto").prop("checked", data.setAuto);
            $("#stewardChoice").prop("checked", data.stewardChoice);
            $("#discountWithOther").prop("checked", data.discountWithOther);
        },
        error: function (e) {
            alert("error")
        }
    });
});

function next() {
    var id = $('#id').val();
    var manualInput = $('#manualInput').prop('checked');
    var setAuto = $("#setAuto").prop('checked');
    var stewardChoice = $("#stewardChoice").prop('checked');
    var discountWithOther = $("#discountWithOther").prop('checked');
    var advancedOptions = {
        id : id,
        manualInput : manualInput,
        setAuto : setAuto,
        stewardChoice : stewardChoice,
        discountWithOther : discountWithOther
    };
    $.ajax({
        type: "POST",
        url: "/discountandextracharge/saveAdvancedOptions",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(advancedOptions),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            window.location.replace("/discountandextracharge/new3/"+data);
        },
        error: function (e) {
            alert("error")
        }
    });
}