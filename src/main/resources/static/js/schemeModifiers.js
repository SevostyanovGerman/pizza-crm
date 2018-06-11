var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

function exit() {
    window.location.replace("/nomenclature");
}

$(document).ready(function () {
    $('.modalTbodySize').on('click', '.shown', function () {
        $('.shown').removeClass('nameScale', '');
        $(this).addClass('nameScale');
    });
});


$(document).ready(function () {
    $('.tbody').on('click', 'tr', function () {
        if ($(this).hasClass('active-modifier')) {
            $(this).removeClass('active-modifier');
        } else {
            $(this).addClass('active-modifier');
        }
    });
});

function deleteModifierRow() {
    $('tr.active-modifier').each(function () {
        var tr = $(this);
        tr.remove();
    })
}

function saveScaleOfSize() {
    $('.nameScale').each(function () {
        var name = $(this).closest('tr').find('td:eq(0)').text();
        $("#nameScale").val(name);
    })
}

function saveModifiers() {
    $('.selected').each(function () {
        var name = $(this).closest('tr').find('td:eq(0)').text();
        var id = $(".modifier.opened").closest('tr').find('input[type=hidden]').val();
        $(".tbody").append($([
            '<tr>' +
            '<td class="modifierName">' + name + '</td>' +
            '<td><input type="number" class="inputs"></td>' +
            '<td><input type="number" class="inputs"></td>' +
            '<td><input type="number" class="inputs"></td>' +
            '<td><input type="checkbox"></td>' +
            '<td><input type="checkbox"></td>' +
            '<td><input type="checkbox"></td>' +
            '<td><input type="number" class="inputs"></td>' +
            '<input type="hidden" class="hideId" value=' + id + '>' +
            '</tr>'].join("\n")));
    })
}

function saveAndExit() {
    var name = $('#name').val();
    var id = $('#id').val();
    var nameScaleOfSize = $('#nameScale').val();

    var modifierPropertyList = [];
    $('.modifierName').each(function () {
        var modifierName = $(this).text();
        var modifierId = $(this).closest('tr').find('input[type=hidden]').val();
        var minimum = $(this).closest('tr').find('td:eq(1)').find('input[type=number]').val();
        var byDefault = $(this).closest('tr').find('td:eq(2)').find('input[type=number]').val();
        var maximum = $(this).closest('tr').find('td:eq(3)').find('input[type=number]').val();
        var necessarily = $(this).closest('tr').find('td:eq(4)').find('input[type=checkbox]').prop('checked');
        var hideIf = $(this).closest('tr').find('td:eq(5)').find('input[type=checkbox]').prop('checked');
        var restricted = $(this).closest('tr').find('td:eq(6)').find('input[type=checkbox]').prop('checked');
        var free = $(this).closest('tr').find('td:eq(7)').find('input[type=number]').val();
        var modifierProperty = {
            schemeModifiersId: id,
            modifierId: modifierId,
            modifierName: modifierName,
            name: modifierName,
            minimum: minimum,
            byDefault: byDefault,
            maximum: maximum,
            necessarily: necessarily,
            hideIf: hideIf,
            restricted: restricted,
            free: free
        };
        modifierPropertyList.push(modifierProperty);
    });
    var schemeModifiers = {
        name: name + " " + nameScaleOfSize,
        id: id,
        modifierPropertyList: modifierPropertyList,
        nameScaleOfSize: nameScaleOfSize
    }

    $.ajax({
        type: "POST",
        url: "/schemeModifiers/save",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(schemeModifiers),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            window.location.replace("/nomenclature");
        },
        error: function () {
            alert("error")
        }
    });
}

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
        $(this).closest('tr').addClass('active').siblings().removeClass('active');
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
                    $('<tr class="shown ' + trimName + '">' +
                        '<td>' + data[i].nameSize + '</td>' +
                        '</tr>').insertAfter($('.modalTbodySize tr:eq(' + (tr) + ')')
                    );
                }

            },
            error: function () {
                alert("error")
            }
        });
    });
});

$(document).ready(function () {

    $('.parentGroup').click(function () {
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
        $(this).closest('tr').find('i').removeClass('fal fa-angle-right').addClass('fal fa-angle-down');

        $.ajax({
            type: "POST",
            url: "/nomenclature/getModifiers",
            data: {name: name},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var trimName = name.replace(/\s+/g, '');
                    var tr = $('.active').closest('tr').index();
                    $('<tr class="shown ' + trimName + '">' +
                        '<td>' + data[i].name + '</td>' +
                        '</tr>').insertAfter($('.modifierDownTr tr:eq(' + (tr) + ')')
                    );
                }
            },
            error: function (e) {
                console.log("Ошибка в значении Шкалы размеров");
            }
        });
    });

});
//-------------------------------------------------Выделяем модификаторы или группу модификаторов в модалке Модификаторы
$(document).ready(function () {
    $('.modifierDownTr').on('click', '.modifier', function () {
        if ($("#groupModifier").is(":checked") == true) {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                $(this).addClass('selected');
            }
        } else {
            $(".shown").removeClass('selected');
        }

    });
});
$(document).ready(function () {
    $('.modifierDownTr').on('click', '.shown', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $(this).addClass('selected');
        }
    });
});

$(document).ready(function () {
    $('tbody').on('click', '.parentGroup', function () {
        if ($("#groupModifier").is(":checked")) {

            if ($(this).closest('tr').hasClass('opened')) {
                $(this).addClass('selected');
            } else {
                $(this).removeClass('selected');
            }
        }
    });
});

function saveFilterModifiers() {
    if ($(".groupModifier").is(":checked")) {
        saveGroupModifiers();
    }
    else {
        saveModifiers();
    }
}

function saveGroupModifiers() {

    var id = $(".modifier.opened").closest('tr').find('input[type=hidden]').val();

    $("table tbody").find('tr.shown').each(function () {
        var tds = $(this).find('td'),
            name = tds.eq(0).text();

        $(".tbody").append($([
            '<tr>' +
            '<td class="modifierName">' + name + '</td>' +
            '<td><input type="number" class="inputs"></td>' +
            '<td><input type="number" class="inputs"></td>' +
            '<td><input type="number" class="inputs"></td>' +
            '<td><input type="checkbox"></td>' +
            '<td><input type="checkbox"></td>' +
            '<td><input type="checkbox"></td>' +
            '<td><input type="number" class="inputs"></td>' +
            '<input type="hidden" class="hideId" value=' + id + '>' +
            '</tr>'].join("\n"))
        );

    });
}


