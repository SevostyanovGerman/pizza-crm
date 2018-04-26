var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('.parentGroup').click(function () {
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
                alert("error")
            }
        });
    });
});
$(document).ready(function () {
    $('.shown').draggable({
        helper: 'clone'
    });
});

$(document).ready(function () {
    $('tbody').on('click', '.shown', function () {
        $('.shown').removeClass('item-active');
        $(this).addClass('item-active');
    });
});

$(function () {
    $(".parent").droppable({containment: "parent"});
    $(".parent").droppable({
        drop: function (event, ui) {
            var tr = ui.draggable;
            var name = tr.find('td:eq(0)').text();
            var type = tr.find('td:eq(3)').text();
            var price = tr.find('td:eq(6)').text();
            var cookingTimeNorm = tr.find('td:eq(10)').text();
            var cookingTimePeak = tr.find('td:eq(11)').text();
            var parentGroupName = $(this).closest('tr').find('td:eq(0)').text();
            var parentGroupNameTrimmed = parentGroupName.trim();
            var index = $(this).closest('tr').index();
            if ($(this).hasClass('opened')) {
                $('<tr class="shown ' + parentGroupNameTrimmed.replace(/\s+/g, '') + '">' +
                    '<td>' + name + '</td>' +
                    '<td></td>' +   //vendorCode
                    '<td></td>' +   //code
                    '<td>' + type + '</td>' +   //type
                    '<td></td>' +   //remainder
                    '<td></td>' +   //unit
                    '<td>' + price + '</td>' +   //price
                    '<td>' + price + '</td>' +   //cost price
                    '<td></td>' +   //place of sale
                    '<td></td>' +   //button color
                    '<td>' + cookingTimeNorm + '</td>' +   //cookingTimeNorm
                    '<td>' + cookingTimePeak + '</td>' +   //cookingTimePeak
                    '</tr>').insertAfter($('tr:eq(' + (index + 1) + ')'));
                $('.shown').draggable({
                    helper: 'clone'
                });
                $.ajax({
                    type: "POST",
                    url: "/nomenclature/changeParentGroup",
                    data: {
                        parentGroupName: parentGroupNameTrimmed,
                        name: name
                    },
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(csrfHeader, csrfToken);
                    },
                    success: function () {
                        tr.remove();
                    },
                    error: function () {
                        alert("error")
                    }
                });
            } else {
                $.ajax({
                    type: "POST",
                    url: "/nomenclature/changeParentGroup",
                    data: {
                        parentGroupName: parentGroupNameTrimmed,
                        name: name
                    },
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(csrfHeader, csrfToken);
                    },
                    success: function () {
                        tr.remove();
                    },
                    error: function () {
                        alert("error")
                    }
                });
            }
        }
    });
});

function saveParentGroup() {
    var vendorCode = $('#vendorCode').val();
    var name = $('#name').val();
    var code = $('#code').val();
    var backgroundColor = $('#backgroundColor').val();
    var fontColor = $('#fontColor').val();
    var parentGroup = {
        name: name,
        vendorCode: vendorCode,
        code: code,
        fontColor: fontColor,
        backgroundColor: backgroundColor
    };
    $.ajax({
        type: "POST",
        url: "/nomenclature/saveParentGroup",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(parentGroup),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            window.location.replace("/nomenclature");
        },
        error: function () {
            alert("error")
        }
    });
}

function editNomenclature() {
    var name = $('.item-active').find('td:eq(0)').text();
    $.ajax({
        type: "POST",
        url: "/nomenclature/getNomenclatureId",
        data: {name: name},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            // window.location.replace("/editNomenclature/"+data);
            window.location.href = "/editNomenclature/" + data;
        },
        error: function () {
            alert("error")
        }
    });
}

function deleteNomenclature() {
    var tr = $('.item-active').closest('tr');
    var name = tr.find('td:eq(0)').text();
    $.ajax({
        type: "POST",
        url: "nomenclature/delete",
        data: {name: name},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            tr.remove();
            window.location.replace("/nomenclature");
        },
        error: function () {
            alert("error")
        }
    });
}

function showColors() {
    var backgroundColor = $('#backgroundColor').val();
    var fontColor = $('#fontColor').val();
    $('.showColors').css('background-color', backgroundColor);
    $('.showColors').css('color', fontColor);
}
