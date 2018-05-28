var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");


//-------------------------------------------------Выделяем модификаторы или группу модификаторов в модалке Модификаторы
$(document).ready(function () {
    $("body").on("click", ".groupModifier", function () {
        if($(this).is(":checked")){
            //$(".shown").removeClass('selected');
            $(".shown").remove();
           // $(this).closest('tr').addClass('opened');

            $(".modifier").closest('tr').removeClass('opened');
           $(".modifier").find('i').removeClass('fal fa-angle-down').addClass('fal fa-angle-right');


            if($("tr").hasClass('opened')){
                $("td.selectView.active").addClass('selected');
            } else {
                $("td.selectView").removeClass('selected');
            }

        } else {
            $("td.selectView").removeClass('selected');
        }

    });
});
$(document).ready(function () {

    $('tbody').on('click', '.shown', function () {
      if ($("#groupModifier").is(":checked") == false){

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
    $('tbody').on('click', '.selectView', function () {
        if ($("#groupModifier").is(":checked")){

            if($(this).closest('tr').hasClass('opened')){
                $(this).addClass('selected');
            } else {
                $(this).removeClass('selected');
            }
        }
    });
});


//-------------------------------------------------------------------------Выделяем строку в модалке Схема модификаторов
$(document).ready(function () {
    $('tbody').on('click', '.listScheme', function () {
        $('.listScheme').removeClass('selected');
        $(this).addClass('selected');
    });
});

//--------------------------------------------------------------------------------Выделяем строку в таблице Модификаторы
$(document).ready(function () {
    $('.tbody').on('click', 'tr', function () {
            if ($(this).hasClass('active-modifier')) {
            $(this).removeClass('active-modifier');
        } else {
            $(this).addClass('active-modifier');
        }
    });
});

//---------------------------------------------------------------------- Удаляем выделенну строку в таблице Модификаторы
function deleteModifierRow() {
    $('tr.active-modifier').each(function () {
        var tr = $(this);
        tr.remove();
    })
}

//--------------------------------------------------------------Добавляем в модалке модификаторы к группам модификаторов

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
        $(this).addClass('active').siblings().removeClass('active'); //.siblings() поиск соседних элементов для выбраных элементов
        //$(this).addClass('active').siblings().removeClass('active'); //.siblings() поиск соседних элементов для выбраных элементов
        $(this).closest('tr').addClass('opened');
        $(this).closest('tr').find('i').removeClass('fal fa-angle-right').addClass('fal fa-angle-down');

        $.ajax({
            type: "POST",
            url: "getModifiers",
            data: {name: name},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var trimName = name.replace(/\s+/g, '');
                    var tr = $('.active').closest('tr').index();
                        $('<tr id="shown" class="shown ' + trimName + '">' +
                            '<td>' + data[i].name + '</td>' +
                            '</tr>').insertAfter($('.modifierDownTr tr:eq(' + (tr) + ')')
                        );
                }
            },
            error: function (e) {
                //alert("Ошибка в значении Шкалы размеров")
                console.log("Ошибка в значении Шкалы размеров");
            }
        });
    });

});

function addFilterModifiers() {
    if ($(".groupModifier").is(":checked")){
        addGroupModifiers();
    }
    else {
        addModifiers();
    }
}
//----------------------------------------------------------------------------------------добавляем модификаторы группой

function addGroupModifiers(){

    var id = $(".modifier.opened").closest('tr').find('input[type=hidden]').val();

    $("table tbody").find('tr.shown').each(function () {
        var tds = $(this).find('td'),
            name = tds.eq(0).text();
            console.log(name);

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

//--------------------------------------------------------------------------------добавляем модификаторы по отдельности
function addModifiers() {
    $('.selected').each(function () {
        var name = $(this).closest('tr').find('td:eq(0)').text();
        var id = $(".modifier.opened").closest('tr').find('input[type=hidden]').val();
        alert(id);
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
        unitOfMeasurement: unitOfMeasurement,
        removed: false
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
            alert("error save")
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
        unitOfMeasurement: unitOfMeasurement,
        removed: false
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
            alert("error saveAndExit")
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
