<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>The Local Library</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/80ef1619da.js" crossorigin="anonymous"></script>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
</head>
<body style="background: none">
<div class="container">
    <nav class="navbar m-3 justify-content-center">
        <div class="container-fluid">
            <a class="index-header" href="index">
                <i class="fa-solid fa-book"></i>&nbsp;&nbsp;The Local Library
            </a>
            <sec:authorize access="isAuthenticated()">
                <em><sec:authentication property="principal.username"></sec:authentication></em>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/user/usersearch"><i class="fa-solid fa-user fa-border" title="Go to your personal account"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/login/logout" title="Logout"><i class="fa-solid fa-arrow-right-from-bracket"></i></a>
            </sec:authorize>
        </div>
    </nav>
    <div class="row container">
        <div class="col-sm-8 p-3 text-white">
            <img src="images/landing.jpg" alt="A picture of a library" width="100%" height="auto">
        </div>
        <div class="container btn-group-vertical col-sm-4 p-3 text-white">
            <button type="button" id="goodreads" class="btn btn-outline-dark">Good Reads</button>
            <button type="button" id="plogin" class="btn btn-outline-dark">User Login</button>
            <button type="button" id="pregister" class="btn btn-outline-dark">User Registration</button>
        </div>
    </div>
</div>
<script src="js/index.js"></script>
</body>
</html>

<!-- TODO Redesign landing page with a picture and two buttons: sign up and login. -->