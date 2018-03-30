let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('.btn-pm-edit').click(function (e) {
        e.preventDefault();
        fillPaymentTypes();
        $.ajax({
            type: 'GET',
            url: $(this).attr('href'),
            success: function (response) {
                $('.modal-title').text(response.name);
                $('#pm-id').val(response.id);
                $('#pm-name').val(response.name);
                $('#pm-paymentType option[value="'+ response.paymentType.id +'"]').attr('selected', true);
                $('#pm-printCashBill').prop('checked', response.printCashBill).change();
                $('#pm-nameInCashBill').val(response.nameInCashBill);
                $('#pm-combinable').prop('checked', response.combinable).change();
                $('#pm-manualInput').prop('checked', response.manualInput).change();
                $('#pm-comment').val(response.comment);
                $('#pm-edit-modal').modal('show');
            }
        });
    });
});

$(document).ready(function () {
    $('.btn-pm-save').click(function (e) {
        e.preventDefault();
        if (!validateData()) {
            return;
        }
        let requestData = {
            id: $('#pm-id').val(),
            name: $('#pm-name').val(),
            paymentType: {
                id: $('#pm-paymentType option:selected').val(),
                name: $('#pm-paymentType option:selected').text()
            },
            printCashBill: $('#pm-printCashBill').prop('checked'),
            nameInCashBill: $('#pm-nameInCashBill').val(),
            combinable: $('#pm-combinable').prop('checked'),
            manualInput: $('#pm-manualInput').prop('checked'),
            comment: $('#pm-comment').val()
        };
        $.ajax({
            type: 'POST',
            url: $(this).attr('href'),
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(requestData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                location.reload();
            }
        });
    });
});

$(document).ready(function () {
    $('.btn-pm-delete').click(function (e) {
        e.preventDefault();
        $.ajax({
            type: 'DELETE',
            url: $(this).attr('href'),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                location.reload();
            }
        })
    });
});

$(document).ready(function () {
    $('.btn-pm-new').click(function (e) {
        e.preventDefault();
        fillPaymentTypes();
        $('#pm-paymentType option:eq(0)').attr('selected', true);
        $('#pm-id').val("");
        $('#pm-name').val("");
        $('#pm-printCashBill').prop('checked', false).change();
        $('#pm-nameInCashBill').val("");
        $('#pm-combinable').prop('checked', false).change();
        $('#pm-manualInput').prop('checked', false).change();
        $('#pm-comment').val("");
        $('#pm-edit-modal').modal('show');
    })
});

function fillPaymentTypes() {
    $('#pm-paymentType').empty();
    $.ajax({
        type: 'GET',
        url: '/admin/paymentType/all',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (response) {
            let ptSelect = $('#pm-paymentType');
            $.each(response, function (k, v) {
                ptSelect.append($("<option></option>")
                    .attr("value", v.id)
                    .text(v.name));
            });
        }
    });
}

function validateData() {
    let name = $('#pm-name');
    name.removeClass('is-invalid');
    if (!name.val().trim()) {
        name.addClass('is-invalid');
        return false;
    }
    return true;
}