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

                $('#showSchedule').append('<tr class="tableSchedule">' +
                    '<input id="id" class="ids" type="hidden" value="' + data[i].id + '">' +
                    '<td><input type="time" value="' + data[i].beginTime + '"></td>' +
                    '<td><input type="time" value="' + data[i].endTime + '"></td>' +
                    '<td><input type="checkbox" class="monday activated"' + ((data[i].dayOfWeekList.indexOf( 'MONDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="tuesday activated"' + ((data[i].dayOfWeekList.indexOf( 'TUESDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="wednesday activated"' + ((data[i].dayOfWeekList.indexOf( 'WEDNESDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="thursday activated"' + ((data[i].dayOfWeekList.indexOf( 'THURSDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="friday activated"' + ((data[i].dayOfWeekList.indexOf( 'FRIDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="saturday activated"' + ((data[i].dayOfWeekList.indexOf( 'SATURDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="sunday activated"' + ((data[i].dayOfWeekList.indexOf( 'SUNDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '<td><a class="btn btn-danger btn-sm deleteSchedule" href="#"><i class="fa fa-trash" aria-hidden="true" ></i></a></td>' +
                    '</tr>');
            }

        },
        error: function (e) {
            alert("error")
        }
    });
}


// add one schedule with use button "+"
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

function deleteValidity() {
    var selected = $('.item-active').html();
    if (selected !== undefined) {
        $.ajax({
            type: "POST",
            url: "/validity/deleteValidity",
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

//deleteSchedule
$(document).ready(function () {
    $("body").on("click", ".deleteSchedule", function () {
        var id = $(this).closest('tr').find('input[type=hidden]').val();
        deleteSchedule(id);

    });
});


function deleteSchedule(id) {
    $.ajax({
        type: "POST",
        url: "/schedule/deleteSchedule",
        data: {id: id},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            window.location.replace("/validity");
        },
        error: function () {
            alert("error delete")
        }
    });


}


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

//удаление строк из модалки

$(document).ready(function () {
    $('#addScheduleDown').on('click', 'tr', function () {
        if ($(this).hasClass('active-modifier')) {
            $(this).removeClass('active-modifier');
        } else {
            $(this).addClass('active-modifier');
        }
    });
});


function delScheduleDown() {
    $('tr.active-modifier').each(function () {
        var tr = $(this);
        tr.remove();
    })
}


function addField() {

    var nameValidity = $('#validityName').val();
    var schedules = [];

    $('.schedule-tr').each(function () {
        var beginTimeSchedule =    $(this).closest('tr').find('td:eq(0)').find('input[type=time]').val();
        var endTimeSchedule =      $(this).closest('tr').find('td:eq(1)').find('input[type=time]').val();
        var mondaySchedule =       $(this).closest('tr').find('td:eq(2)').find('input[type=checkbox]').prop('checked');
        var tuesdaySchedule =      $(this).closest('tr').find('td:eq(3)').find('input[type=checkbox]').prop('checked');
        var wednesdaySchedule =    $(this).closest('tr').find('td:eq(4)').find('input[type=checkbox]').prop('checked');
        var thursdaySchedule =     $(this).closest('tr').find('td:eq(5)').find('input[type=checkbox]').prop('checked');
        var fridaySchedule =       $(this).closest('tr').find('td:eq(6)').find('input[type=checkbox]').prop('checked');
        var saturdaySchedule =     $(this).closest('tr').find('td:eq(7)').find('input[type=checkbox]').prop('checked');
        var sundaySchedule =       $(this).closest('tr').find('td:eq(8)').find('input[type=checkbox]').prop('checked');

        var dayOfWeekList = [];
        if (mondaySchedule == true) {dayOfWeekList.push("MONDAY");}
        if (tuesdaySchedule == true) {dayOfWeekList.push("TUESDAY");}
        if (thursdaySchedule == true) {dayOfWeekList.push("THURSDAY");}
        if (wednesdaySchedule == true) {dayOfWeekList.push("WEDNESDAY");}
        if (fridaySchedule == true) {dayOfWeekList.push("FRIDAY");}
        if (saturdaySchedule == true) {dayOfWeekList.push("SATURDAY");}
        if (sundaySchedule == true) {dayOfWeekList.push("SUNDAY");}

        var scheduleSchedule = {
            beginTime: beginTimeSchedule,
            endTime: endTimeSchedule,
            dayOfWeekList: dayOfWeekList
        };

        schedules.push(scheduleSchedule);

    });

     //---------------------------!!!----------------------------------
    //всегда переменная которая отправляеться  с помощь JSON
    //должна иметь имя поля в сущьности :)
    var validity = {
        nameValidity: nameValidity,
        validityScheduleList: schedules
    };


    $.ajax({
        type: "POST",
        url: "/validity/addField",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(validity),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            window.location.replace("/validity");

        },
        error: function () {}
    });

}

function refresh() {
    window.location.replace("/validity");
}


function exit() {
    window.location.replace("/newDecree");
}

function saveAndExit() {

}


//Код Сохранить все
function save() {
    var id = $(".item-active").closest('tr').find('input[type=hidden]').val();
    var nameValidity = $("#scheduleListName").val();
    var schedules = [];
    console.log("save(): " + id);
    $('.tableSchedule').each(function () {
       // var id = $(this).closest('tr').find('input[type=hidden]').val();

        var beginTime = $('#showSchedule tr').find("input[type=time]").eq(0).val();
        var endTime = $('#showSchedule tr').find("input[type=time]").eq(1).val();
        var monday = $(".monday").prop('checked');
        var tuesday = $(".tuesday").prop('checked');
        var thursday = $(".thursday").prop('checked');
        var wednesday = $(".wednesday").prop('checked');
        var friday = $(".friday").prop('checked');
        var saturday = $(".saturday").prop('checked');
        var sunday = $('.sunday').prop('checked');

        var dayOfWeekList = [];
        if (monday == true) {dayOfWeekList.push("MONDAY");}
        if (tuesday == true) {dayOfWeekList.push("TUESDAY");}
        if (thursday == true) {dayOfWeekList.push("THURSDAY");}
        if (wednesday == true) {dayOfWeekList.push("WEDNESDAY");}
        if (friday == true) {dayOfWeekList.push("FRIDAY");}
        if (saturday == true) {dayOfWeekList.push("SATURDAY");}
        if (sunday == true) {dayOfWeekList.push("SUNDAY");}

        var scheduleSchedule = {
         //   id: id,
            name: name,
            beginTime: beginTime,
            endTime: endTime,
            dayOfWeekList: dayOfWeekList
        };

        schedules.push(scheduleSchedule);
    });

    var validity = {
        id:id,
        nameValidity: nameValidity,
        validityScheduleList: schedules
    };


    $.ajax({
        type: "POST",
        url: "/validity/save",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(validity),
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

//Код Сохранить все и Выйти
function saveAndExit() {
    var id = $(".item-active").closest('tr').find('input[type=hidden]').val();
    var nameValidity = $("#scheduleListName").val();
    var schedules = [];
    console.log("save(): " + id);
    $('.tableSchedule').each(function () {
        // var id = $(this).closest('tr').find('input[type=hidden]').val();

        var beginTime = $('#showSchedule tr').find("input[type=time]").eq(0).val();
        var endTime = $('#showSchedule tr').find("input[type=time]").eq(1).val();
        var monday = $(".monday").prop('checked');
        var tuesday = $(".tuesday").prop('checked');
        var thursday = $(".thursday").prop('checked');
        var wednesday = $(".wednesday").prop('checked');
        var friday = $(".friday").prop('checked');
        var saturday = $(".saturday").prop('checked');
        var sunday = $('.sunday').prop('checked');

        var dayOfWeekList = [];
        if (monday == true) {dayOfWeekList.push("MONDAY");}
        if (tuesday == true) {dayOfWeekList.push("TUESDAY");}
        if (thursday == true) {dayOfWeekList.push("THURSDAY");}
        if (wednesday == true) {dayOfWeekList.push("WEDNESDAY");}
        if (friday == true) {dayOfWeekList.push("FRIDAY");}
        if (saturday == true) {dayOfWeekList.push("SATURDAY");}
        if (sunday == true) {dayOfWeekList.push("SUNDAY");}

        var scheduleSchedule = {
            //   id: id,
            name: name,
            beginTime: beginTime,
            endTime: endTime,
            dayOfWeekList: dayOfWeekList
        };

        schedules.push(scheduleSchedule);
    });

    var validity = {
        id:id,
        nameValidity: nameValidity,
        validityScheduleList: schedules
    };


    $.ajax({
        type: "POST",
        url: "/validity/save",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(validity),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            window.location.replace("/newDecree");
        },
        error: function (e) {
            alert("error")
        }
    });
}