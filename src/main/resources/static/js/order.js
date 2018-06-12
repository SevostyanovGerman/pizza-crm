let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");
let colonToggler = false;


// Display time
function getLocaleTimeString() {
    return new Date().toLocaleTimeString('ru-RU', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit'
    });
}

function displayDateTime() {
    let dateTimeParts = getLocaleTimeString().split(',');
    let timeParts = dateTimeParts[1].split(':');
    $('.clock-date').text(dateTimeParts[0]);
    $('.clock-hours').text(timeParts[0]);
    $('.clock-minutes').text(timeParts[1]);
    $(".clock-colon").css({visibility: colonToggler ? 'visible' : 'hidden'});
    colonToggler = !colonToggler;
}

$(document).ready(function () {
    displayDateTime();
    setInterval(displayDateTime, 1000);
});

function setOrderTimestamp() {
    let dateTimeParts = getLocaleTimeString().split(',');
    $('#orderTime').html(dateTimeParts[1]);
}

//***********************************************************

// Category
$(document).ready(function () {
    $(".category-item").click(function () {
        $("#backward").removeClass("disable");
        $("#category").css({"display": "none"});
        var buttonName = $(this).text();
        $.ajax({
            type: "POST",
            url: "/nomenclature/getNomenclatureParentGroup",
            data: "name=" + $(this).text(),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {
                $("#dish").empty().css({"display": "block"});
                if (data.length > 23) {
                    for (var i = 0; i < 23; i++) {
                        $("#dish").append($([
                            "<a href='#' class='order-item middle-panel-white'",
                            "data-item-name='" + data[i].name + "'",
                            "data-quantity='1'",
                            "data-price=" + data[i].price,
                            ">",
                            "<p>" + data[i].name + "</p>",
                            "<p>" + data[i].price + "</p>",
                            "</a>"
                        ].join("\n")));
                    }
                    $("#dish").append($([
                        '<a href="#" class="middle-panel-white" onclick="showMoreDishes(\'' + buttonName + '\')">' +
                        '<p><i class=\'fa fa-angle-down\' aria-hidden=\'true\'></i></p>' +
                        '</a>'
                    ].join("\n")));

                } else {
                    $.each(data, function (key, value) {
                        $("#dish").append($([
                            "<a href='#' class='order-item middle-panel-white'",
                            "data-item-name=\"" + value.name + "\"",
                            "data-quantity='1'",
                            "data-price=" + value.price,
                            ">",
                            "<p>" + value.name + "</p>",
                            "<p>" + value.price + "</p>",
                            "</a>"
                        ].join("\n")));
                    });
                }
            },
            error: function (e) {
            }
        });
    });
});

$(document).ready(function () {
    $("#backward").click(function () {
        $(this).addClass("disable");
        $("#category").css({"display": "block"});
        $("#dish").css({"display": "none"});
    })
});
//***********************************************************

// Discount
// getAllDiscountsForOrder
$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "/discount/getAllDiscountsForOrder",
        contentType: "application/json; charset=utf-8",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            allDiscounts = data;
            for (var i = 0; i < data.length; i++) {

                if (data[i].automatic) {
                    $('.discount-select-tbody').append(
                        '<tr class="select-discount" aria-disabled="true">' +
                        '<td>' + data[i].name + '</td>' +
                        '<td>' + data[i].value + '</td>' +
                        '<td> % </td>' +
                        '<input type="hidden" value="' + data[i].automatic + '">' +
                        '</tr>')
                    $('.discount-select-tbody').find(".select-discount").eq(i).css("background", "#dee284");
                } else {
                    $('.discount-select-tbody').append(
                        '<tr class="select-discount" onclick="changeColor(this)">' +
                        '<td>' + data[i].name + '</td>' +
                        '<td>' + data[i].value + '</td>' +
                        '<td> % </td>' +
                        '<input type="hidden" value="' + data[i].automatic + '">' +
                        '</tr>')
                }

            }
        },
        error: function () {
        }
    });
});

// Change color onclick
function changeColor(td) {
    if ($(td).css('background') === "rgb(222, 226, 132) none repeat scroll 0% 0% / auto padding-box border-box") {
        $(td).css("background", "#FFFFFF");
    } else {
        $(td).css("background", "#dee284");
    }
}

//Discounts application
var discountsAndExtraCharges = [];

function applicateDiscounts() {
    discountsAndExtraCharges = [];
    $(".select-discount").each(function () {
        if ($(this).css('background') === "rgb(222, 226, 132) none repeat scroll 0% 0% / auto padding-box border-box") {

            var name = $(this).closest('tr').find('td').eq(0).text();
            var discountAndExtraCharge = {name: name};
            discountsAndExtraCharges.push(discountAndExtraCharge);
        }
    });

    updateTotal();
}

//***********************************************************

$(document).ready(function () {
    $('.order-table').on('click', 'tr', function () {
        $(this).addClass('highlight').siblings().removeClass('highlight');
    });
});

// Quantity
$(document).ready(function () {
    $('.add-quantity').click(function () {
        let tr = getSelectedRow();
        let quantity = parseFloat(tr.find('td:eq(0)').text());
        quantity++;
        tr.find('td:eq(0)').text(quantity);
        updateTotal();
    });
});

$(document).ready(function () {
    $('.subtract-quantity').click(function () {
        let tr = getSelectedRow();
        let quantity = parseFloat(tr.find('td:eq(0)').text());
        if (--quantity <= 0) {
           // tr.remove();
            updateTotal();
            return;
        }
        tr.find('td:eq(0)').text(quantity);
        updateTotal();
    });
});
//***********************************************************

//Dish
$(document).ready(function () {
    $('.remove-selected-dish').click(function () {
        let tr = getSelectedRow();
        tr.prev().addClass('highlight').siblings().removeClass('highlight');
        updateTotal();
        tr.remove();
        if ($('.order-table tr').length === 0) {
            $('#orderTime').html('');
        }
    });
});

$(document).ready(function () {
    $('#dish').on('click', '.order-item', function (e) {
        e.preventDefault();
        var quantity = $(this).data('quantity');
        var itemName = $(this).data('itemName');
        var price = $(this).data('price');
        var tableRow = $('.order-table tr').length;
        if (tableRow == 0) {
            setOrderTimestamp();
            $('.order-table').append($([
                "<tr>",
                "<td>" + $(this).data('quantity') + "</td>",
                "<td>" + $(this).data('itemName') + "</td>",
                "<td>" + $(this).data('price') + "</td>",
            ].join("/n")));
            applicateDiscounts();
            updateTotal();
            return;
        } else {
            var go = true;
            $('.order-table tr').each(function (i) {
                if (itemName == $(this).find('td:eq(1)').text()) {
                    var quantity2 = $(this).find('td:eq(0)').text();
                    quantity2++;
                    go = false;
                    $(this).find('td:eq(0)').text(quantity2);
                }
            });
            if (go) {
                $('.order-table').append($([
                    "<tr>",
                    "<td>" + quantity + "</td>",
                    "<td>" + itemName + "</td>",
                    "<td>" + price + "</td>",
                ].join("/n")));
            }

        }
        updateTotal();
    });
});
//***********************************************************

// Product
$(document).ready(function () {
    $('#productsItem').on('click', '.product-search', function (e) {

        var quantity = $(this).data('quantity');
        var itemName = $(this).children()[0].innerHTML;
        var price = $(this).children()[1].innerHTML.slice(0, -1);
        var tableRow = $('.order-table tr').length;
        if (tableRow == 0) {
            setOrderTimestamp();
            $('.order-table').append($([
                "<tr>",
                "<td>" + $(this).data('quantity') + "</td>",
                "<td>" + $(this).children()[0].innerHTML + "</td>",
                "<td>" + $(this).children()[1].innerHTML.slice(0, -1) + "</td>",
            ].join("/n")));
            return;
        } else {
            var go = true;
            $('.order-table tr').each(function (i) {
                if (itemName == $(this).find('td:eq(1)').text()) {
                    var quantity2 = $(this).find('td:eq(0)').text();
                    quantity2++;
                    go = false;
                    $(this).find('td:eq(0)').text(quantity2);
                }
            });
            if (go) {
                $('.order-table').append($([
                    "<tr>",
                    "<td>" + quantity + "</td>",
                    "<td>" + itemName + "</td>",
                    "<td>" + price + "</td>",
                ].join("/n")));
            }

        }
        updateTotal();
    });
});

$(document).ready(function () {
    $('#product').on('click', '.productItem', function (e) {

        var quantity = $(this).data('quantity');
        var itemName = $(this).children()[0].innerHTML;
        var price = $(this).children()[1].innerHTML.slice(0, -1);
        var tableRow = $('.order-table tr').length;
        if (tableRow == 0) {
            setOrderTimestamp();
            $('.order-table').append($([
                "<tr>",
                "<td>" + $(this).data('quantity') + "</td>",
                "<td>" + $(this).children()[0].innerHTML + "</td>",
                "<td>" + $(this).children()[1].innerHTML.slice(0, -1) + "</td>",
            ].join("/n")));
            return;
        } else {
            var go = true;
            $('.order-table tr').each(function (i) {
                if (itemName == $(this).find('td:eq(1)').text()) {
                    var quantity2 = $(this).find('td:eq(0)').text();
                    quantity2++;
                    go = false;
                    $(this).find('td:eq(0)').text(quantity2);
                }
            });
            if (go) {
                $('.order-table').append($([
                    "<tr>",
                    "<td>" + quantity + "</td>",
                    "<td>" + itemName + "</td>",
                    "<td>" + price + "</td>",
                ].join("/n")));
            }

        }
        updateTotal();
    });
});

//***********************************************************

function getSelectedRow() {
    let tr = $('.order-table tr.highlight');
    if (tr.length === 0) {
        tr = $('.order-table tr:last');
        tr.addClass('highlight').siblings().removeClass('highlight');
    }
    return tr;
}

function updateTotal() {
    let rawTotal = 0;
    let total = 0;

    var dishes = [];

    $('.order-table tr').each(function () {
        let amount = parseInt($(this).find('td:eq(0)').text());
        if (isNaN(amount)) {
            amount = 0;
        }
        let name = $(this).find('td:eq(1)').text();

        var dish = {amount: amount, name: name};
        dishes.push(dish);
    });

    var id = $("#new-order-id").text();

    var order = {
        id: id,
        dishes: dishes
    };

    $.ajax({
        type: "POST",
        url: "/discount/getRowTotal",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(order),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            $('#rawTotal').html(data[0]);
            $('#total').html(data[1]);
        },
        error: function () {
        }
    });
}

// Quantity manual input
$(document).ready(function () {
    $('.quantity-control-modal-show').click(function () {
        let tr = getSelectedRow();
        let selectedQty = tr.find('td:eq(0)').text();
        if (selectedQty) {
            $('.input-quantity').val(parseFloat(selectedQty));
            $('.dish-name').text(tr.find('td:eq(1)').text());
            $('.quantity-control-modal').modal('show');
        }
    });
});

$(document).ready(function () {
    $('.quantity-control-modal .btn-num').click(function () {
        let qty = $('.input-quantity');
        let qtyVal = parseFloat(qty.val());
        if (isNaN(qtyVal) || qtyVal === 0) {
            qty.val($(this).val());
        } else {
            qty.val(qty.val() + $(this).val());
        }
    });
});

$(document).ready(function () {
    $('.quantity-control-modal .btn-delimiter').click(function () {
        let qty = $('.input-quantity');
        if (qty.val().includes('.')) {
            return;
        }
        qty.val(qty.val() + $(this).val());
    });
});

$(document).ready(function () {
    $('.quantity-control-modal .btn-clear').click(function () {
        $('.input-quantity').val('0');
    });
});

$(document).ready(function () {
    $('.quantity-control-modal .btn-plus').click(function () {
        let qty = $('.input-quantity');
        let qtyVal = parseFloat(qty.val());
        if (isNaN(qtyVal)) {
            return;
        }
        let qtyPlus = parseFloat($(this).val());
        qty.val(qtyVal + qtyPlus);

    });
});

$(document).ready(function () {
    $('.btn-quantity-save').click(function () {
        let tr = getSelectedRow();
        let savedQty = $('.input-quantity').val();
        tr.find('td:eq(0)').val(savedQty).text(savedQty);
        updateTotal();
        $('.quantity-control-modal').modal('hide');
    });
});

//***********************************************************

function showMoreDishes(name) {
    $.ajax({
        type: "POST",
        url: "/nomenclature/getNomenclatureParentGroup",
        data: "name=" + name,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            $("#dish").empty().css({"display": "block"});
            for (var i = 23; i < data.length; i++) {
                $("#dish").append($([
                    "<a href='#' class='order-item middle-panel-white'",
                    "data-item-name='" + data[i].name + "'",
                    "data-quantity='1'",
                    "data-price=" + data[i].price,
                    ">",
                    "<p>" + data[i].name + "</p>",
                    "<p>" + data[i].price + "</p>",
                    "</a>"
                ].join("\n")));
            }
        },
        error: function (e) {
        }
    });
}

// Order-cashbox
$(document).ready(function () {
    $('.open-cashbox').click(function () {
        let orderItems = [];
        $('.order-table tr').each(function () {
            orderItems.push({
                quantity: $(this).find('td:eq(0)').text(),
                dishName: $(this).find('td:eq(1)').text(),
                price: $(this).find('td:eq(2)').text()
            });
        });
        let orderList = {
            orderItems: orderItems,
            total: $('#total').text(),
            rawTotal: $('#rawTotal').text(),
            discount: $('#discount').text(),
            extraCharge: $('#extraCharge').text()
        };
        sessionStorage.setItem('order-list', JSON.stringify(orderList));
    });
});
//***********************************************************

var arrDiscount = [];
var quickMenu;
var searchString = '';
var options = {
    valueNames: ['nameProduct']
};
var listObj = new List('product1', options);
var option2 = {
    valueNames: ['code', 'barcode', 'vendorcode']
};
var listObj2 = new List('products1', option2);
var searchAll = false;
var searchDarCode = false;
var searchCode = false;
var searchVendorCode = false;
date = new Date;
var day = date.getDay();
if (day == 0) {
    GetQuickMenu(7);
} else {
    GetQuickMenu(day);
}


function SearchProduct(value) {
    searchString = searchString + value;
    $("#SearchModalInput").val(searchString);
    listObj2.search(searchString);
}

function SearchProductDel() {
    searchString = '';
    $("#SearchModalInput").val(searchString);
    listObj2.search(searchString);
}

function SearchProductDelOne() {
    searchString = searchString.slice(0, -1);
    $("#SearchModalInput").val(searchString);
    listObj2.search(searchString);
}

function SearchModalЕxpandShow() {
    $("#SearchModal").modal('hide');
    $("#SearchModalЕxpand").modal('show');

    $.ajax({
        type: "POST",
        url: "/get/dish",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {

            var dish = data;
            console.log(dish);
            if($("#SearchModalЕxpand").hasClass("flagSearch")){
                dish.forEach(function (value) {
                    $("#product").append("<div class=\"col-3\"><div class=\"productItem\" data-quantity=\"1\"><p class=\"nameProduct\">" + value.name + "</p><p class=\"costProduct\">" + value.price + "p</p></div></div>");
                });
            }

            $("#SearchModalЕxpand").removeClass("flagSearch");

            listObj = new List('product1', options);

        },
        error: function (e) {
        }
    });
}


function SearchModalShow() {
    $("#SearchModalЕxpand").modal('hide');
    $("#SearchModal").modal('show');

    $.ajax({
        type: "POST",
        url: "/get/dish",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            var dish = data;
            console.log(dish);
                dish.forEach(function (value) {
                    $("#productsItem").append("<li class=\"product-search\" data-quantity=\"1\"><p class=\"name\">" + value.name + "</p><p class=\"price\">" + value.price + "р</p><p class=\"code\">Код: " + value.code + "</p><p class=\"barcode\" style=\"display: none\">" + value.barcode + "</p><p class=\"vendorcode\" style=\"display: none\">" + value.vendorCode + "</p></li>");
                });
           listObj2 = new List('products1', option2);

        },
        error: function (e) {
        }
    });
    SearchAll();
}

$(document).ready(function () {
    $('#search-field').on('keyup', function () {
        var searchString = $(this).val();
        listObj.search(searchString);
    });
});

function SearchModalЕxpandHide() {
    $("#SearchModal").modal('hide');
    $("#SearchModalЕxpand").modal('hide');
}

function SearchAll() {
    searchAll = !searchAll;
    if (searchAll) {
        $("#SearchCode").css({"background-color": "#ffff00"});
        $("#SearchDarCode").css({"background-color": "#ffff00"});
        $("#SearchVendorCode").css({"background-color": "#ffff00"});
        option2 = {
            valueNames: ['code', 'barcode', 'vendorcode']
        };
        listObj2 = new List('products1', option2);
        searchDarCode = true;
        searchCode = true;
        searchVendorCode = true;
    } else {
        $("#SearchCode").css({"background-color": "#fff"});
        $("#SearchDarCode").css({"background-color": "#fff"});
        $("#SearchVendorCode").css({"background-color": "#fff"});
        option2 = {
            valueNames: []
        };
        listObj2 = new List('products1', option2);
        searchDarCode = false;
        searchCode = false;
        searchVendorCode = false;
    }
}

function SearchDarCode() {
    searchDarCode = !searchDarCode;
    if (searchDarCode) {
        $("#SearchCode").css({"background-color": "#fff"});
        $("#SearchDarCode").css({"background-color": "#ffff00"});
        $("#SearchVendorCode").css({"background-color": "#fff"});
        option2 = {
            valueNames: ['barcode']
        };
        listObj2 = new List('products1', option2);
        searchCode = false;
        searchVendorCode = false;
    } else {
        $("#SearchCode").css({"background-color": "#fff"});
        $("#SearchDarCode").css({"background-color": "#fff"});
        $("#SearchVendorCode").css({"background-color": "#fff"});
        option2 = {
            valueNames: []
        };
        listObj2 = new List('products1', option2);
    }
}

function SearchCode() {
    searchCode = !searchCode;
    if (searchCode) {
        $("#SearchCode").css({"background-color": "#ffff00"});
        $("#SearchDarCode").css({"background-color": "#fff"});
        $("#SearchVendorCode").css({"background-color": "#fff"});
        option2 = {
            valueNames: ['code']
        };
        listObj2 = new List('products1', option2);
        searchDarCode = false;
        searchVendorCode = false;
    } else {
        $("#SearchCode").css({"background-color": "#fff"});
        $("#SearchDarCode").css({"background-color": "#fff"});
        $("#SearchVendorCode").css({"background-color": "#fff"});
        option2 = {
            valueNames: []
        };
        listObj2 = new List('products1', option2);
    }
}

function SearchVendorCode() {
    searchVendorCode = !searchVendorCode;
    if (searchVendorCode) {
        $("#SearchCode").css({"background-color": "#fff"});
        $("#SearchDarCode").css({"background-color": "#fff"});
        $("#SearchVendorCode").css({"background-color": "#ffff00"});
        option2 = {
            valueNames: ['vendorcode']
        };
        listObj2 = new List('products1', option2);
        searchDarCode = false;
        searchCode = false;
    } else {
        $("#SearchCode").css({"background-color": "#fff"});
        $("#SearchDarCode").css({"background-color": "#fff"});
        $("#SearchVendorCode").css({"background-color": "#fff"});
        option2 = {
            valueNames: []
        };
        listObj2 = new List('products1', option2);
    }
}

function GetQuickMenu(day) {
    var index = 1;
    $.ajax({
        type: "GET",
        url: "/get/quickmenu/" + day,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            console.log(data);
            $("#head-quick-menu").empty();
            quickMenu = data;
            quickMenu.forEach(function (value) {
                $("#head-quick-menu").append("<li><a href=\"#\" onclick=\'ChangeQuickMenu(" + index + ")\' id=\"headerQuickMenu-" + index + "\">" + value.name + "</a></li>");
                index = index + 1;
            });
            $(".right-panel-body-1").empty();
            $(".right-panel-body-2").empty();
            $(".right-panel-body-3").empty();
            quickMenu[0].dishQuickMenu.forEach(function (value) {
                console.log(value);
                if (value.position == 1) {
                    $(".right-panel-body-1").append("<a class=\"middle-panel-yellow\" style=\"background-color: " + value.color + " \" href=\"#\">" + value.dish[0].name + "</a>");
                }
                if (value.position == 2) {
                    $(".right-panel-body-2").append("<a class=\"middle-panel-yellow\" style=\"background-color: " + value.color + " \" href=\"#\">" + value.dish[0].name + "</a>");
                }
                if (value.position == 3) {
                    $(".right-panel-body-3").append("<a class=\"middle-panel-yellow\" style=\"background-color: " + value.color + " \" href=\"#\">" + value.dish[0].name + "</a>");
                }
            });
            headerQuickMenu(1);
        },
        error: function (e) {
        }
    });
}

function ChangeQuickMenu(numMenu) {
    headerQuickMenu(numMenu)
    $(".right-panel-body-1").empty();
    $(".right-panel-body-2").empty();
    $(".right-panel-body-3").empty();
    quickMenu[numMenu - 1].dishQuickMenu.forEach(function (value) {
        if (value.position == 1) {
            $(".right-panel-body-1").append("<a class=\"middle-panel-yellow\" style=\"background-color: " + value.color + " \" href=\"#\">" + value.dish[0].name + "</a>");
        }
        if (value.position == 2) {
            $(".right-panel-body-2").append("<a class=\"middle-panel-yellow\" style=\"background-color: " + value.color + " \" href=\"#\">" + value.dish[0].name + "</a>");
        }
        if (value.position == 3) {
            $(".right-panel-body-3").append("<a class=\"middle-panel-yellow\" style=\"background-color: " + value.color + " \" href=\"#\">" + value.dish[0].name + "</a>");
        }
    });
}

function headerQuickMenu(num) {
    var id = "#headerQuickMenu-" + num;
    $("#headerQuickMenu-1").css({"background-color": "#3e4040", "color": "#fff"});
    $("#headerQuickMenu-2").css({"background-color": "#3e4040", "color": "#fff"});
    $("#headerQuickMenu-3").css({"background-color": "#3e4040", "color": "#fff"});
    $(id).css({"background-color": "#dfe487", "color": "#3e4040"});
}

function discountModalShow() {
    var price = $("#rawTotal").text();
    $("#discount-extraCharge-modal").modal('show');
    console.log(price);
    $("#rawTotalModal").text(price);
    $("#rawTotalDiscountModal").text(price);
    $.ajax({
        type: "GET",
        url: "/get/discount/" + price,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            console.log(data);
            $("#discount-extraCharge-modal-item").empty();
            var discountList = data;
            discountList.forEach(function (value) {
                var classDis = "disableDiscount";
                if (arrDiscount.indexOf(value.id) != -1) {
                    classDis = "enableDiscount";
                    AddDiscountActive(value.value, value.discountCalculationMode);
                }
                var price = $("#rawTotal").text();
                var newPrise = CountDiscount(value.value, value.discountCalculationMode, value.id);
                console.log(value);
                $("#discount-extraCharge-modal-item").append(
                    "<div class=\"col-4\" style=\"padding-left: 5px; padding-right: 5px; cursor: pointer;\" onclick=\"AddDiscount(" + value.value + ", \'" + value.discountCalculationMode + "\'" + "," + value.id + ")\">\n" +
                    "<div class=\"DiscountItem " + classDis + " discount_id_" + value.id + "\">\n" +
                    "<p class=\"NameDiscount\">" + value.name + "</p>\n" +
                    "<p class=\"TypeDiscount\">" + value.type + "</p>\n" +
                    "<p class=\"ValueDiscount\">" + value.value + " %" + "</p>\n" +
                    "<p class=\"ValueDiscountCount\">" + "- " + newPrise + " p" + "</p>\n" +
                    "</div>\n" +
                    "</div>");
            });
        },
        error: function (e) {
        }
    });
}

var globalDiscount = 0;

function AddDiscount(discount, type, idDis) {
    var id = ".discount_id_" + idDis;
    var price = $("#rawTotal").text();
    var newPrise;
    if ($(id).attr("class").indexOf("disableDiscount") + 1) {
        arrDiscount.push(idDis);
        $(id).removeClass("disableDiscount");
        $(id).addClass("enableDiscount");
        if (type == "PERCENT") {
            newPrise = price * (100 - discount) / 100;
        } else {
            newPrise = price - discount;
        }
        $("#rawTotalDiscountModal").text(newPrise);
        globalDiscount = discount;
    } else {
        globalDiscount = 0;
        arrDiscount.pop(idDis);
        $(id).removeClass("enableDiscount");
        $(id).addClass("disableDiscount");
        $("#rawTotalDiscountModal").text(price);
    }
}

function AddDiscountActive(discount, type) {
    var price = $("#rawTotal").text();
    if (type == "PERCENT") {
        newPrise = price * (100 - discount) / 100;
    } else {
        newPrise = price - discount;
    }
    $("#rawTotalDiscountModal").text(newPrise);
}

function CountDiscount(discount, type, idDis) {
    var price = $("#rawTotal").text();
    var newPrise;
    if (type == "PERCENT") {
        newPrise = price * (discount) / 100;
    } else {
        newPrise = discount;
    }
    return newPrise;
}

function CloseModal() {
    $("#discount-extraCharge-modal").modal('hide');
    globalDiscount = 0;
    $("#discount").text(globalDiscount);
    $("#total").text($("#rawTotal").text());
}

function SaveModal() {
    $("#discount-extraCharge-modal").modal('hide');
    $("#total").text($("#rawTotalDiscountModal").text());
    $("#discount").text(globalDiscount);
}
