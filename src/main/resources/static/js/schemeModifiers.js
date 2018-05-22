var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

function exit() {
    window.location.replace("/nomenclature");
}

$(document).ready(function () {
    $('tbody').on('click', '.modifier', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            $(this).addClass('selected');
        }
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

function saveModifiers() {
    $('.selected').each(function () {
        var name = $(this).closest('tr').find('td:eq(0)').text();
        var id = $(this).closest('tr').find('input[type=hidden]').val();
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
        name: name,
        id: id,
        modifierPropertyList: modifierPropertyList
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
