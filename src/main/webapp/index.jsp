<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Textie</title>
    <meta name="author" content="Lukas Fülling - k40s.net"/>
    <meta name="Description" content="Demonstration for running Textie in a browser."/>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.mousewheel-min.js"></script>
    <script src="js/jquery.terminal-min.js"></script>
    <link href="css/jquery.terminal.css" rel="stylesheet"/>
    <link rel="icon" type="image/vnd.microsoft.icon" href="img/favicon.ico">
    <script>
        jQuery(document).ready(function ($) {
            $('body').terminal(function (command, term) {
                if (command !== '') {
                    $.ajax({
                        url: "webapi/textie",
                        type: "POST",
                        data: command,
                        contentType: "text/plain",
                        dataType: "text",
                        success: function (data) {
                            term.echo(data);
                        }
                    });

                } else {
                    term.echo("Bitte gib einen Befehl ein.")
                }
            }, {
                greetings: 'Textie now posesses your browser!\n\nDu kommst in einen dunklen Raum. Nach einiger Zeit gewöhnen sich deine Augen an die Dunkelheit.',
                name: 'textie',
                height: 800,
                width: 800,
                prompt: 'Was willst du tun? > '
            });
        });
    </script>
</head>
<body>
</body>
