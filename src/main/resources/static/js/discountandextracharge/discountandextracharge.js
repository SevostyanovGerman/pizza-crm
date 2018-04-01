var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function () {
    $('.discountMain tr').click(function () {
        $('tr').removeClass();
        $(this).addClass('item-active');
    });
});

function deleteDiscount() {
    var name = $('.discountMain tr.item-active').find('td:eq(0)').text();
    if (name === "") {
        alert("Выберите элемент для удаления")
    } else {
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/delete",
            data: "name=" + name,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/discountandextracharge");
            },
            error: function () {
                alert()
            }
        });
    }
}

function updateDiscount() {
    var name = $('.discountMain tr.item-active').find('td:eq(0)').text();
    if (name === "") {
        alert("Выберите элемент для изменения")
    } else {
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/update",
            data: "name=" + name,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (id) {
                localStorage.setItem("id", id);
                window.location.replace("/discountandextracharge/update/step1/");
            },
            error: function () {
                alert("error")
            }
        });
    }
}

$(document).ready(function () {
    $('.activated').click(function () {
        var tr = $(this).closest('tr');
        var name = tr.find('td:eq(0)').text();
        var status = tr.find('input[type=checkbox]').prop('checked');
        var changeStatus = {
            name: name,
            status: status
        };
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/changeActiveStatus",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(changeStatus),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/discountandextracharge");
            },
            error: function () {
                alert("error")
            }
        });
    })
});

$(document).ready(function () {
    $('.paymentByCard').click(function () {
        var tr = $(this).closest('tr');
        var name = tr.find('td:eq(0)').text();
        var status = tr.find('input.paymentByCard').prop('checked');
        var paymentType = $(this).attr('class');
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/changePayment",
            data: {name: name, status: status, paymentType: paymentType},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/discountandextracharge");
            },
            error: function () {
                alert("error")
            }
        });
    })
});

$(document).ready(function () {
    $('.diners').click(function () {
        var tr = $(this).closest('tr');
        var name = tr.find('td:eq(0)').text();
        var status = tr.find('input.diners').prop('checked');
        var paymentType = $(this).attr('class');
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/changePayment",
            data: {name: name, status: status, paymentType: paymentType},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/discountandextracharge");
            },
            error: function () {
                alert("error")
            }
        });
    })
});

$(document).ready(function () {
    $('.masterCardElectronics').click(function () {
        var tr = $(this).closest('tr');
        var name = tr.find('td:eq(0)').text();
        var status = tr.find('input.masterCardElectronics').prop('checked');
        var paymentType = $(this).attr('class');
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/changePayment",
            data: {name: name, status: status, paymentType: paymentType},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/discountandextracharge");
            },
            error: function () {
                alert("error")
            }
        });
    })
});

$(document).ready(function () {
    $('.visa').click(function () {
        var tr = $(this).closest('tr');
        var name = tr.find('td:eq(0)').text();
        var status = tr.find('input.visa').prop('checked');
        var paymentType = $(this).attr('class');
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/changePayment",
            data: {name: name, status: status, paymentType: paymentType},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/discountandextracharge");
            },
            error: function () {
                alert("error")
            }
        });
    })
});

$(document).ready(function () {
    $('.masterCard').click(function () {
        var tr = $(this).closest('tr');
        var name = tr.find('td:eq(0)').text();
        var status = tr.find('input.masterCard').prop('checked');
        var paymentType = $(this).attr('class');
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/changePayment",
            data: {name: name, status: status, paymentType: paymentType},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/discountandextracharge");
            },
            error: function () {
                alert("error")
            }
        });
    });
});

$(document).ready(function () {
    $('.visaElectron').click(function () {
        var tr = $(this).closest('tr');
        var name = tr.find('td:eq(0)').text();
        var status = tr.find('input.visaElectron').prop('checked');
        var paymentType = $(this).attr('class');
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/changePayment",
            data: {name: name, status: status, paymentType: paymentType},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/discountandextracharge");
            },
            error: function () {
                alert("error")
            }
        });
    });
});

$(document).ready(function () {
    $('.maestro').click(function () {
        var tr = $(this).closest('tr');
        var name = tr.find('td:eq(0)').text();
        var status = tr.find('input.maestro').prop('checked');
        var paymentType = $(this).attr('class');
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/changePayment",
            data: {name: name, status: status, paymentType: paymentType},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/discountandextracharge");
            },
            error: function () {
                alert("error")
            }
        });
    });
});

$(document).ready(function () {
    $('.cash').click(function () {
        var tr = $(this).closest('tr');
        var name = tr.find('td:eq(0)').text();
        var status = tr.find('input.cash').prop('checked');
        var paymentType = $(this).attr('class');
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/changePayment",
            data: {name: name, status: status, paymentType: paymentType},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/discountandextracharge");
            },
            error: function () {
                alert("error")
            }
        });
    });
});

$(document).ready(function () {
    $('.onlinePayment').click(function () {
        var tr = $(this).closest('tr');
        var name = tr.find('td:eq(0)').text();
        var status = tr.find('input.onlinePayment').prop('checked');
        var paymentType = $(this).attr('class');
        $.ajax({
            type: "POST",
            url: "/discountandextracharge/changePayment",
            data: {name: name, status: status, paymentType: paymentType},
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/discountandextracharge");
            },
            error: function () {
                alert("error")
            }
        });
    });
});