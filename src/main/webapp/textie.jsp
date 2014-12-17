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

<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-44457715-6', 'auto');
    ga('send', 'pageview');

</script>

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
                        <li><a href="#" id="saveGame">Speichern</a></li>
                        <li><a href="#" id="loadGame">Laden</a></li>
                        <li class="divider"></li>
                        <li><a href="#" id="reloadConfig">Konfiguration Neu Laden</a></li>
                        <li><a href="#" data-toggle="modal" data-target="#configModal">Konfiguration &Auml;ndern</a></li>
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
<div id="footer">
    <div class="container">
        <p class="muted credit">Gemacht von <a href="http://k40s.net">@</a><a href="http://github.com/lfuelling">lfuelling</a> und <a href="http://github.com/YuleZ">@YuleZ</a>.</p>
    </div>
</div>
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
                    Achtung: Das &Auml;ndern der Konfiguration macht das Savegame m&ouml;glicherweise unbrauchbar.
                </div>
                <div class="alert alert-danger" id="configAXError" style="display: none;" role="alert">
                    Fehler beim Speichern der Konfiguration.
                </div>
                <p>
                    Hier kannst du deine Konfiguration sehen. Sie definiert genau genommen dein ganzes Spiel, also kannst du dir damit dein eigenes Abenteuer schreiben.
                </p>
                <p>
                    Am besten funktioniert das ganze, indem du die Konfiguration in einen Editor kopierst und dort bearbeitest. Danach f&uuml;ge sie einfach hier ein und klick auf speichern.
                </p>
                <textarea id="configArea"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Abbrechen</button>
                <button type="button" class="btn btn-default" id="restoreDefaultConfigButton">Standard Wiederherstellen</button>
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
    $("#restoreDefaultConfigButton").click(function(){
        $.ajax({
            url: "api/textie/getdefaultconfig",
            type: "GET",
            success: function (data) {
                $("#configArea").val(data);
            }
        });
    });
    $("#loadGame").click(function(){
        $.ajax({
            url: "api/textie/save",
            type: "GET",
            success: function (data) {
                term.echo(data);
            }
        });
    });
    $("#saveGame").click(function(){
        $.ajax({
            url: "api/textie/load",
            type: "GET",
            success: function (data) {
                term.echo(data);
            }
        });
    });
    $("#reloadConfig").click(function(){
        $.ajax({
            url: "api/textie/restart",
            type: "GET",
            success: function (data) {
                term.echo(data);
            }
        });
    });
</script>


<script type="text/javascript">
    $('document').ready(function(){
        $.ajax({
            url: "api/textie/getconfig",
            type: "GET",
            success: function (data) {
                $("#configArea").val(data);
            }
        });
    });
</script>
</body>
</html>
