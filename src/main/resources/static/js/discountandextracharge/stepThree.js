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
                var applicationMethod = data.applicationMethod;
                $("input[name=applicationMethod][value=" + applicationMethod + "]").prop('checked', true);
                if (applicationMethod === "WITHOTHER") {
                    $("#priority").removeAttr("disabled");
                    var priority = data.priority;
                    $("#priority").val(priority);
                }
                var applyForAllType = data.applyForAllType;
                $('#applyForAllType').prop('checked', applyForAllType);
                if (applyForAllType) {
                    $(".table").css({"display": "none"});
                    $('#discount').removeAttr("disabled");
                    $('#extracharge').removeAttr("disabled");
                    $('#percent').removeAttr("disabled");
                    $('#fixeddiscount').removeAttr("disabled");
                    $('#value').removeAttr("disabled");
                    $('#printDetails').attr('disabled', 'disabled')
                    var discountType = data.discountType;
                    $("input[name=discountType][value=" + discountType + "]").prop('checked', true);
                    var discountValueType = data.discountValueType;
                    $("input[name=discountValueType][value=" + discountValueType + "]").prop('checked', true);
                    if (discountValueType === 'FIXEDDISCOUNT') {
                        $("#labelForValue").html("Фиксированная сумма:");
                    } else if (discountValueType === 'PERCENT') {
                        $("#labelForValue").html("Процент:");
                    }
                    var value = data.value;
                    $('#value').val(value);
                } else {
                    $(".table").css({"display": "table"});
                    $('#discount').attr('disabled', 'disabled');
                    $('#extracharge').attr('disabled', 'disabled');
                    $('#percent').attr('disabled', 'disabled');
                    $('#fixeddiscount').attr('disabled', 'disabled');
                    $('#value').attr('disabled', 'disabled');
                    $('#printDetails').removeAttr('disabled');
                }
            },
            error: function () {
                alert("error")
            }
        });
    }
});

$(document).ready(function () {
    $('#percent').click(function () {
        $("#labelForValue").html("Процент:");
    });
});

$(document).ready(function () {
    $('#fixeddiscount').click(function () {
        $("#labelForValue").html("Фиксированная сумма:");
    });
});

$(document).ready(function () {
    $('#WITHOTHER').click(function () {
        $("#priority").removeAttr("disabled");
    });
});

$(document).ready(function () {
    $('#FULLPRICE').click(function () {
        $("#priority").attr('disabled', 'disabled');
    });
});

$(document).ready(function () {
    $('#applyForAllType').click(function () {
        var applyForAllType = $('#applyForAllType').prop('checked');
        if (applyForAllType === true) {
            $(".table").css({"display": "none"});
            $('#discount').removeAttr("disabled");
            $('#extracharge').removeAttr("disabled");
            $('#percent').removeAttr("disabled");
            $('#fixeddiscount').removeAttr("disabled");
            $('#value').removeAttr("disabled");
            $('#printDetails').attr('disabled', 'disabled')
        } else {
            $(".table").css({"display": "table"});
            $('#discount').attr('disabled', 'disabled');
            $('#extracharge').attr('disabled', 'disabled');
            $('#percent').attr('disabled', 'disabled');
            $('#fixeddiscount').attr('disabled', 'disabled');
            $('#value').attr('disabled', 'disabled');
            $('#printDetails').removeAttr('disabled');
        }
    });
});

function next() {
    var categoryDiscounts = [];
    $('#categoryDiscounts tr').each(function () {
        var name = $(this).find('.name').text();
        var type = $(this).find('.type').val();
        var value = $(this).find(".value").val();
        var discount = {
            categoryName: name,
            discountType: type,
            value: value
        };
        categoryDiscounts.push(discount);
    });
    var applicationMethod = $("input[name='applicationMethod']:checked").val();
    var priority = $('#priority').val();
    var applyForAllType = $('#applyForAllType').prop('checked');
    var discountType = $("input[name='discountType']:checked").val();
    var discountValueType = $("input[name='discountValueType']:checked").val();
    var value = $('#value').val();
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
    var discountWithOther = discountPrevPage.discountWithOther;
    if (applyForAllType === true) {
        if (applicationMethod === 'FULLPRICE') {
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
                discountWithOther: discountWithOther,
                applyForAllType: applyForAllType,
                discountType: discountType,
                discountValueType: discountValueType,
                value: value
            };
            localStorage.setItem("discount", JSON.stringify(discount));
            window.location.replace("/discountandextracharge/step4/");
        } else if (applicationMethod === 'WITHOTHER') {
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
                discountType: discountType,
                discountValueType: discountValueType,
                value: value
            };
            localStorage.setItem("discount", JSON.stringify(discount));
            window.location.replace("/discountandextracharge/step4/");
        }
    } else if (applyForAllType === false) {
        if (applicationMethod === 'FULLPRICE') {
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
                discountWithOther: discountWithOther,
                applyForAllType: applyForAllType,
                categoryDiscounts: categoryDiscounts
            };
        } else if (applicationMethod === 'WITHOTHER') {
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
                categoryDiscounts: categoryDiscounts
            };
            localStorage.setItem("discount", JSON.stringify(discount));
            window.location.replace("/discountandextracharge/step4/");
        }
    }
}

function back() {
    window.location.replace("/discountandextracharge/step2/");
}

function cancel() {
    localStorage.removeItem("id");
    localStorage.removeItem('discount');
    window.location.replace("/discountandextracharge");
}