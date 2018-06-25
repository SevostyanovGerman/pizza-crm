$(document).ready(function () {
    $('.dropdown-item').click(function (e) {
        e.preventDefault();
        let id = $(this).attr('id');
        console.log(id);
        $('#discount-categories-dropdown + id').text($(this).text());
    });
});

let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

function SaveDiscount() {

    var discountId = $("#discount-id").val();
    var discountName = $("#discount-name").val();
    var discountEnabled = $("#discount-enabled").prop('checked');
    var discountNameInCheck = $("#discount-nameInCheck").val();
    var discountType = $("#discount-type").val();
    var discountManualSelectWithOthers = $("#discount-manualSelectWithOthers").prop('checked');
    var discountManualInput = $("#discount-manualInput").prop('checked');
    var discountAutomatic = $("#discount-automatic").prop('checked');
    var discountManualDishSelect = $("#discount-manualDishSelect").prop('checked');
    var discountCombinable = $("#discount-combinable").prop('checked');
    var discountComment = $("#discount-comment").val();

// Save Priority
    var discountDiscountApplicationMethod = $("#discount-discountApplicationMethod").val();
    var discountPriority = 0;
    if ( discountDiscountApplicationMethod === "WITH_OTHERS" ) {
        var discountPriority = $("#discount-priority").val();
    }
//***********************************************************************************************************

// Save discountMinSum
    var discountMinSumRestriction = $("#discount-minSumRestriction").prop('checked');
    var discountMinSum = 0;
    if ( $("#discount-minSumRestriction").is( ":checked" ) ) {
        var discountMinSum = $("#discount-minSum").val();
    }
//***********************************************************************************************************

// Save schedules
    var discountScheduleRestriction = $("#discount-scheduleRestriction").prop('checked');
    var discountDiscountAssignMode = null;
    var validities = [];
    if ( $("#discount-scheduleRestriction").is( ":checked" ) ) {
        var discountDiscountAssignMode = $("#discount-discountAssignMode").val();
        $('.schedule-tr').each(function () {
            var idSchedule = $(this).closest('tr').find('input[type=hidden]').val();
            var validity = {
                id: idSchedule
            };
            validities.push(validity);
        });
    }
//***********************************************************************************************************

// Save DiscountCategories
    var discountApplyForAllDiscountCategories = $("#discount-applyForAllDiscountCategories").prop('checked');
    var discountDetailWhenPrinting = $("#discount-detailWhenPrinting").prop('checked');
    var discountDiscountMode = null;
    var discountDiscountCalculationMode = null;
    var discountValue = 0;
    var discountCategories = [];
    $('.discountCategories-row').each(function () {
        var discountCategoryName = $(this).text();
        var discountCategoryId = $(this).closest('tr').find('input[type=hidden]').val();
        var discountCategoryDiscountMode = $(this).closest('tr').find('td:eq(1)').find('option:selected').val();
        var discountCategoryValue = $(this).closest('tr').find('td:eq(2)').find('input[type=number]').val();
        var discountCategory = {
            id: discountCategoryId,
            name: discountCategoryName,
            discountMode: discountCategoryDiscountMode,
            value: discountCategoryValue
        };
        discountCategories.push(discountCategory);

    });

    if ($("#discount-applyForAllDiscountCategories").is( ":checked" )) {
        var discountDetailWhenPrinting = false;
        var discountDiscountMode = $("#discount-discountMode").val();
        var discountDiscountCalculationMode = $("#discount-discountCalculationMode").val();
        var discountValue = $("#discount-value").val();
        var discountCategories = [];
    }
//***********************************************************************************************************

    var discount = {
        id: discountId,
        name: discountName,
        enabled: discountEnabled,
        nameInCheck: discountNameInCheck,
        type: discountType,
        manualSelectWithOthers: discountManualSelectWithOthers,
        minSumRestriction: discountMinSumRestriction,
        minSum: discountMinSum,
        scheduleRestriction: discountScheduleRestriction,
        discountAssignMode: discountDiscountAssignMode,
        discountApplicationMethod: discountDiscountApplicationMethod,
        discountMode: discountDiscountMode,
        discountCalculationMode: discountDiscountCalculationMode,
        priority: discountPriority,
        value: discountValue,
        applyForAllDiscountCategories: discountApplyForAllDiscountCategories,
        manualInput: discountManualInput,
        automatic: discountAutomatic,
        manualDishSelect: discountManualDishSelect,
        combinable: discountCombinable,
        comment: discountComment,
        validities: validities,
        detailWhenPrinting: discountDetailWhenPrinting,
        discountCategories: discountCategories
    };

    $.ajax({
        type: "POST",
        url: "/admin/discount/save",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(discount),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {},
        error: function () {}
    });
    location.href = "/admin/discount/list";
}

// Enable/Disable a site area
$(document).ready(function () {
    applicatedScheduleRestriction();
    applicatedMinSum();
    applicatedDiscountCategories();
    applicatedPriority();
});

function applicatedScheduleRestriction() {
    var discountScheduleRestriction = $("#discount-scheduleRestriction").prop('checked');
    if(!(discountScheduleRestriction === true)) {
        document.getElementById('discount-discountAssignMode').disabled = true;
        $("#table-schedules").find("button,select").attr("disabled", "disabled");
    }
    document.getElementById('discount-scheduleRestriction').onchange = function() {
        document.getElementById('discount-discountAssignMode').disabled = !this.checked;
        $("#table-schedules").find("button,select").attr("disabled", !this.checked);
    };
}

function applicatedMinSum() {
    var discountMinSumRestriction = $("#discount-minSumRestriction").prop('checked');
    if(!(discountMinSumRestriction === true)) {
        document.getElementById('discount-minSum').disabled = true;
    }
    document.getElementById('discount-minSumRestriction').onchange = function() {
        document.getElementById('discount-minSum').disabled = !this.checked;
    };
}

function applicatedDiscountCategories() {

    document.getElementById('discount-discountMode').disabled = true;
    document.getElementById('discount-discountCalculationMode').disabled = true;
    document.getElementById('discount-value').disabled = true;

    var discountApplyForAllDiscountCategories = $("#discount-applyForAllDiscountCategories").prop('checked');
    if((discountApplyForAllDiscountCategories === true)) {
        $("#discountCategories-table").find("input,button,textarea,select").attr("disabled", true);
        document.getElementById('discount-detailWhenPrinting').disabled = true;
        document.getElementById('discount-discountMode').disabled = false;
        document.getElementById('discount-discountCalculationMode').disabled = false;
        document.getElementById('discount-value').disabled = false;
    }

    document.getElementById('discount-applyForAllDiscountCategories').onchange = function() {
        $("#discountCategories-table").find("input,button,textarea,select").attr("disabled", this.checked);
        document.getElementById('discount-discountMode').disabled = !this.checked;
        document.getElementById('discount-discountCalculationMode').disabled = !this.checked;
        document.getElementById('discount-value').disabled = !this.checked;
        document.getElementById('discount-detailWhenPrinting').disabled = this.checked;
    };
}

function applicatedPriority() {

    var discountDiscountApplicationMethod = $("#discount-discountApplicationMethod").val();
    if(discountDiscountApplicationMethod === "FULL_PRICE") {
        document.getElementById('discount-priority').disabled = true;
    }
    document.getElementById('discount-discountApplicationMethod').onchange = function() {
        var discountDiscountApplicationMethod = $("#discount-discountApplicationMethod").val();
        if (discountDiscountApplicationMethod === "WITH_OTHERS") {
            document.getElementById('discount-priority').disabled = false;
        } else {
            document.getElementById('discount-priority').disabled = true;
        }
    };

}

// Add schedules in the schedules table
function addValidity() {
    var ValidId = $('#allValidities option:selected').val();
    var validity = {id: ValidId};

    $.ajax({
        type: "POST",
        url: "/admin/discount/getValidity",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(validity),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {

            $('#validity-tbody2').append(
                '<tr class="schedule-tr">' +
                '<td>'+data.nameValidity+'</td>' +
                '<input type="hidden" value="'+data.id+'">' +
                '</tr>'
                );
            for (var i = 0; i < data.validityScheduleList.length; i++) {
                $('#validity-tbody2').append(
                    '<tr>' +
                    '<td></td>' +
                    '<td><input type="time" value="'+data.validityScheduleList[i].beginTime+'" disabled></td>' +
                    '<td><input type="time" value="'+data.validityScheduleList[i].endTime+'" disabled></td>' +
                    '<td><input disabled type="checkbox" class="monday activated"' + ((data.validityScheduleList[i].dayOfWeekList.indexOf( 'MONDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '<td><input disabled type="checkbox" class="tuesday activated"' + ((data.validityScheduleList[i].dayOfWeekList.indexOf( 'TUESDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '<td><input disabled type="checkbox" class="wednesday activated"' + ((data.validityScheduleList[i].dayOfWeekList.indexOf( 'WEDNESDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '<td><input disabled type="checkbox" class="thursday activated"' + ((data.validityScheduleList[i].dayOfWeekList.indexOf( 'THURSDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '<td><input disabled type="checkbox" class="friday activated"' + ((data.validityScheduleList[i].dayOfWeekList.indexOf( 'FRIDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '<td><input disabled type="checkbox" class="saturday activated"' + ((data.validityScheduleList[i].dayOfWeekList.indexOf( 'SATURDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '<td><input disabled type="checkbox" class="sunday activated"' + ((data.validityScheduleList[i].dayOfWeekList.indexOf( 'SUNDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                    '</tr>'
                );
            }
        },
        error: function () {}
    });
}

$(document).ready(function () {

        var name = $('#discount-name').val();
        var discount = {name: name};

        $.ajax({
            type: "POST",
            url: "/admin/discount/getValidityForDiscount",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(discount),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {

                for (var j = 0; j < data.length; j++) {

                    $('#validity-tbody2').append(
                        '<tr class="schedule-tr">' +
                        '<td>'+data[j].nameValidity+'</td>' +
                        '<input type="hidden" value="'+data[j].id+'">' +
                        '</tr>'
                    );

                    for (var i = 0; i < data[j].validityScheduleList.length; i++) {
                        $('#validity-tbody2').append(
                            '<tr>' +
                            '<td></td>' +
                            '<td><input type="time" value="'+data[j].validityScheduleList[i].beginTime+'" disabled></td>' +
                            '<td><input type="time" value="'+data[j].validityScheduleList[i].endTime+'" disabled></td>' +
                            '<td><input disabled type="checkbox" class="monday activated"' + ((data[j].validityScheduleList[i].dayOfWeekList.indexOf( 'MONDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                            '<td><input disabled type="checkbox" class="tuesday activated"' + ((data[j].validityScheduleList[i].dayOfWeekList.indexOf( 'TUESDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                            '<td><input disabled type="checkbox" class="wednesday activated"' + ((data[j].validityScheduleList[i].dayOfWeekList.indexOf( 'WEDNESDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                            '<td><input disabled type="checkbox" class="thursday activated"' + ((data[j].validityScheduleList[i].dayOfWeekList.indexOf( 'THURSDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                            '<td><input disabled type="checkbox" class="friday activated"' + ((data[j].validityScheduleList[i].dayOfWeekList.indexOf( 'FRIDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                            '<td><input disabled type="checkbox" class="saturday activated"' + ((data[j].validityScheduleList[i].dayOfWeekList.indexOf( 'SATURDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                            '<td><input disabled type="checkbox" class="sunday activated"' + ((data[j].validityScheduleList[i].dayOfWeekList.indexOf( 'SUNDAY' )) != (-1) ? 'checked' : '') + '></td>' +
                            '</tr>'
                        );
                    }
                }
            },
            error: function () {}
        });
});

// Delete schedules in the schedules table
$(document).ready(function(){
    $('#clear-list').click(function () {
        $('#validity-tbody2').each(function () {
            $(this).closest('table').find('tbody').empty();
            }
        );
    });
});
