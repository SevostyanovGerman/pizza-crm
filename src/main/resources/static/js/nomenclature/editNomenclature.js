var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");


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
    $('tbody').on('click', '.listScheme', function () {
        $('.listScheme').removeClass('selected');
        $(this).addClass('selected');
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

function save() {
    var name = $('#name').val();
    var id = $('#id').val();
    var nomenclatureType = $('#nomenclatureType').val();
    var accountingCategory = $('#accountingCategory').val();
    var parentGroupName = $('#parentGroup option:selected').text();
    var parentGroupId = $('#parentGroup option:selected').val();
    var cookingTimeNorm = $('#cookingTimeNorm').val();
    var cookingTimePeak = $('#cookingTimePeak').val();
    var cookingPlace = $('#cookingPlace').val();
    var price = $('#priceByPriceList').val();
    var backgroundColor = $('#backgroundColor').val();
    var fontColor = $('#fontColor').val();
    var unitOfMeasurement = $("#measureUnit option:selected").text();

    var parentGroups = [];
    var nomenclatureParentGroupSet = {
        id: parentGroupId,
        name: parentGroupName
    };
    parentGroups.push(nomenclatureParentGroupSet);

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
            nomenclatureId: id,
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

    var packagingList = [];
    $('.packagingRow').each(function () {
        var tr = $(this).closest('tr');
        var id = tr.find('input[type=hidden]').val();
        var name = tr.find('td:eq(1)').find('input[type=text]').val();
        var code = tr.find('td:eq(0)').find('input[type=number]').val();
        var backIn = tr.find('td:eq(2)').find('input[type=checkbox]').prop('checked');
        var quantityInPackaging = tr.find('td:eq(3)').find('input[type=number]').val();
        var unitOfMeasurement = tr.find('.addOption option:selected').text();
        var packaging = {
            id: id,
            name: name,
            code: code,
            backIn: backIn,
            quantityInPackaging: quantityInPackaging,
            unitOfMeasurement: unitOfMeasurement
        };
        packagingList.push(packaging);
    });

    var nomenclature = {
        name: name,
        id: id,
        nomenclatureType: nomenclatureType,
        accountingCategory: accountingCategory,
        cookingTimeNorm: cookingTimeNorm,
        cookingTimePeak: cookingTimePeak,
        cookingPlace: cookingPlace,
        nomenclatureParentGroupSet: parentGroups,
        price: price,
        modifierPropertyList: modifierPropertyList,
        backgroundColor: backgroundColor,
        fontColor: fontColor,
        packagingList: packagingList,
        unitOfMeasurement: unitOfMeasurement
    };

    $.ajax({
        type: "POST",
        url: "/nomenclature/save",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(nomenclature),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            alert("Сохранено")
            // window.location.replace("/discountandextracharge");
        },
        error: function () {
            alert("error")
        }
    });
}

function saveAndExit() {
    var name = $('#name').val();
    var id = $('#id').val();
    var nomenclatureType = $('#nomenclatureType').val();
    var accountingCategory = $('#accountingCategory').val();
    var parentGroupName = $('#parentGroup option:selected').text();
    var parentGroupId = $('#parentGroup option:selected').val();
    var cookingTimeNorm = $('#cookingTimeNorm').val();
    var cookingTimePeak = $('#cookingTimePeak').val();
    var cookingPlace = $('#cookingPlace').val();
    var price = $('#priceByPriceList').val();
    var backgroundColor = $('#backgroundColor').val();
    var fontColor = $('#fontColor').val();
    var unitOfMeasurement = $("#measureUnit option:selected").text();

    var parentGroups = [];
    var nomenclatureParentGroupSet = {
        id: parentGroupId,
        name: parentGroupName
    };
    parentGroups.push(nomenclatureParentGroupSet);

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
            nomenclatureId: id,
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

    var packagingList = [];
    $('.packagingRow').each(function () {
        var tr = $(this).closest('tr');
        var id = tr.find('input[type=hidden]').val();
        var name = tr.find('td:eq(1)').find('input[type=text]').val();
        var code = tr.find('td:eq(0)').find('input[type=number]').val();
        var backIn = tr.find('td:eq(2)').find('input[type=checkbox]').prop('checked');
        var quantityInPackaging = tr.find('td:eq(3)').find('input[type=number]').val();
        var unitOfMeasurement = tr.find('.addOption option:selected').text();
        var packaging = {
            id: id,
            name: name,
            code: code,
            backIn: backIn,
            quantityInPackaging: quantityInPackaging,
            unitOfMeasurement: unitOfMeasurement
        };
        packagingList.push(packaging);
    });

    var nomenclature = {
        name: name,
        id: id,
        nomenclatureType: nomenclatureType,
        accountingCategory: accountingCategory,
        cookingTimeNorm: cookingTimeNorm,
        cookingTimePeak: cookingTimePeak,
        cookingPlace: cookingPlace,
        nomenclatureParentGroupSet: parentGroups,
        price: price,
        modifierPropertyList: modifierPropertyList,
        backgroundColor: backgroundColor,
        fontColor: fontColor,
        packagingList: packagingList,
        unitOfMeasurement: unitOfMeasurement
    };

    $.ajax({
        type: "POST",
        url: "/nomenclature/save",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(nomenclature),
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

function exit() {
    window.location.replace("/nomenclature");
}

function addPackagingRow() {

    $(".packaging").append($([
        '<tr class="packagingRow">' +
        '<td><input type="number"></td>' +
        '<td><input type="text"></td>' +
        '<td><input type="checkbox"></td>' +
        '<td><input type="number"></td>' +
        '<td><select class="addOption form-control">' +
        '</select></td>' +
        '</tr>'].join("\n")));

    $("#measureUnit option").each(function () {
        $('.packaging tr:last').find('.addOption').append($("<option></option>").text($(this).text()));
    });
}

$(document).ready(function () {
    $('.packaging').on('click', '.packagingRow', function () {
        $('.packagingRow').removeClass('packagingRow-active');
        $(this).addClass('packagingRow-active');
    });
});

function deletePackagingRow() {
    var tr = $('.packagingRow-active').closest('tr');
    tr.remove();
}

function showColors() {
    var backgroundColor = $('#backgroundColor').val();
    var fontColor = $('#fontColor').val();
    $('.showColors').css('background-color', backgroundColor);
    $('.showColors').css('color', fontColor);
}

function choiceScheme() {
    var name = $('.selected').find('td:eq(0)').text();
    $.ajax({
        type: "POST",
        url: "schemeModifiers/getListModifiers",
        data: {name: name},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                if (data[i] == true) {
                    $('#checkbox').prop('checked', true);
                }
                $(".tbody").append($([
                    '<tr>' +
                    '<td class="modifierName">' + data[i].name + '</td>' +
                    '<td><input type="number" class="inputs" value=' + data[i].minimum + '></td>' +
                    '<td><input type="number" class="inputs" value=' + data[i].byDefault + '></td>' +
                    '<td><input type="number" class="inputs" value=' + data[i].maximum + '></td>' +
                    '<td><input type="checkbox" class="activated" ' + (data[i].necessarily ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="activated" ' + (data[i].hideIf ? 'checked' : '') + '></td>' +
                    '<td><input type="checkbox" class="activated" ' + (data[i].restricted ? 'checked' : '') + '></td>' +
                    '<td><input type="number" class="inputs" value=' + data[i].free + '></td>' +
                    '<input type="hidden" class="hideId" value=' + data[i].modifierId + '>' +
                    '</tr>'].join("\n")));
            }
            $("#modifierScheme").val(name);

        },
        error: function () {
            alert("error")
        }
    });
}

function removeSchemeModifiers() {
    var name = $('.selected').find('td:eq(0)').text();
    $.ajax({
        type: "POST",
        url: "schemeModifiers/delete",
        data: {name: name},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {

        },
        error: function () {
            alert("error")
        }
    });
}
