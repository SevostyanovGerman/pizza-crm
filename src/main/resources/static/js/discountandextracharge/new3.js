// var csrfToken = $("meta[name='_csrf']").attr("content");
// var csrfHeader = $("meta[name='_csrf_header']").attr("content");

function next() {
    var id = $('#id').val();
    window.location.replace("/discountandextracharge/new4/"+id);
}