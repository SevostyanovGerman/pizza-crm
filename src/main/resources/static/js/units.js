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

function updateUnit(par) {
    var id = $(par).closest('.modal-content').find('#updateid').val();
    var name = $(par).closest('.modal-content').find('#updateName').val();
    var shortName = $(par).closest('.modal-content').find('#updateShortName').val();
    var basic = $(par).closest('.modal-content').find('#updateBasic').prop('checked');
    var code = $(par).closest('.modal-content').find('#updateCode').val();

    var unit = {id: id,
                name: name,
                shortName: shortName,
                basic: basic,
                code: code
    };

    $.ajax({
        type: "POST",
        url: "/measurement/update",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(unit),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            location.href = "/measurement";
        },
        error: function (e) {
        }
    });
}