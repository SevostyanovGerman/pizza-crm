var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

var options = {
    valueNames: ['name']
};
new List('users', options);
new List('users1', options);
$(function () {
    $("#sortable1, #sortable2").sortable({
        connectWith: ".connectedSortable",
        update: function (event, ui) {
            new List('users', options);
            new List('users1', options);
        }
    }).disableSelection();
});


$(".btn-save").click(function () {

    var mylist = [];
    $("ul.list-g > li > p").each(function () {
        mylist.push({
            "id": $(this).attr("value"),
            "name": $(this).text()
        });
    });
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/update/categoriesdish/" + $(".cat-name").text(),
        data: JSON.stringify(mylist),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
        },
        error: function (e) {
        }
    });
});

