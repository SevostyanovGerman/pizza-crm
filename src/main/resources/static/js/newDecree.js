var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

function addSchedule(){
    var name = $("#scheduleName").val();
    var beginTime = $('#beginTime').val();
    var endTime = $('#endTime').val();
    var monday = $("#monday").prop('checked');
    var tuesday = $("#tuesday").prop('checked');
    var thursday = $("#thursday").prop('checked');
    var wednesday = $("#wednesday").prop('checked');
    var friday = $("#friday").prop('checked');
    var saturday = $("#saturday").prop('checked');
    var sunday = $('#sunday').prop('checked');
    var schedule = {
        name : name,
        beginTime : beginTime,
        endTime : endTime,
        monday : monday,
        tuesday : tuesday,
        thursday : thursday,
        wednesday : wednesday,
        friday : friday,
        saturday : saturday,
        sunday : sunday
    };
    $.ajax({
        type: "POST",
        url: "/newDecree/save",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(schedule),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (data) {
            window.location.replace("/newDecree");
        },
        error: function (e) {
            alert("error")
        }
    });
}