let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('.btn-pincode-generator').click(function generatePincode(e) {
        e.preventDefault();
        let num = Math.floor(Math.random() * 9000 + 1000);
        while (checkPincode(num.toString()) !== undefined) {
            num = Math.floor(Math.random() * 9000 + 1000);
        }
        $('#input-pincode').val(num);
    });
});

function checkPincode(value) {
    $.ajax({
        type: 'GET',
        url: $(this).attr('href'),
        data: {pincode: value},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (response) {
            return response.pincode;
        }
    });
}