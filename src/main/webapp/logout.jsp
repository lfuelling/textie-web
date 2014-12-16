<html>
<head>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.cookies.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $.cookies.del("token");
            window.location.href = "index.jsp";
        });
    </script>
</head>
<body><b>REDIRECTING...</b></body>
</html>