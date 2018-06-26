var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('.selectView').click(function () {
        var nameNotTrimmed = $(this).text();
        var name = $.trim(nameNotTrimmed);

        if ($(this).closest('tr').hasClass('opened')) {
            var trimName = name.replace(/\s+/g, '');
            $('.' + trimName + '').closest('tr').each(function () {
                $(this).remove();
            });
            $(this).closest('tr').find('i').removeClass('fal fa-angle-down').addClass('fal fa-angle-right');
            $(this).closest('tr').removeClass('opened');
            return;
        }

        $('tr .active').removeClass('active');
        $(this).addClass('active').siblings().removeClass('active');
        $(this).closest('tr').addClass('opened');
        $(this).closest('tr').find('i').removeClass('fal fa-angle-down').addClass('fal fa-angle-down');

        $.ajax({
            type: "POST",
            url: "/scale_of_size/getValues",
            data: {scaleName: name},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {
                for (var i = 0; i < data.length; i++) {

                    var trimName = name.replace(/\s+/g, '');
                    var tr = $('.active').closest('tr').index();

                    if(data[i].removed != true) {

                        $('<tr id="shown" class="shown ' + trimName + '">' +
                            '<td>' + data[i].nameSize + '</td>' +
                            '<td>' + data[i].kitchenSize + '</td>' +
                            '<td><input type="checkbox" class="activated" ' + (data[i].defaultSize ? 'checked' : '') + '></td>' +
                            '<td><a class="btn .btn-primary btn-sm editValues" href="#"><i class="fa fa-pencil" aria-hidden="true" onclick="sendValuesToModal()"></i></a>' +
                            '<a class="btn btn-danger btn-sm deleteValues" href="#" onclick="deleteValues()"><i class="fa fa-trash" aria-hidden="true" ></i></a></td>' +
                            '</tr>').insertAfter($('tr:eq(' + (tr + 1) + ')')
                        );

                    }

                    if(data[i].removed == true && $('input.viewDel').is(':checked'))  {

                            $('<tr id="removed_el" class="shown ' + trimName + ' ">' +
                                '<td>' + data[i].nameSize + '</td>' +
                                '<td>' + data[i].kitchenSize + '</td>' +
                                '<td><input type="checkbox" class="activated" ' + (data[i].defaultSize ? 'checked' : '') + '></td>' +
                                '<td><a class="fa fa-ban"></a>' +
                                '</tr>').insertAfter($('tr:eq(' + (tr + 1) + ')')
                            );
                    }
                }
            },
            error: function (e) {
                alert("Ошибка в значении Шкалы размеров")
            }
        });
    });

//send checkbox in Server
    $("body").on("click", ".activated", function () {
        var tr = $(this).closest('tr');
        var nameSize = tr.find('td:eq(0)').text();
        var defaultSize = $(this).prop('checked');

        $.ajax({
            type: "POST",
            url: "/scale_of_size/changeDefaultSize",
            data: {nameSize: nameSize, defaultSize: defaultSize},
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


function viewDel(checkboxElem) {
    if (checkboxElem.checked) {
       // $('#removed_el').toggle;

    } else {
        window.location.replace("/scale_of_size");
    }
}


function addScale() {
    var nameScale = $("#nameScale").val();

    $.ajax({
        type: "POST",
        url: "/scale_of_size/addScale",
        data:  {
            nameScale: nameScale
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            window.location.replace("/scale_of_size");
        },
        error: function (e) {
            alert("error")
        }
    });
}


$(document).ready(function () {
    $('button.values').on('click', function() {
        //var info = $(this).closest('tr').find('td:eq(0)').text();
         nameTr = $(this).closest('tr').find('.selectView').text();
         trimNameTr = $.trim(nameTr);
    });
});

function addValues() {
    var scaleNameValue = trimNameTr;
    var nameSize = $("#nameSize").val();
    var kitchenSize = $("#kitchenSize").val();
    var defaultSize = $("#defaultSize").prop('checked');

    $.ajax({
        type: "POST",
        url: "/scale_of_size/addValues",
        data: {
            scaleNameValue:scaleNameValue,
            nameSize: nameSize,
            kitchenSize: kitchenSize,
            defaultSize:defaultSize
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            window.location.replace("/scale_of_size");
        },
        error: function (e) {
            alert("error add Values")
        }
    });
}


// Удаление значений шкалы размеров
function deleteValues() {

  //var tr = $('.item-active').closest('tr'); // -a*- использовали при использовании Выделение значений шкалы размеров для удаления
  //var nameSize = tr.find('td:eq(0)').text();

    var nameSize = $("#shown").find("td:eq(0)").text();



    $.ajax({
        type: "POST",
        url: "scale_of_size/delete/values",
        data: {nameSize: nameSize},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
           // tr.remove(); //-a*-
            window.location.replace("/scale_of_size");
        },
        error: function () {
            alert("error")
        }
    });
}


// Удаление шкалы размеров
function deleteScale() {
    // получение имени Шкалы размеров
    var nameScaleTrim = $("#records_table").has("tr").find('td:eq(0)').text();
    var nameScale = $.trim(nameScaleTrim);
    $.ajax({
        type: "POST",
        url: "scale_of_size/delete/scale",
        data: {nameScale: nameScale},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            window.location.replace("/scale_of_size");
        },
        error: function () {
            alert("error")
        }
    });
}


function sendValuesToModal() {

        $('a.editValues').on('click', function() {

        var el = $(this).closest('tr').find('td');
        var nameSize = el.eq(0).text();
        var kitchenSize = el.eq(1).text();
        var defaultSize = el.eq(2).find('input').prop('checked');

        if (defaultSize.checked) { defaultSize.value = "true"; }

        $('#editValues').modal('show'); //показываем модалку

        $("input[name=nameSize]").val(nameSize);
        $("input[name=kitchenSize]").val(kitchenSize);
        $("input[name=defaultSize]").prop('checked', defaultSize);
    });
}

function editValues() {
    var nameSizeNoEdit = $("input[name=nameSize]").val();
    var nameSize = document.getElementById("editNameSize").value;
    var kitchenSize = document.getElementById("editKitchenSize").value;
    var defaultSize = document.getElementById("editDefaultSize").checked;

    $.ajax({
        type: "POST",
        url: "/scale_of_size/edit/values",
        data: {
            nameSizeNoEdit: nameSizeNoEdit,
            nameSize: nameSize,
            kitchenSize:kitchenSize,
            defaultSize:defaultSize

        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            window.location.replace("/scale_of_size");
        },
        error: function () {
            alert("error")
        }
    });
}

$(document).ready(function () {
    $("#sendScaleToModal").click(function () {
        var el = $(this).closest('tr').find('td');
        var nameScaleTrim = el.eq(0).text();
        var nameScale = $.trim(nameScaleTrim);

        $('#editScale').modal('show'); //показываем модалку
        $("input[name=nameScale]").val(nameScale);
    });
});
function editScale() {

    var nameScaleNoEdit = $("input[name=nameScale]").val();
    var nameScale = document.getElementById("editNameScale").value;

    // получаю поле в таблице
    //var elem = $("#shown").find("td:eq(0)").text();


    $.ajax({
        type: "POST",
        url: "/scale_of_size/edit/scale",
        data: {
            nameScaleNoEdit: nameScaleNoEdit,
            nameScale: nameScale
           // nameSize:nameSize,
           // kitchenSize:kitchenSize,
           // defaultSize:defaultSize
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            window.location.replace("/scale_of_size");
        },
        error: function () {
            alert("error editScale")
        }
    });
}

function refresh() {
    window.location.replace("/scale_of_size");
}


