var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('#schedule-list td').click(function () {
        $('td').removeClass();
        $(this).addClass('item-active');
        getSelectedSchedule();
    });
});

//выделение строк в таблице
function getSelectedSchedule() {
    var td = $('#schedule-list td.item-active');
    var name = td.text();
    $('#scheduleListName').val(name);
    showSchedule(name);
}

function showSchedule(name) {
    $.ajax({
        type: "POST",
        url: "/validity/get",
        data: "name=" + name,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            $('#id').val(data.id);
            $('#showSchedule').empty();
            $('#showSchedule').append('<tr>' +
                '<td><input type="time" value="' + data.beginTime + '"></td>' +
                '<td><input type="time" value="' + data.endTime + '"></td>' +
                '<td><input type="checkbox" class="monday"></td>' +
                '<td><input type="checkbox" class="tuesday"></td>' +
                '<td><input type="checkbox" class="wednesday"></td>' +
                '<td><input type="checkbox" class="thursday"></td>' +
                '<td><input type="checkbox" class="friday"></td>' +
                '<td><input type="checkbox" class="saturday"></td>' +
                '<td><input type="checkbox" class="sunday"></td>' +
                '</tr>');
            checkCheckbox(data);
        },
        error: function (e) {
            alert("error")
        }
    });
}

function checkCheckbox(data) {
    $(".monday").prop("checked", data.monday);
    $(".tuesday").prop("checked", data.tuesday);
    $(".wednesday").prop("checked", data.wednesday);
    $(".thursday").prop("checked", data.thursday);
    $(".friday").prop("checked", data.friday);
    $(".saturday").prop("checked", data.saturday);
    $(".sunday").prop("checked", data.sunday);
}

function save() {
    var name = $("#scheduleListName").val();
    var id = $('#id').val();
    var beginTime = $('#showSchedule tr').find("input[type=time]").eq(0).val();
    var endTime = $('#showSchedule tr').find("input[type=time]").eq(1).val();
    var monday = $(".monday").prop('checked');
    var tuesday = $(".tuesday").prop('checked');
    var thursday = $(".thursday").prop('checked');
    var wednesday = $(".wednesday").prop('checked');
    var friday = $(".friday").prop('checked');
    var saturday = $(".saturday").prop('checked');
    var sunday = $('.sunday').prop('checked');
    var schedule = {
        id: id,
        name: name,
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
    $.ajax({
        type: "POST",
        url: "/validity/save",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(schedule),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            window.location.replace("/validity");
        },
        error: function (e) {
            alert("error")
        }
    });
}

function exit() {
    window.location.replace("/newDecree");
}

function saveAndExit() {

}

function addSchedule() {
    var name = $("#scheduleName").val();
    var beginTime = $('#beginTime').val();
    var endTime = $('#endTime').val();
    var monday = $("#monday").prop('checked');
    var tuesday = $("#tuesday").prop('checked');
    var thursday = $("#thursday").prop('checked');
    var wednesday = $("#wednesday").prop('checked');
    var friday = $("#friday").prop('checked');
    var saturday = $("#saturday").prop('checked');
    var sunday = $('#sunday').prop('checked');
    var schedule = {
        name: name,
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
    $.ajax({
        type: "POST",
        url: "/validity/save",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(schedule),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            window.location.replace("/validity");
        },
        error: function (e) {
            alert("error")
        }
    });
}

function deleteSchedule() {
    var selected = $('.item-active').html();
    if (selected !== undefined) {
        $.ajax({
            type: "POST",
            url: "/validity/delete",
            data: {name: selected},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/validity");
            },
            error: function (e) {
                // alert("error")
            }
        });
    } else {
        alert("Выберите наименование")
    }

}

function refresh() {
    window.location.replace("/validity");
}