let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('.btn-position-edit').click(function (e) {
        e.preventDefault();
        $.ajax({
            type: "GET",
            url: $(this).attr('href'),
            success: function (response) {
                $('.modal-title').text('Редактирование <' + response.name + '>');
                $('#position-id').val(response.id);
                $('#position-name').val(response.name);
                $('#position-shortname').val(response.shortName);
                $('#position-comment').val(response.comment);
                $('#position-enabled').prop('checked', response.enabled);
                $('#position-edit-modal').modal('show');
            }
        });
    });
});

$(document).ready(function () {
    $('.btn-position-save').click(function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: $(this).attr('href'),
            data: {
                id: $('#position-id').val(),
                name:  $('#position-name').val(),
                shortName: $('#position-shortname').val(),
                comment: $('#position-comment').val(),
                enabled: $('#position-enabled').val()
            },
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
    $('.btn-position-delete').click(function (e) {
        e.preventDefault();
        $.ajax({
            type: "DELETE",
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