let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('.btn-pt-edit').click(function (e) {
        e.preventDefault();
        $.ajax({
            type: 'GET',
            url: $(this).attr('href'),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (response) {
                $('.modal-title').text(response.name);
                $('#pt-id').val(response.id);
                $('#pt-name').val(response.name);
                $('#pt-edit-modal').modal('show');
            }
        });
    });
});

$(document).ready(function () {
    $('.btn-pt-save').click(function (e) {
        e.preventDefault();
        let name = $('#pt-name');
        name.removeClass('is-invalid');
        if (!name.val() || /^\s*$/.test(name.val())) {
            name.addClass('is-invalid');
            return;
        }
        $.ajax({
            type: 'POST',
            url: $(this).attr('href'),
            data: {
                id: $('#pt-id').val(),
                name: name.val()
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
    $('.btn-pt-delete').click(function (e) {
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
        });
    });
});