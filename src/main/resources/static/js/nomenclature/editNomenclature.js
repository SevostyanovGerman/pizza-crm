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

    var parentGroups = [];
    var nomenclatureParentGroupSet = {
        id: parentGroupId,
        name: parentGroupName
    };
    parentGroups.push(nomenclatureParentGroupSet);

    var nomenclatureList = [];
    $('.modifierName').each(function () {
        var modifierName = $(this).text();
        var id = $(this).closest('tr').find('input[type=hidden]').val();
        var modifier = {
            modifierName: modifierName,
            id: id
        };
        nomenclatureList.push(modifier);
    });

    var packagingList = [];
    $('.packagingRow').each(function () {
        var tr = $(this).closest('tr');
        var id = tr.find('input[type=hidden]').val();
        var name = tr.find('td:eq(1)').find('input[type=text]').val();
        var code = tr.find('td:eq(0)').find('input[type=number]').val();
        var backIn = tr.find('td:eq(2)').find('input[type=checkbox]').prop('checked');
        var quantityInPackaging = tr.find('td:eq(3)').find('input[type=number]').val();
        var packaging = {
            id: id,
            name: name,
            code: code,
            backIn: backIn,
            quantityInPackaging: quantityInPackaging
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
        nomenclatureList: nomenclatureList,
        backgroundColor: backgroundColor,
        fontColor: fontColor,
        packagingList: packagingList
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

    var parentGroups = [];
    var nomenclatureParentGroupSet = {
        id: parentGroupId,
        name: parentGroupName
    };
    parentGroups.push(nomenclatureParentGroupSet);

    var nomenclatureList = [];
    $('.modifierName').each(function () {
        var modifierName = $(this).text();
        var id = $(this).closest('tr').find('input[type=hidden]').val();
        var modifier = {
            modifierName: modifierName,
            id: id
        };
        nomenclatureList.push(modifier);
    });

    var packagingList = [];
    $('.packagingRow').each(function () {
        var tr = $(this).closest('tr');
        var id = tr.find('input[type=hidden]').val();
        var name = tr.find('td:eq(1)').find('input[type=text]').val();
        var code = tr.find('td:eq(0)').find('input[type=number]').val();
        var backIn = tr.find('td:eq(2)').find('input[type=checkbox]').prop('checked');
        var quantityInPackaging = tr.find('td:eq(3)').find('input[type=number]').val();
        var packaging = {
            id: id,
            name: name,
            code: code,
            backIn: backIn,
            quantityInPackaging: quantityInPackaging
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
        nomenclatureList: nomenclatureList,
        backgroundColor: backgroundColor,
        fontColor: fontColor,
        packagingList: packagingList
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
        '<td><select class="form-control">' +
        '<option>порц</option>' +
        '<option>кг</option>' +
        '<option>шт</option>' +
        '<option>л</option>' +
        '</select></td>' +
        '</tr>'].join("\n")));
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