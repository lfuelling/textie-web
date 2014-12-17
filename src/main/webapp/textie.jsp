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
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.cookies.min.js"></script>
    <script src="js/textieui.js"></script>
    <link href="css/jquery.terminal.css" rel="stylesheet"/>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/bootstrap-theme.min.css" rel="stylesheet"/>
    <link href="css/textie.css" rel="stylesheet"/>
    <link rel="icon" href="img/favicon.png">
</head>
<body>



<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <img class="navbar-brand" src="img/logo_white.png">
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Aktionen<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#" >Savegame Laden</a></li>
                        <li><a href="#" data-toggle="modal" data-target="#configModal">Konfiguration</a></li>
                        <li class="divider"></li>
                        <li><a href="#" id="logoutLink">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div style="height:70px;"></div>
<div class="container" id="terminal"></div>

<!-- configModal -->
<div class="modal fade" id="configModal" tabindex="-1" role="dialog" aria-labelledby="configModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="configModalLabel">Konfiguration &Auml;ndern</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-warning" id="configSVWarning" role="alert">
                    Achtung: Das &Auml;ndern der Konfiguration macht das Savegame möglicherweise unbrauchbar.
                </div>
                <div class="alert alert-danger" id="configAXError" style="display: none;" role="alert">
                    Fehler beim Speichern der Konfiguration.
                </div>
                <p>
                    Hier kannst du deine Konfiguration sehen. Sie steuert genau genommen dein ganzes Spiel, also kannst du dir damit dein eigenes Abenteuer schreiben.
                </p>
                <p>
                    Am besten funktioniert das ganze, indem du die Konfiguration in einen Editor kopierst und dort bearbeitest. Danach f&uuml;ge sie einfach hier ein und klick auf speichern.
                </p>
                <textarea id="configArea"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Abbrechen</button>
                <button type="button" class="btn btn-primary" id="saveConfigButton">Speichern</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#saveConfigButton").click(function(event) {
        event.preventDefault();
        var config = $("#configArea").val();
        var token = $.cookies.get("token");
        var obj = {
            config: config,
            token: token
        };
        var json = JSON.stringify(obj);
        $.ajax({
            url: "api/textie/saveconfig",
            type: "POST",
            data: json,
            contentType: "application/json",
            success: function(data){
                if(data == "true"){
                    $('#configModal').modal('hide')
                } else {
                    $('#configSVWarning').hide();
                    $('#configAXError').show();
                }

            }
        });

    });
</script>
<script type="text/javascript">
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
</script>
</body>
</html>
