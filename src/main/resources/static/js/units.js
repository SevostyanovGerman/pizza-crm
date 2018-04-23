var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('.activated').click(function () {
        var tr = $(this).closest('tr');
        var name = tr.find('td:eq(0)').text();
        var basic = $(this).prop('checked');
        $.ajax({
            type: "POST",
            url: "/measurement/changeBasic",
            data: {name: name, basic: basic},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {

            },
            error: function () {
                alert("error")
            }
        });
    });
});