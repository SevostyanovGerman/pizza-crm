var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");



$(document).ready(function () {
    $('.selectView').click(function () {
        var nameNotTrimmed = $(this).text();
        var name = $.trim(nameNotTrimmed);

        if ($(this).closest('tr').hasClass('opened')) {
            var trimName = name.replace(/\s+/g, '');
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
            url: "/scale_of_size/getValues",
            data: {scaleName: name},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var trimName = name.replace(/\s+/g, '');
                    var tr = $('.active').closest('tr').index();

                    $('<tr class="shown ' + trimName + '">' +
                        '<td>' + data[i].nameSize + '</td>' +
                        '<td>' + data[i].kitchenSize + '</td>' +
                        '<td><input type="checkbox" class="activated" '+(data[i].defaultSize ? 'checked' : '' )+'></td>' +
                        '</tr>').insertAfter($('tr:eq(' + (tr + 1) + ')')
                    );
                }
            },
            error: function (e) {
                alert("error")
            }
        });
    });

//send checkbox in Server
    $("body").on("click", ".activated", function () {
        var tr = $(this).closest('tr');
        var nameSize = tr.find('td:eq(0)').text();
        var defaultSize = $(this).prop('checked');

        $.ajax({
            type: "POST",
            url: "/scale_of_size/changeDefaultSize",
            data: {nameSize: nameSize, defaultSize: defaultSize},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {

            },
            error: function () {
                alert("no checked")
            }
        });
    });

});

function addScale() {
    var nameScale = $("#nameScale").val();

    $.ajax({
        type: "POST",
        url: "/scale_of_size/addScale",
        data:  {
            nameScale: nameScale
        },
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


$(document).ready(function () {
    $('button.values').on('click', function() {
        //var info = $(this).closest('tr').find('td:eq(0)').text();
         nameTr = $(this).closest('tr').find('.selectView').text();
         trimNameTr = $.trim(nameTr);
    });
});

function addValues() {
    var scaleNameValue = trimNameTr;
    var nameSize = $("#nameSize").val();
    var kitchenSize = $("#kitchenSize").val();
    var defaultSize = $("#defaultSize").prop('checked');

    $.ajax({
        type: "POST",
        url: "/scale_of_size/addValues",
        data: {
            scaleNameValue:scaleNameValue,
            nameSize: nameSize,
            kitchenSize: kitchenSize,
            defaultSize:defaultSize
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            window.location.replace("/scale_of_size");
        },
        error: function (e) {
            alert("error add Values")
        }
    });
}


function deleteScale() {
    var selected = $('.item-active').html();
    if (selected !== undefined) {
        $.ajax({
            type: "POST",
            url: "/validity/delete",
            data: {name: selected},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/validity");
            },
            error: function (e) {
                // alert("error")
            }
        });
    } else {
        alert("Выберите наименование")
    }

}

function refresh() {
    window.location.replace("/validity");
}