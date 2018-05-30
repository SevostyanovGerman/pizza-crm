var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

// checkbox List paymentMethods
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

function checkboxListPaymentMethods (par) {

    var col = $(par).parent().parent().children().index($(par).parent());
    var namePm = $('.paymentMethods-names').closest('tr').find('th').eq(col).text();

    var checkbox = $(par).prop('checked');
    var idDiscount = $(par).closest('tr').find('input[type=hidden]').val();

    var discount = {
        id: idDiscount,
        enabled: checkbox,
        name: namePm
    };

    $.ajax({
        type: "POST",
        url: "/admin/discount/methods",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(discount),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {},
        error: function () {}
    });
}

