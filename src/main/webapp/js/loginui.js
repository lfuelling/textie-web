$(document).ready(function () {
    var token = $.cookies.get("token");
    if (token == null) {
        $("#wrongpass").hide();
        $("#security").hide();
        $("#loginform").fadeIn("slow").show();
    } else {
        $.ajax({
            url: "api/auth/restore",
            type: "POST",
            data: token,
            contentType: "text/plain; charset=utf-8",
            dataType: "json",
            statusCode: {
                404: function() {
                    console.log("404 error");
                    $("#wrongpass").hide();
                    $("#loginform").fadeIn("slow").show();
                }
            },
            success: function (data) {
                if (data == false) {
                    $("#wrongpass").hide();
                    $("#loginform").fadeIn("slow").show();
                } else if (data == true) {
                    window.location.href = "textie.jsp";
                } else {
                    console.log("no connection to api.");
                }
            }
        })
    }
});

$("#subbtn").click(function (event) {
    event.preventDefault();
    var pass = $("#password").val();
    var passenc = $.sha1(pass);
    $.ajax({
        url: "api/auth/login",
        type: "POST",
        data: {"email": $("#email").val(), "pass": passenc},
        contentType: "text/plain; charset=utf-8",
        dataType: "text",
        success: function (data) {
            if (data == "false") {
                $("#wrongpass").fadeIn("slow").show();
                console.warn("Server returned: false");
            } else if (data != null) {
                $.cookies.set("token", data);
                window.location.href = "textie.jsp";
            } else {
                $("#wrongpass").fadeIn("slow").show();
            }
        }
    })
});