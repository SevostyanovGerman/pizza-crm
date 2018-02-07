function dinar(data) {
    var markup = "<tr><td>" + data.name + "</td><td>" + data.color + "</td></tr>";
    $("#result").append(markup)
}

function getInfo(name) {
    $.ajax({
        url: "http://127.0.0.1:8080/admin/getinfo",
        data: ({name: name}),
        success: function asd (data) {
            dinar(data)

            return data;
        },
        error: function(data) {
            alert("Error" + data);
    }
    })
}