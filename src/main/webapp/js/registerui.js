$(document).ready(function () {
    $("#wrongpass").hide();
    $("#useralready").hide();
    $("#loginform").fadeIn("slow").show();
});
$("#subbtn").click(function (event) {
    event.preventDefault();
    var pass = $("#password").val();
    var pass2 = $("#password2").val();
    if (pass == pass2) {
        var passenc = $.sha1(pass);
        $.ajax({
            url: "api/auth/register",
            type: "POST",
            data: {"email": $("#email").val(), "pass": passenc},
            contentType: "text/plain; charset=utf-8",
            dataType: "text",
            success: function (data) {
                if (data == "false") {
                    $("#useralready").fadeIn("slow").show();
                    console.warn("Server returned: false");
                } else if (data == "true") {
                    window.location.href = "index.jsp";
                } else {
                    $("#wrongpass").fadeIn("slow").show();
                }
            }
        })
    } else {

    }
});