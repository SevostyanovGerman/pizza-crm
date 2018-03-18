let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('.btn-pincode-generator').click(function (e) {
        e.preventDefault();
        let num = Math.floor(Math.random() * 9000 + 1000);
        $('#input-pincode').val(num);
    });
});