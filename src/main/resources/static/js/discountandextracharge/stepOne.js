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
                $('#name').val(data.name);
                $('#checkName').val(data.checkName);
                $('#acceptManualDiscount').prop("checked", data.acceptManualDiscount);
                $('#minSum').val(data.minSum);
                $.each(data.actionTimeList, function (key, value) {
                    $(".tbody").append($([
                        '<tr>' +
                        '<td><input type="time" value="' + value.beginTime + '"></td>' +
                        '<td><input type="time" value="' + value.endTime + '"></td>' +
                        '<td><input type="checkbox" class="monday"></td>' +
                        '<td><input type="checkbox" class="tuesday"></td>' +
                        '<td><input type="checkbox" class="wednesday" ></td>' +
                        '<td><input type="checkbox" class="thursday" ></td>' +
                        '<td><input type="checkbox" class="friday"></td>' +
                        '<td><input type="checkbox" class="saturday"></td>' +
                        '<td><input type="checkbox" class="sunday"></td>' +
                        '</tr>'].join("\n")));
                    $('tr:eq('+(key+1)+') .monday').prop("checked", value.monday);
                    $('tr:eq('+(key+1)+') .tuesday').prop("checked", value.tuesday);
                    $('tr:eq('+(key+1)+') .wednesday').prop("checked", value.wednesday);
                    $('tr:eq('+(key+1)+') .thursday').prop("checked", value.thursday);
                    $('tr:eq('+(key+1)+') .friday').prop("checked", value.friday);
                    $('tr:eq('+(key+1)+') .saturday').prop("checked", value.saturday);
                    $('tr:eq('+(key+1)+') .sunday').prop("checked", value.sunday);
                    $('tr:eq('+(key+1)+')').find('.friday').prop("checked", value.thursday);
                });
            },
            error: function () {
                alert("error")
            }
        });
    }
});

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
    var id = localStorage.getItem("id");
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
    localStorage.setItem('discount', JSON.stringify(discount));
    window.location.replace("/discountandextracharge/step2/");
}

function cancel() {
    localStorage.removeItem("id");
    localStorage.removeItem('discount');
    window.location.replace("/discountandextracharge");
}