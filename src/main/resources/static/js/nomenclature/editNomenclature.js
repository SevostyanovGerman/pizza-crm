var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");


function save() {
    var name = $('#name').val();
    var nomenclatureType = $('#nomenclatureType').val();
    var accountingCategory = $('#accountingCategory').val();
    var parentGroupName = $('#parentGroup option:selected').text();
    var parentGroupId = $('#parentGroup option:selected').val();
    var cookingTimeNorm = $('#cookingTimeNorm').val();
    var cookingTimePeak = $('#cookingTimePeak').val();
    var cookingPlace = $('#cookingPlace').val();
    var parentGroups = [];
    var nomenclatureParentGroupSet = {
        id: parentGroupId,
        name: parentGroupName
    };
    parentGroups.push(nomenclatureParentGroupSet);

    var nomenclature = {
        name: name,
        nomenclatureType: nomenclatureType,
        accountingCategory: accountingCategory,
        parentGroup: parentGroup,
        cookingTimeNorm: cookingTimeNorm,
        cookingTimePeak: cookingTimePeak,
        cookingPlace: cookingPlace,
        nomenclatureParentGroupSet: parentGroups
    };

    $.ajax({
        type: "POST",
        url: "/nomenclature/save",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(nomenclature),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            // window.location.replace("/discountandextracharge");
        },
        error: function () {
            alert("error")
        }
    });
}