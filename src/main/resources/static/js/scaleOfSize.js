var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");


$(document).ready(function () {
    $('.parentGroup').click(function () {
        var nameNotTrimmed = $(this).text();
        //в this находиться эелемент по которому кликнули тоесть <tr>
        //.text() - Получает текст выбранного элемента в наборе
        var name = $.trim(nameNotTrimmed);
        // trim - Функция удаления пробелов из начала и конца строки
        //.closest() - Ближайший подходящий предок
        if ($(this).closest('tr').hasClass('opened')) { //? что за opened
            var trimName = name.replace(/\s+/g, ''); //replace() - замена елементов строки
            $('.' + trimName + '').closest('tr').each(function () {
                $(this).remove();
            });
            $(this).closest('tr').find('i').removeClass('fa fa-minus-square-o').addClass('fa fa-plus-square-o');
            $(this).closest('tr').removeClass('opened');
            return;
        }
        $('tr .active').removeClass('active');
        $(this).addClass('active').siblings().removeClass('active');
        $(this).closest('tr').addClass('opened');
        $(this).closest('tr').find('i').removeClass('fa fa-plus-square-o').addClass('fa fa-minus-square-o');

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
                    $('<tr class="shown ' + trimName + '">' +
                        '<td>' + data[i].name + '</td>' +
                        '<td></td>' +
                        '<td></td>' +
                        '<td>' + data[i].nomenclatureType + '</td>' +
                        '<td></td>' +
                        '<td></td>' +
                        '<td>' + data[i].price + '</td>' +
                        '<td>' + data[i].price + '</td>' +
                        '<td></td>' +
                        '<td style="color: ' + data[i].fontColor + '; background: ' + data[i].backgroundColor + '">Цвет кнопки</td>' +
                        '<td>' + data[i].cookingTimeNorm + '</td>' +
                        '<td>' + data[i].cookingTimePeak + '</td>' +
                        '</tr>').insertAfter($('tr:eq(' + (tr + 1) + ')'));
                }
                $('.shown').draggable({
                    helper: 'clone'
                });
            },
            error: function () {
                alert("error parentGroup")
            }
        });
    });
});



/*$(document).ready(function () {
$('.plus').click(function () {
        $(this).parents('.order').nextUntil(".order", '.order_item').toggle();
    });

});*/


function addScale() {
    var nameScale = $("#nameScale").val();
    var nameSize = $("#nameSize").val();
    var kitchenSize = $("#kitchenSize").val();
    var defaultSize = $("#defaultSize").prop('checked');

    var scale = {
        nameScale: nameScale,
        valuesList:[{
            nameSize:nameSize,
            kitchenSize:kitchenSize,
            defaultSize:defaultSize
        }]
    };

    $.ajax({
        type: "POST",
        url: "/scale_of_size/save",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(scale),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            window.location.replace("/scale_of_size");
        },
        error: function (e) {
            alert("error")
        }
    });
}