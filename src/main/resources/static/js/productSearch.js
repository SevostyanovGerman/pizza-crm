$(document).ready(function () {
    $("#userInput").keyup(function () {
        let userInput = $(this).val().toUpperCase();
        $(".filter-item").each(function () {
            if ($(this).text().toUpperCase().indexOf(userInput) > -1) {
                $(this).show();
                $(".filter-div").show();
            } else {
                $(this).hide();
                $(".filter-div").hide();
            }
        });
    });
});

$(document).ready(function () {
    $("#clearUserInput").click(function () {
        $("#userInput").val("").keyup();
    });
});