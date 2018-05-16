var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('td.viewName').click(function () {
        $('td').removeClass();
        $(this).addClass('item-active');
        getSelectedSchedule();
    });
});

//выделение строк в таблице
function getSelectedSchedule() {
    var td = $('#Schedule-list td.item-active');
    var name = td.text();
    $('#scheduleListName').val(name);
    showSchedule(name);
    $('#showSchedule').empty(); // добавил эту строчку здесь!!! Работает!

}

function showSchedule(nameValidity) {
    $.ajax({
        type: "POST",
        url: "/validity/get",
        data: {nameValidity: nameValidity},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            console.log(data);
            for (var i = 0; i < data.length; i++) {

                $('#showSchedule').append('<tr>' +
                    '<td><input type="time" value="' + data[i].beginTime + '"></td>' +
                    '<td><input type="time" value="' + data[i].endTime + '"></td>' +
                    '<td><input type="checkbox" class="monday"'    +(data[i].monday ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="tuesday"'   +(data[i].tuesday ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="wednesday"' +(data[i].wednesday ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="thursday"'  +(data[i].thursday ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="friday"'    +(data[i].friday ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="saturday"'  +(data[i].saturday ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="sunday"'    +(data[i].sunday ? 'checked' : '') + '></td>' +
                    '</tr>');
            }

        },
        error: function (e) {
            alert("error")
        }
    });
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


function addValidity() {
    var nameValidity = $("#validityName").val();
    $.ajax({
        type: "POST",
        url: "/validity/addValidity",
        data: {nameValidity:nameValidity},
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



$(document).ready(function () {
    $('button.addSchedule').on('click', function() {
        tdName = $(this).closest('tr').find('td:eq(0)').text();
    });
});

function addSchedule() {
    var validityName = tdName;
    var beginTime = $('#beginTime').val();
    var endTime = $('#endTime').val();
    var monday = $("#monday").prop('checked');
    var tuesday = $("#tuesday").prop('checked');
    var thursday = $("#thursday").prop('checked');
    var wednesday = $("#wednesday").prop('checked');
    var friday = $("#friday").prop('checked');
    var saturday = $("#saturday").prop('checked');
    var sunday = $('#sunday').prop('checked');

    $.ajax({
        type: "POST",
        url: "/schedule/addSchedule",
        data: {
            validityName:validityName,
            beginTime:beginTime,
            endTime:endTime,
            monday:monday,
            tuesday:tuesday,
            thursday:thursday,
            wednesday:wednesday,
            friday:friday,
            saturday:saturday,
            sunday:sunday
        },
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