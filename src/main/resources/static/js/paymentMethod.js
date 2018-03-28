let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('.btn-pm-edit').click(function (e) {
        e.preventDefault();
        $.ajax({
            type: 'GET',
            url: '/admin/paymentType/all',
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (response) {
                let ptSelect = $('#pm-paymentType');
                ptSelect.empty();
                $.each(response, function (k, v) {
                    ptSelect.append($("<option></option>")
                        .attr("value", v.id)
                        .text(v.name));
                });
            }
        });
        $.ajax({
            type: 'GET',
            url: $(this).attr('href'),
            success: function (response) {
                $('.modal-title').text(response.name);
                $('#pm-id').val(response.id);
                $('#pm-name').val(response.name);
                $('#pm-paymentType').val(response.paymentType);
                $('#pm-printCashBill').prop('checked', response.printCashBill);
                $('#pm-nameInCashBill').val(response.nameInCashBill);
                $('#pm-combinable').prop('checked', response.combinable);
                $('#pm-manualInput').prop('checked', response.manualInput);
                $('#pm-comment').val(response.comment);
                $('#pm-edit-modal').modal('show');
            }
        });
    });
});

$(document).ready(function () {
    $('.btn-pm-save').click(function (e) {
        e.preventDefault();
        let requestData = {
            paymentMethod: {
                id: $('#pm-id').val(),
                name: $('#pm-name').val(),
                printCashBill: $('#pm-printCashBill').prop('checked'),
                nameInCashBill: $('#pm-nameInCashBill').val(),
                combinable: $('#pm-combinable').prop('checked'),
                manualInput: $('#pm-manualInput').prop('checked'),
                comment: $('#pm-comment').val()
            },
            paymentType: {
                id: $('#pm-paymentType option:selected').val(),
                name: $('#pm-paymentType option:selected').text()
            },
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