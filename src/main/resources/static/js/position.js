let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('.btn-position-edit').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: "GET",
            url: $(this).attr('href'),
            success: function (response) {
                showPositionEditModal(response);
            }
        });
        $.get()
    });
});

$(document).ready(function () {
    $('.btn-position-save').click(function () {
        let position = {
            id: $('#position-id').val(),
            name:  $('#position-name').val(),
            shortName: $('#position-shortname').val(),
            comment: $('#position-comment').val(),
            enabled: $('#position-enabled').val()
        };
        $.ajax({
            type: "POST",
            url: $(this).attr('href'),
            data: position,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                $('#position-edit-modal').modal('hide');
                location.reload();
            }
        });
    });
});

$(document).ready(function () {
    $('.btn-position-delete').click(function () {
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

function showPositionEditModal(positionObject) {
    console.log(positionObject);
    $('.modal-title').text('Редактирование <' + positionObject.name + '>');
    $('#position-id').val(positionObject.id);
    $('#position-name').val(positionObject.name);
    $('#position-shortname').val(positionObject.shortName);
    $('#position-comment').val(positionObject.comment);
    $('#position-enabled').prop('checked', positionObject.enabled);
    $('#position-edit-modal').modal('show');
}