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

$(document).ready(function () {
    $('.btn-discount-delete').click(function () {
        $.ajax({
            type: 'DELETE',
            url: $(this).attr('href'),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                location.reload();
            }
        });
    });
});

function SaveDiscount() {

    var discountId = $("#discount-id").val();
    var discountName = $("#discount-name").val();
    var discountEnabled = $("#discount-enabled").prop('checked');
    var discountNameInCheck = $("#discount-nameInCheck").val();
    var discountType = $("#discount-type").val();
    var discountManualSelectWithOthers = $("#discount-manualSelectWithOthers").prop('checked');
    var discountDiscountApplicationMethod = $("#discount-discountApplicationMethod").val();
    var discountDiscountMode = $("#discount-discountMode").val();
    var discountDiscountCalculationMode = $("#discount-discountCalculationMode").val();
    var discountPriority = $("#discount-priority").val();
    var discountValue = $("#discount-value").val();

    var discountManualInput = $("#discount-manualInput").prop('checked');
    var discountAutomatic = $("#discount-automatic").prop('checked');
    var discountManualDishSelect = $("#discount-manualDishSelect").prop('checked');
    var discountCombinable = $("#discount-combinable").prop('checked');
    var discountDetailWhenPrinting = $("#discount-detailWhenPrinting").prop('checked');
    var discountComment = $("#discount-comment").val();

    var discountMinSumRestriction = $("#discount-minSumRestriction").prop('checked');
    var discountMinSum = 0;
    if ( $("#discount-minSumRestriction").is( ":checked" ) ) {
        var discountMinSum = $("#discount-minSum").val();
    }

    var discountScheduleRestriction = $("#discount-scheduleRestriction").prop('checked');
    var discountDiscountAssignMode = null;
    var schedules = [];
    if ( $("#discount-scheduleRestriction").is( ":checked" ) ) {
        var discountDiscountAssignMode = $("#discount-discountAssignMode").val();
        $('.schedule-tr').each(function () {
            var scheduleName =         $(this).text();
            var idSchedule =           $(this).closest('tr').find('input[type=hidden]').val();
            var beginTimeSchedule =    $(this).closest('tr').find('td:eq(1)').find('input[type=time]').val();
            var endTimeSchedule =      $(this).closest('tr').find('td:eq(2)').find('input[type=time]').val();
            var mondaySchedule =       $(this).closest('tr').find('td:eq(3)').find('input[type=checkbox]').prop('checked');
            var tuesdaySchedule =      $(this).closest('tr').find('td:eq(4)').find('input[type=checkbox]').prop('checked');
            var wednesdaySchedule =    $(this).closest('tr').find('td:eq(5)').find('input[type=checkbox]').prop('checked');
            var thursdaySchedule =     $(this).closest('tr').find('td:eq(6)').find('input[type=checkbox]').prop('checked');
            var fridaySchedule =       $(this).closest('tr').find('td:eq(7)').find('input[type=checkbox]').prop('checked');
            var saturdaySchedule =     $(this).closest('tr').find('td:eq(8)').find('input[type=checkbox]').prop('checked');
            var sundaySchedule =       $(this).closest('tr').find('td:eq(9)').find('input[type=checkbox]').prop('checked');
            var scheduleSchedule = {
                name: scheduleName,
                id: idSchedule,
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
    }

    var discountApplyForAllDiscountCategories = $("#discount-applyForAllDiscountCategories").prop('checked');
    var discountCategories = [];
    if (false==discountApplyForAllDiscountCategories) {

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
    }


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
        schedules: schedules,
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
        success: function () {
        },
        error: function () {}
    });
    location.href = "/admin/discount/list";
}

// Enable/Disable a site area
$(document).ready(function () {
    applicatedScheduleRestriction();
    applicatedMinSum();
    applicatedDiscountCategories();
});

function applicatedScheduleRestriction() {
    document.getElementById('discount-discountAssignMode').disabled = true;
    $("#table-schedules").find("input,button,textarea,select").attr("disabled", "disabled");

    document.getElementById('discount-scheduleRestriction').onchange = function() {
        document.getElementById('discount-discountAssignMode').disabled = !this.checked;
        $("#table-schedules").find("input,button,textarea,select").attr("disabled", !this.checked);
    };
}

function applicatedMinSum() {
    document.getElementById('discount-minSum').disabled = true;

    document.getElementById('discount-minSumRestriction').onchange = function() {
        document.getElementById('discount-minSum').disabled = !this.checked;
    };
}

function applicatedDiscountCategories() {
    $("#discountCategories-table").find("input,button,textarea,select").attr("disabled", "disabled");
    document.getElementById('discount-applyForAllDiscountCategories').onchange = function() {
        $("#discountCategories-table").find("input,button,textarea,select").attr("disabled", this.checked);
    };
}

// Add schedules in the schedules table
function addSchedule() {
    var scheduleId = $('#allSchedules option:selected').val();
    var schedule = {id: scheduleId};

    $.ajax({
        type: "POST",
        url: "/admin/discount/getSchedule",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(schedule),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            $('#table-schedules').append('<tr class="schedule-tr"> ' +
                '<td>'+data.name+'</td>' +
                '<td><input type="time" value="'+data.beginTime+'" disabled></td>' +
                '<td><input type="time" value="'+data.endTime+'" disabled></td>' +
                '<td><input disabled type="checkbox" class="activated" ' + (data.monday ? 'checked' : '') + '></td>' +
                '<td><input disabled type="checkbox" class="activated" ' + (data.tuesday ? 'checked' : '') + '></td>' +
                '<td><input disabled type="checkbox" class="activated" ' + (data.wednesday ? 'checked' : '') + '></td>' +
                '<td><input disabled type="checkbox" class="activated" ' + (data.thursday ? 'checked' : '') + '></td>' +
                '<td><input disabled type="checkbox" class="activated" ' + (data.friday ? 'checked' : '') + '></td>' +
                '<td><input disabled type="checkbox" class="activated" ' + (data.saturday ? 'checked' : '') + '></td>' +
                '<td><input disabled type="checkbox" class="activated" ' + (data.sunday ? 'checked' : '') + '></td>' +
                '<td><button type="button" class="btn btn-primary" onclick="deleteSchedule(this)">Удалить</button></td>' +
                '<input type="hidden" value="'+data.id+'">' +
                '</tr>');
        },
        error: function () {}
    });
}
// Delete schedules in the schedules table
function deleteSchedule(r) {
    var i = r.parentNode.parentNode.rowIndex;
    document.getElementById("table-schedules").deleteRow(i);
}


/*// Output List paymentMethods
/!*$(document).ready(function () {
    outputListPaymentMethods();
});

function outputListPaymentMethods() {

    $.ajax({
        type: "POST",
        url: "/admin/discount/getAllDiscounts",
        contentType: "application/json; charset=utf-8",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {

            var discounts = data;

            for (i = 0;  i < discounts.size; i++) {

            }

        },
        error: function () {}
    });
}*!/*/



