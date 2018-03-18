let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('.btn-department-edit').click(function (e) {
        e.preventDefault();
        $.ajax({
            type: 'GET',
            url: $(this).attr('href'),
            success: function (response) {
                $('#department-id').val(response.id);
                $('#department-name').val(response.name);
                $('#department-edit-modal').modal('show');
            }
        });
    });
});

$(document).ready(function () {
    $('.btn-department-delete').click(function (e) {
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

$(document).ready(function () {
    $('.btn-department-save').click(function (e) {
        e.preventDefault();
        let departmentName = $('#department-name');
        departmentName.removeClass('is-invalid');
        if (isBlank(departmentName.val())) {
            departmentName.addClass('is-invalid');
            return;
        }
        $.ajax({
            type: 'POST',
            url: $(this).attr('href'),
            data: {
                id: $('#department-id').val(),
                name: departmentName.val()
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

function isBlank(str) {
    return (!str || /^\s*$/.test(str));
}