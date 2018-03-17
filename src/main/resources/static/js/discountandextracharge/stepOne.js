var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

function addNewRow() {
    $('.tbody').append($([
        "<tr>" +
        "<td><input class='beginTime' type='time'></td>" +
        "<td><input class='endTime' type='time'></td>" +
        "<td><input class='monday' type='checkbox'></td>" +
        "<td><input class='tuesday' type='checkbox'></td>" +
        "<td><input class='wednesday' type='checkbox'></td>" +
        "<td><input class='thursday' type='checkbox'></td>" +
        "<td><input class='friday' type='checkbox'></td>" +
        "<td><input class='saturday' type='checkbox'></td>" +
        "<td><input class='sunday' type='checkbox'></td>" +
        "</tr>"
    ].join('/n')))
}

$(document).ready(function () {
    $('.tbody').on('click', 'tr', function () {
        $(this).addClass('item-active').siblings().removeClass('item-active');
    });
});

function deleteRow() {
    var tr = $('.tbody tr.item-active');
    tr.remove();
}

function next() {
    var timeArray = [];
    $('.tbody tr').each(function () {
        var beginTime = $(this).find('.beginTime').val();
        var endTime = $(this).find('.endTime').val();
        var monday = $(this).find(".monday").prop('checked');
        var tuesday = $(this).find(".tuesday").prop('checked');
        var thursday = $(this).find(".thursday").prop('checked');
        var wednesday = $(this).find(".wednesday").prop('checked');
        var friday = $(this).find(".friday").prop('checked');
        var saturday = $(this).find(".saturday").prop('checked');
        var sunday = $(this).find('.sunday').prop('checked');
        var time = {
            beginTime: beginTime,
            endTime: endTime,
            monday: monday,
            tuesday: tuesday,
            thursday: thursday,
            wednesday: wednesday,
            friday: friday,
            saturday: saturday,
            sunday: sunday
        };
        timeArray.push(time);
    });
    var id = $('#id').val();
    var name = $('#name').val();
    var checkName = $('#checkName').val();
    var type = $('#type').val();
    var acceptManualDiscount = $('#acceptManualDiscount').prop('checked');
    var minSum = $('#minSum').val();
    var discount = {
        id: id,
        name: name,
        checkName: checkName,
        type: type,
        acceptManualDiscount: acceptManualDiscount,
        minSum: minSum,
        actionTimeList: timeArray
    };
    $.ajax({
        type: "POST",
        url: "/discountandextracharge/saveStepOne",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(discount),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (id) {
            window.location.replace("/discountandextracharge/step2/" + id);
        },
        error: function (e) {
            alert("error")
        }
    });
}