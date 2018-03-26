$(document).ready(function () {
    $('.btn-pm-edit').click(function (e) {
        e.preventDefault();
        $.ajax({
            type: 'GET',
            url: $(this).attr('href'),
            success: function (response) {
                                
            }
        });
        $('#pm-edit-modal').modal('show');
    });
});
