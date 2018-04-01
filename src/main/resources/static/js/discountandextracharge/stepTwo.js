var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    var id = localStorage.getItem("id");
    if (id != null){
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
                $("#discountWithOther").prop("checked", data.discountWithOther);
            },
            error: function () {
                alert("error")
            }
        });
    }
});

function next() {
    var manualInput = $('#manualInput').prop('checked');
    var setAuto = $("#setAuto").prop('checked');
    var discountWithOther = $("#discountWithOther").prop('checked');

    var discountPrevPage = JSON.parse(localStorage.getItem("discount"));
    var id = discountPrevPage.id;
    var name = discountPrevPage.name;
    var checkName = discountPrevPage.checkName;
    var type = discountPrevPage.type;
    var acceptManualDiscount = discountPrevPage.acceptManualDiscount;
    var minSum = discountPrevPage.minSum;
    var actionTimeList = discountPrevPage.actionTimeList;
    var discount = {
        id: id,
        name: name,
        checkName: checkName,
        type: type,
        acceptManualDiscount: acceptManualDiscount,
        minSum: minSum,
        actionTimeList: actionTimeList,
        manualInput: manualInput,
        setAuto: setAuto,
        discountWithOther: discountWithOther
    };
    localStorage.setItem("discount", JSON.stringify(discount));
    window.location.replace("/discountandextracharge/step3/");
}

function back() {
    window.location.replace("/discountandextracharge/update/step1/");
}

function cancel() {
    localStorage.removeItem("id");
    localStorage.removeItem('discount');
    window.location.replace("/discountandextracharge");
}