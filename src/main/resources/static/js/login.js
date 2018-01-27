function pinbuttonClick(digit) {
    $("#pincode").val($("#pincode").val() + digit);
}

function clearInput() {
    $("#pincode").val('');
}

function removeLast() {
    $("#pincode").val($("#pincode").val().slice(0, -1));
}