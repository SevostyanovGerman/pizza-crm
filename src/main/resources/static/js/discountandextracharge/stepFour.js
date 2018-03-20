var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    var id = localStorage.getItem("id");
    if (id != null) {
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/get",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(id),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {
                var active = data.active;
                $('#active').prop('checked', active);
            },
            error: function () {
                alert("error")
            }
        });
    }
});

function next() {
    var active = $('#active').prop('checked');

    var discountPrevPage = JSON.parse(localStorage.getItem("discount"));
    var id = discountPrevPage.id;
    var name = discountPrevPage.name;
    var checkName = discountPrevPage.checkName;
    var type = discountPrevPage.type;
    var acceptManualDiscount = discountPrevPage.acceptManualDiscount;
    var minSum = discountPrevPage.minSum;
    var actionTimeList = discountPrevPage.actionTimeList;
    var manualInput = discountPrevPage.manualInput;
    var setAuto = discountPrevPage.setAuto;
    var applicationMethod = discountPrevPage.applicationMethod;
    var priority = discountPrevPage.priority;
    var discountWithOther = discountPrevPage.discountWithOther;
    var applyForAllType = discountPrevPage.applyForAllType;
    var discountType = discountPrevPage.discountType;
    var discountValueType = discountPrevPage.discountValueType;
    var value = discountPrevPage.value;
    var categoryDiscounts = discountPrevPage.categoryDiscounts;

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
        applicationMethod: applicationMethod,
        priority: priority,
        discountWithOther: discountWithOther,
        applyForAllType: applyForAllType,
        categoryDiscounts: categoryDiscounts,
        discountType: discountType,
        discountValueType: discountValueType,
        value: value,
        active: active
    };

    $.ajax({
        type: "POST",
        url: "/discountandextracharge/save",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(discount),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            localStorage.removeItem('discount');
            localStorage.removeItem("id");
            window.location.replace("/discountandextracharge");
        },
        error: function () {
            alert("error")
        }
    });

}

function back() {
    window.location.replace("/discountandextracharge/step3/");
}

function cancel() {
    localStorage.removeItem("id");
    localStorage.removeItem('discount');
    window.location.replace("/discountandextracharge");
}