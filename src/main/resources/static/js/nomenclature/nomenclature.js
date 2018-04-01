var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('.parentGroup').click(function () {
        var name = $(this).text();
        if ($(this).closest('tr').hasClass('opened')) {
            var trimName = name.replace(/\s+/g, '');
            var selected = $('.' + trimName + '').closest('tr');
            selected.remove();
            $(this).closest('tr').removeClass('opened')
            return;
        }
        $('tr .active').removeClass('active');
        $(this).addClass('active').siblings().removeClass('active');
        $(this).closest('tr').addClass('opened');

        $.ajax({
            type: "POST",
            url: "/nomenclature/getNomenclatureParentGroup",
            data: {name: name},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var trimName = name.replace(/\s+/g, '');
                    var tr = $('.active').closest('tr').index();
                    $('<tr class="show ' + trimName + '">' +
                        '<td>' + data[i].name + '</td>' +
                        '<td></td>' +
                        '<td></td>' +
                        '<td>' + data[i].nomenclatureType + '</td>' +
                        '<td></td>' +
                        '<td></td>' +
                        '<td>' + data[i].price + '</td>' +
                        '<td>' + data[i].price + '</td>' +
                        '<td></td>' +
                        '<td></td>' +
                        '<td>' + data[i].cookingTimeNorm + '</td>' +
                        '<td>' + data[i].cookingTimePeak + '</td>' +
                        '</tr>').insertAfter($('tr:eq(' + (tr + 1) + ')'));
                }
            },
            error: function () {
                alert("error")
            }
        });
    });
});