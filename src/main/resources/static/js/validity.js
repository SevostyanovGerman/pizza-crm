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
            for (var i = 0; i < data.length; i++) {
                $('#showSchedule').append('<tr>' +
                    '<input class="ids" type="hidden" value="' + data[i].id + '">' +
                    '<td><input type="time" value="' + data[i].beginTime + '"></td>' +
                    '<td><input type="time" value="' + data[i].endTime + '"></td>' +
                    '<td><input type="checkbox" class="monday activated"' + (data[i].monday ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="tuesday activated"' + (data[i].tuesday ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="wednesday activated"' + (data[i].wednesday ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="thursday activated"' + (data[i].thursday ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="friday activated"' + (data[i].friday ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="saturday activated"' + (data[i].saturday ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="sunday activated"' + (data[i].sunday ? 'checked' : '') + '></td>' +
                    '<td><a class="btn btn-danger btn-sm deleteSchedule" href="#"><i class="fa fa-trash deleteSchedule" aria-hidden="true" ></i></a></td>' +
                    '</tr>');
            }

        },
        error: function (e) {
            alert("error")
        }
    });
}

//send checkbox in Server
$(document).ready(function () {
    $("body").on("click", ".activated", function () {
        var id = $(this).closest('tr').find('input[type=hidden]').val();
        var activatedChecked = $(this).prop('checked').find('td:eq(2)').text();

        alert(activatedChecked);

        $.ajax({
            type: "POST",
            url: "/schedule/changeCheckbox",
            data: {id: id, activatedChecked: activatedChecked},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {

            },
            error: function () {
                alert("no checked")
            }
        });
    });
});

/*
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
}*/

function exit() {
    window.location.replace("/newDecree");
}

function saveAndExit() {

}


$(document).ready(function () {
    $('button.addSchedule').on('click', function () {
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

            validityName: validityName,
            beginTime: beginTime,
            endTime: endTime,
            monday: monday,
            tuesday: tuesday,
            thursday: thursday,
            wednesday: wednesday,
            friday: friday,
            saturday: saturday,
            sunday: sunday
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


//отправка значений на сервер в БД
function addAll() {
    var nameValidity = $('#validityNameAll').val();
    var beginTime = $('#beginTimeAll').val();
    var endTime = $('#endTimeAll').val();
    var monday = $("#mondayAll").prop('checked');
    var tuesday = $("#tuesdayAll").prop('checked');
    var thursday = $("#thursdayAll").prop('checked');
    var wednesday = $("#wednesdayAll").prop('checked');
    var friday = $("#fridayAll").prop('checked');
    var saturday = $("#saturdayAll").prop('checked');
    var sunday = $('#sundayAll').prop('checked');

    var validity = {
        nameValidity: nameValidity,
        validityScheduleList: [{
            beginTime: beginTime,
            endTime: endTime,
            monday: monday,
            tuesday: tuesday,
            thursday: thursday,
            wednesday: wednesday,
            friday: friday,
            saturday: saturday,
            sunday: sunday
        }]
    };

    $.ajax({
        type: "POST",
        url: "/schedule/addAll",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(validity),
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


function deleteValidity() {
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
                alert("error")
            }
        });
    } else {
        alert("Выберите наименование")
    }

}


$(document).ready(function () {
    $("body").on("click", ".deleteSchedule", function () {
        var id = $(this).closest('tr').find('input[type=hidden]').val();
        deleteSchedule(id);

    });
});


function deleteSchedule(id) {
    $.ajax({
        type: "POST",
        url: "/schedule/delete",
        data: {id: id},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            window.location.replace("/validity");
        },
        error: function () {
            alert("error")
        }
    });


}

function refresh() {
    window.location.replace("/validity");
}


// сделать добавление графиков в модалку

$("i").on("click", ".addScheduleDown", function () {
    alert("Hi man!");
    $(this).after("<p>Another paragraph!</p>");
});


//добавление новой таблицы в модалку
function addScheduleDown() {
    $('#addScheduleDown').append('<tr class="schedule-tr">' +
        '<td><input type="time" id="beginT"/></td>' +
        '<td><input type="time" id="endT"/></td>' +
        '<td><input type="checkbox" id="mondayField" class="mondayField"></td>' +
        '<td><input type="checkbox" id="tuesdayField" class="tuesdayField"></td>' +
        '<td><input type="checkbox" id="wednesdayField" class="wednesdayField"></td>' +
        '<td><input type="checkbox" id="thursdayField" class="thursdayField"></td>' +
        '<td><input type="checkbox" id="fridayField" class="fridayField"></td>' +
        '<td><input type="checkbox" id="saturdayField" class="saturdayField"></td>' +
        '<td><input type="checkbox" id="sundayField" class="sundayField"></td>' +
        '</tr>'
    );
}


function addField() {


    var schedules = [];

    $('.schedule-tr').each(function () {
        var nameValidity =  $("#validityNameAll").val();
        var beginTimeSchedule =    $(this).closest('tr').find('td:eq(0)').find('input[type=time]').val();
        var endTimeSchedule =      $(this).closest('tr').find('td:eq(1)').find('input[type=time]').val();
        var mondaySchedule =       $(this).closest('tr').find('td:eq(2)').find('input[type=checkbox]').prop('checked');
        var tuesdaySchedule =      $(this).closest('tr').find('td:eq(3)').find('input[type=checkbox]').prop('checked');
        var wednesdaySchedule =    $(this).closest('tr').find('td:eq(4)').find('input[type=checkbox]').prop('checked');
        var thursdaySchedule =     $(this).closest('tr').find('td:eq(5)').find('input[type=checkbox]').prop('checked');
        var fridaySchedule =       $(this).closest('tr').find('td:eq(6)').find('input[type=checkbox]').prop('checked');
        var saturdaySchedule =     $(this).closest('tr').find('td:eq(7)').find('input[type=checkbox]').prop('checked');
        var sundaySchedule =       $(this).closest('tr').find('td:eq(8)').find('input[type=checkbox]').prop('checked');

        var scheduleSchedule = {
            nameValidity:nameValidity,
            beginTime: beginTimeSchedule,
            endTime: endTimeSchedule,
            monday: mondaySchedule,
            tuesday: tuesdaySchedule,
            wednesday: wednesdaySchedule,
            thursday: thursdaySchedule,
            friday: fridaySchedule,
            saturday: saturdaySchedule,
            sunday: sundaySchedule
        };
        schedules.push(scheduleSchedule);
    });

    console.log(schedules);

    var discount = {
        nameValidity:nameValidity,
        schedules:schedules

    };


    $.ajax({
        type: "POST",
        url: "/validity/save",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(discount),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
        },
        error: function () {}
    });

}