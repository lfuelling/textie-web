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
    <link rel="icon" type="image/vnd.microsoft.icon" href="img/favicon.ico">
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
            <a class="navbar-brand" href="#">Textie</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Aktionen<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#" data-toggle="modal" data-target="#savegameLoadModal">Savegame Laden</a></li>
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
                <div class="alert alert-warning" role="alert">
                    Achtung: Das &Auml;ndern der Konfiguration macht die Savegames möglicherweise unbrauchbar.
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
                <button type="button" class="btn btn-primary">Speichern</button>
            </div>
        </div>
    </div>
</div>

<!-- savegameLoadModal -->
<div class="modal fade" id="savegameLoadModal" tabindex="-1" role="dialog" aria-labelledby="savegameLoadModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="savegameLoadModalLabel">Neues Savegame Laden</h4>
            </div>
            <div class="modal-body">
                <p>Wähle bitte einen Slot zum laden aus. Wenn du im Spiel "speichern" ausführst, wird das aktuelle Spiel in diesem Slot gespeichert.</p>
                <div class="btn-group" role="group" aria-label="slotSelector">
                    <button type="button" class="btn btn-default">Slot 1</button>
                    <button type="button" class="btn btn-default">Slot 2</button>
                    <button type="button" class="btn btn-default">Slot 3</button>
                    <button type="button" class="btn btn-default">Slot 4</button>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Abbrechen</button>
                <button type="button" class="btn btn-default">Alle Slots Leeren</button>
                <button type="button" class="btn btn-primary">Speichern</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
