$(document).ready(function () {
    $("form").submit(function (e) {
        e.preventDefault();
        let dish = {
            id: undefined,
            name: undefined,
            categories: undefined
        };
        dish.id = $(".dish-id").val();
        dish.name = $(".dish-name").val();
        dish.categories = $(".dishCategories option").map(function () {
            return $(this).val();
        }).get();
        let postUrl = $("form").attr("action");
        console.log(postUrl);
        $.ajax({
            url: postUrl,
            type: "POST",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(dish),
            async: false,
            cache: false,
            processData: false,
            success: function () {
                window.location.replace("/admin/dish");
            }
        });
    });
    $(".dishCategories").change(function () {
        transferOption("dishCategories", "allDishCategories");
    });
    $(".allDishCategories").change(function () {
        transferOption("allDishCategories", "dishCategories")
    });
});

function transferOption(from, to) {
    let opt = $("." + from + " option:selected");
    $("." + to).append($('<option></option>')
        .attr("value", opt.val())
        .text(opt.text()));
    opt.remove();
}