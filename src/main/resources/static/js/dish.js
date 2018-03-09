$(document).ready(function () {
    let options = {
        valueNames: ['name']
    };

    new List('category', options);
    new List('categoryAll', options);

    $(function () {
        $("#dishCategoriesList, #allDishCategoriesList").sortable({
            connectWith: ".connectedSortable",
            update: function (event, ui) {
                new List('category', options);
                new List('categoryAll', options);
            }
        }).disableSelection();
    });
});

$(document).ready(function () {
    let options = {
        valueNames: ['name']
    };

    new List('ingredient', options);
    new List('ingredientAll', options);

    $(function () {
        $("#dishIngredientList, #allDishIngredientList").sortable({
            connectWith: ".connectedSortable",
            update: function (event, ui) {
                new List('ingredient', options);
                new List('ingredientAll', options);
            }
        }).disableSelection();
    });
});

$(document).ready(function () {
    let csrfToken = $("meta[name='_csrf']").attr("content");
    let csrfHeader = $("meta[name='_csrf_header']").attr("content");

    $(".btn").click(function () {
        let dish = {
            id: undefined,
            name: undefined,
            price: undefined,
            categories: [],
            ingredients: []
        };
        dish.id = $(".dish-id").val();
        dish.name = $(".dish-name").val();
        dish.price = $("#price").val();
        $(".dc-list > p").each(function () {
            dish.categories.push({
                "id": $(this).attr("value"),
                "name": $(this).text()
            });
        });
        $(".di-list > p").each(function () {
            dish.ingredients.push({
                "id": $(this).attr("value"),
                "name": $(this).text()
            });
        });
        console.log(JSON.stringify(dish));
        $.ajax({
            url: "/admin/dish/update",
            type: "POST",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(dish),
            async: false,
            cache: false,
            processData: false,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function () {
                window.location.replace("/admin/dish");
            }
        });
    });
});
