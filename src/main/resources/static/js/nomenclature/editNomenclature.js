var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");


$(document).ready(function () {
    $('.modifier').click(function () {
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
    var nomenclatureType = $('#nomenclatureType').val();
    var accountingCategory = $('#accountingCategory').val();
    var parentGroupName = $('#parentGroup option:selected').text();
    var parentGroupId = $('#parentGroup option:selected').val();
    var cookingTimeNorm = $('#cookingTimeNorm').val();
    var cookingTimePeak = $('#cookingTimePeak').val();
    var cookingPlace = $('#cookingPlace').val();
    var price = $('priceByPriceList').val();
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
        nomenclatureList.push(modifier)
    });

    var nomenclature = {
        name: name,
        nomenclatureType: nomenclatureType,
        accountingCategory: accountingCategory,
        cookingTimeNorm: cookingTimeNorm,
        cookingTimePeak: cookingTimePeak,
        cookingPlace: cookingPlace,
        nomenclatureParentGroupSet: parentGroups,
        price: price,
        nomenclatureList: nomenclatureList
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