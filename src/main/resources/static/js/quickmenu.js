var activeMenu = 1;

let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

GetQuickMenu(1);
GetDish();

var options = {
    valueNames: ['name', 'category', 'code']
};
var listObj = new List('prouctList', options);

$(function () {
    $("#sortablePoduct, #menu-item-1, #menu-item-2, #menu-item-3").sortable({
        connectWith: ".connectedSortable",
        stop: function (event, ui) {
            console.log("Куда переместили: " + ui.item[0].parentElement.id.substr(-1));
            console.log("ID продукта: " + ui.item[0].children[3].innerHTML);
            console.log("Active menu: " + activeMenu);
            i = 0;
            flag = false;
            quickMenu[activeMenu - 1].dishQuickMenu.forEach(function (value) {
                if (value.id == ui.item[0].children[3].innerHTML) {
                    flag = true;
                    if (ui.item[0].parentElement.id.substr(-1) == "t") {
                        flag = true;
                        value.position = 0;
                        delete quickMenu[activeMenu - 1].dishQuickMenu[i];
                    } else {
                        value.position = ui.item[0].parentElement.id.substr(-1);
                    }
                }
                i++;
            });
            if (flag == false) {
                dish.forEach(function (value) {
                    if (value.id == ui.item[0].children[3].innerHTML) {
                        var temp = {};
                        temp.id = null;
                        temp.color = "red";
                        temp.position = parseInt(ui.item[0].parentElement.id.substr(-1));
                        temp.dish = [value];
                        quickMenu[activeMenu - 1].dishQuickMenu.unshift(temp);
                    }
                });
            }
        }
    });
});


$('#SearchInput').on('keyup', function () {
    var searchString = $(this).val();
    listObj.search(searchString);
});

function clearInputSearch() {
    searchString = '';
    $("#SearchInput").val(searchString);
    listObj.search(searchString);
}

function ClickToHeadMenu(idClickMenu) {
    activeMenu = idClickMenu;
    var id = "#head-item-" + idClickMenu;
    $("#head-item-1").css({"background-color": "#3e4040"});
    $("#head-item-2").css({"background-color": "#3e4040"});
    $("#head-item-3").css({"background-color": "#3e4040"});
    $(id).css({"background-color": "green"});
    console.log(activeMenu);
    $("#menu-item-1").empty();
    $("#menu-item-2").empty();
    $("#menu-item-3").empty();

    quickMenu[activeMenu - 1].dishQuickMenu.forEach(function (value) {
        if (value.position == 1) {
            $("#menu-item-1").append("<li class=\"menu-product-item\" style=\"background-color:" + value.color + "\"><p class=\"name\"> " + value.dish[0].name + "</p><p class=\"category\">" + value.dish[0].categories[0].name + "</p><p class=\"code\">Код - " + value.dish[0].code + "</p><p сlass=\"id\" style=\"display: none\">" + value.id + "</p></li>");
        }
        if (value.position == 2) {
            $("#menu-item-2").append("<li class=\"menu-product-item\" style=\"background-color:" + value.color + "\"><p class=\"name\"> " + value.dish[0].name + "</p><p class=\"category\">" + value.dish[0].categories[0].name + "</p><p class=\"code\">Код - " + value.dish[0].code + "</p><p сlass=\"id\" style=\"display: none\">" + value.id + "</p></li>");
        }
        if (value.position == 3) {
            $("#menu-item-3").append("<li class=\"menu-product-item\" style=\"background-color:" + value.color + "\"><p class=\"name\"> " + value.dish[0].name + "</p><p class=\"category\">" + value.dish[0].categories[0].name + "</p><p class=\"code\">Код - " + value.dish[0].code + "</p><p сlass=\"id\" style=\"display: none\">" + value.id + "</p></li>");
        }

    });

}

function GetDish() {
    $.ajax({
        type: "GET",
        url: "/get/dish/",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            dish = data;
        },
        error: function (e) {

        }
    });
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
            $(".head-item-menu-1").empty();
            $(".head-item-menu-2").empty();
            $(".head-item-menu-3").empty();
            quickMenu = data;
            quickMenu.forEach(function (value) {
                $(".head-item-menu-" + index).append("<p id=\"head-item-" + index + "\" onclick=\"ClickToHeadMenu(" + index + ")\">" + value.name + "</p>");
                index = index + 1;
            });

            $("#menu-item-1").empty();
            $("#menu-item-2").empty();
            $("#menu-item-3").empty();

            quickMenu[0].dishQuickMenu.forEach(function (value) {
                if (value.position == 1) {
                    $("#menu-item-1").append("<li class=\"menu-product-item\" style=\"background-color:" + value.color + "\"><p class=\"name\"> " + value.dish[0].name + "</p><p class=\"category\">" + value.dish[0].categories[0].name + "</p><p class=\"code\">Код - " + value.dish[0].code + "</p><p сlass=\"id\" style=\"display: none\">" + value.id + "</p></li>");
                }
                if (value.position == 2) {
                    $("#menu-item-2").append("<li class=\"menu-product-item\" style=\"background-color:" + value.color + "\"><p class=\"name\"> " + value.dish[0].name + "</p><p class=\"category\">" + value.dish[0].categories[0].name + "</p><p class=\"code\">Код - " + value.dish[0].code + "</p><p сlass=\"id\" style=\"display: none\">" + value.id + "</p></li>");
                }
                if (value.position == 3) {
                    $("#menu-item-3").append("<li class=\"menu-product-item\" style=\"background-color:" + value.color + "\"><p class=\"name\"> " + value.dish[0].name + "</p><p class=\"category\">" + value.dish[0].categories[0].name + "</p><p class=\"code\">Код - " + value.dish[0].code + "</p><p сlass=\"id\" style=\"display: none\">" + value.id + "</p></li>");
                }

            });

            $("#head-item-1").css({"background-color": "green"});
        },
        error: function (e) {
        }
    });
}

$("#exampleSelect1").change(function () {
    GetQuickMenu($("select#exampleSelect1").val());
});

function ShowChangeNameHeadMenu() {
    $("#nameHeadMenu").val(quickMenu[activeMenu - 1].name);
    $("#ChangeNameHeadMenu").modal('show');
}

function ChangeNameHeadMenu() {
    var newNameHeadMenu = $("#nameHeadMenu").val()
    $("#head-item-" + activeMenu).text(newNameHeadMenu);
    quickMenu[activeMenu - 1].name = newNameHeadMenu;
    $("#ChangeNameHeadMenu").modal('hide');
}

function Save() {
    console.log(JSON.stringify(quickMenu));
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/update/quickmenu/" + $("select#exampleSelect1").val(),
        data: JSON.stringify(quickMenu),
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
}
