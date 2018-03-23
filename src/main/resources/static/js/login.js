$(document).ready(function () {
    $('.btn-num').click(function () {
        let pincode = $("#pincode");
        pincode.val(pincode.val() + $(this).val());
    });
});

$(document).ready(function () {
    $('.btn-clear').click(function () {
        $("#pincode").val('');
    });
});

$(document).ready(function () {
    $('.btn-remove-last').click(function () {
        let pincode = $("#pincode");
        pincode.val(pincode.val().slice(0, -1));
    });
});