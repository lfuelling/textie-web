$(document).ready(function () {
    var token = $.cookies.get("token");
    if (token == null) {
        window.location.href = "index.jsp";
    } else {
        $.ajax({
            url: "api/auth/restore",
            type: "POST",
            data: token,
            contentType: "text/plain; charset=utf-8",
            dataType: "json",
            statusCode: {
                404: function () {
                    console.log("404 error");
                    window.location.href = "index.jsp";
                }
            },
            success: function (data) {
                if (data == false) {
                    window.location.href = "index.jsp";
                } else if (data == true) {
                    $('#terminal').terminal(function (command, term) {
                        var obj = {
                            command: command,
                            token: token
                        }
                        var json = JSON.stringify(obj);
                        if (command !== '') {
                            $.ajax({
                                url: "api/textie/command",
                                type: "POST",
                                data: json,
                                contentType: "application/json",
                                dataType: "text",
                                success: function (data) {
                                    term.echo(data);
                                }
                            });

                        } else {
                            term.echo("Bitte gib einen Befehl ein.")
                        }
                    }, {
                        greetings: '\n\nDu kommst in einen dunklen Raum. Nach einiger Zeit gewöhnen sich deine Augen an die Dunkelheit.',
                        name: 'textie',
                        height: 600,
                        width: 800,
                        prompt: 'Was willst du tun? > '
                    });
                } else {
                    window.location.href = "index.jsp";
                }
            }
        });
    }
});
$( "#logoutLink" ).click(function() {
    $.cookies.del("token");
    $.ajax({
        url: "api/auth/logout",
        type: "GET",
        success: function (data) {
            term.echo(data);
        }
    });
    window.location.href = "index.jsp";
});