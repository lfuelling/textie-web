<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" href="img/favicon.ico">

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

<div id="loginform" class="container" style="display: none">
  <div id="wrongpass" class="alert alert-danger" style="text-align: center">
    Wrong email or password!
  </div>
  <div id="security" class="alert alert-warning" style="text-align: center">
    Wrong email or password!
  </div>
  <form class="form-signin" role="form">
    <h2 class="form-signin-heading">Please sign in</h2>
    <input type="email" id="email" class="form-control" placeholder="Email address" required autofocus>
    <input type="password" id="password" class="form-control" placeholder="Password" required>

    <div class="checkbox">
      <label>
        <input type="checkbox" value="remember-me"> Remember me
      </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" id="subbtn" type="submit">Sign in</button>
    <a href="register.jsp">Register</a>
  </form>
</div>
<!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="js/ie10-viewport-bug-workaround.js"></script>
<!-- JQuery -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.sha1.js"></script>
<script src="js/jquery.cookies.min.js"></script>
<!-- Initial stuff! -->
<script type="text/javascript">
  $(document).ready(function () {
    var token = $.cookies.get("token");
    if (token == null) {
      $("#wrongpass").hide();
      $("#security").hide();
      $("#loginform").fadeIn("slow").show();
    } else {
      $.ajax({
        url: "webapi/restore",
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
            window.location.href = "main.jsp";
          } else {
            console.log("no connection to api.");
          }
        }
      })
    }
  });
</script>
<!-- Submit behavior -->
<script type="text/javascript">
  $("#subbtn").click(function (event) {
    event.preventDefault();
    var pass = $("#password").val();
    var passenc = $.sha1(pass);
    $.ajax({
      url: "webapi/login",
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
          window.location.href = "main.jsp";
        } else {
          $("#wrongpass").fadeIn("slow").show();
        }
      }
    })
  });
</script>
</body>
</html>