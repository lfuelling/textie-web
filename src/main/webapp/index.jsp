<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" href="img/favicon.png">

  <title>Login</title>

  <!-- Bootstrap core CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/signin.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body>

<div class="container">
  <div class="col-md-4"></div>
<div id="loginform" class="col-md-4" style="display: none">
  <div id="wrongpass" class="alert alert-danger" style="text-align: center">
    Falscher Benutzername oder falsches Passwort!
  </div>
  <div id="security" class="alert alert-warning" style="text-align: center">
    Deine Session ist ung&uuml;ltig!
  </div>
  <form class="form-signin" role="form">
    <h2 class="form-signin-heading">Einloggen</h2>
    <input type="email" id="email" class="form-control" placeholder="Nutzername" required autofocus>
    <input type="password" id="password" class="form-control" placeholder="Passwort" required>
    <button class="btn btn-lg btn-primary btn-block" id="subbtn" type="submit">Anmelden</button>
    <a href="register.jsp">Noch keinen Account? Hier Registrieren!</a>
  </form>
</div>
<!-- /container -->
<div class="col-md-4" style="margin-top: 50px;">Textie ist ein Spiel, dass von <a href="https://github.com/lfuelling">@lfuelling</a> und <a href="https://github.com/YuleZ">@YuleZ</a> im Rahmen der Ausbildung programmiert wurde. Zum spielen ist ein Account notwendig. Textie ist kostenlos und beh&auml;lt als einzige Daten den Benutzernamen, einen Hash vom Passwort, den <a href="https://de.wikipedia.org/wiki/User_Agent">User Agent</a> und die Konfiguration/Speicherst&auml;nde des jeweiligen Nutzers. Diese Seite ist bestimmt hackbar, also ist es empfehlenswert kein bereits auf anderen Seiten verwendetes Passwort zu benutzen.</div>

</div>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="js/ie10-viewport-bug-workaround.js"></script>
<!-- JQuery -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.sha1.js"></script>
<script src="js/jquery.cookies.min.js"></script>
<script src="js/loginui.js"></script>
</body>
</html>