var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

function next() {
    var id = $('#id').val();
    var active = $('#active').prop('checked');
    var advancedOptions = {
        id: id,
        active: active
    };
    $.ajax({
        type: "POST",
        url: "/discountandextracharge/saveStepFour",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(advancedOptions),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            window.location.replace("/discountandextracharge");
        },
        error: function () {
            alert("error")
        }
    });
}

function back() {
    var id = $('#id').val();
    window.location.replace("/discountandextracharge/step3/" + id);
}