$(document).ready(function () {
    $('.dropdown-item').click(function (e) {
        e.preventDefault();
        let id = $(this).attr('id');
        console.log(id);
        $('#discount-categories-dropdown + id').text($(this).text());
    });
});