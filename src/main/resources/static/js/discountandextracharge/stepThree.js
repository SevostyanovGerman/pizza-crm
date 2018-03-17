var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

function next() {
    var id = $('#id').val();
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
    var applyForAllType = $('#applyForAllType').prop('checked');
    var discountType = $("input[name='discountType']:checked").val();
    var discountValueType = $("input[name='discountValueType']:checked").val();
    var value = $('#value').val();
    if (applyForAllType === true) {
        var discountProperties = {
            id: id,
            applyForAllType: applyForAllType,
            discountType: discountType,
            discountValueType: discountValueType,
            value: value
        };
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/saveStepThree",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(discountProperties),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (id) {
                window.location.replace("/discountandextracharge/step4/" + id);
            },
            error: function (e) {
                alert("error")
            }
        });
    } else if (applyForAllType === false) {
        var discountProperties = {
            id: id,
            applyForAllType: applyForAllType,
            categoryDiscounts: categoryDiscounts
        };
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/saveStepThree",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(discountProperties),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (id) {
                window.location.replace("/discountandextracharge/step4/" + id);
            },
            error: function (e) {
                alert("error")
            }
        });
    }
}

function back() {
    var id = $('#id').val();
    window.location.replace("/discountandextracharge/step2/" + id);
}

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

$(document).ready(function () {
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
