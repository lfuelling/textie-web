<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.png">

    <title>Registrierung</title>

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


<div id="loginform" class="container" style="display: none">
    <div id="wrongpass" class="alert alert-danger" style="text-align: center">
        Fehler. Bitte erneut versuchen.
    </div>
    <div id="useralready" class="alert alert-warning" style="text-align: center">
        Diesen Nutzernamen gibt es schon!
    </div>
    <form class="form-signin" role="form">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" id="email" class="form-control" placeholder="Nutzername" required autofocus>
        <input type="password" id="password" class="form-control" placeholder="Passwort" required>
        <input type="password" id="password2" class="form-control" placeholder="Passwort best&auml;tigen" required>
        <button class="btn btn-lg btn-primary btn-block" id="subbtn" type="submit">Registrieren</button>
        <a href="index.jsp">Hast du schon einen Account?</a>
    </form>
</div>
<!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="js/ie10-viewport-bug-workaround.js"></script>
<!-- JQuery -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.sha1.js"></script>
<script src="js/jquery.cookies.min.js"></script>
<script src="js/registerui.js"></script>
</body>
</html>