<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>The Local Library</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="../css/styles.css" rel="stylesheet" type="text/css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/80ef1619da.js" crossorigin="anonymous"></script>
    <style>
        img {
            width: 100px;
            border: 1px solid;
        }

        .ratio1 {
            width: 10%;
        }

        .ratio5 {
            width: 50%;
        }

        .ratio4 {
            width: 40%;
        }
    </style>
</head>

<body>
<div class="container">
    <nav class="navbar m-3 justify-content-center">
        <div class="container-fluid">
            <a class="index-header" href="../index">
                <i class="fa-solid fa-book"></i>&nbsp;&nbsp;The Local Library
            </a>
            <sec:authorize access="isAuthenticated()">
                <em><sec:authentication property="principal.username"></sec:authentication></em>&nbsp;&nbsp;&nbsp;&nbsp;
                <sec:authorize access="hasAuthority('ADMIN')">
                    <a href="/user/usersearch"><i class="fa-solid fa-user fa-border" title="Switch to your personal account"></i></a>
                </sec:authorize>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/login/logout" title="Logout"><i class="fa-solid fa-arrow-right-from-bracket"></i></a>
            </sec:authorize>
        </div>
    </nav>
    <ul class="nav nav-tabs nav-fill">
        <li class="nav-item">
            <a class="nav-link" href="../admin/adminsearch">Search</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="#">Users</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="../admin/adminbook">Books</a>
        </li>
    </ul>
    <br/>
    <br/>
    <div style="margin:auto;width:50%;">
        <button type="button" class="btn btn-dark">Add a new user</button>
        <button type="button" class="btn btn-dark">Edit user information</button>
        <button type="button" class="btn btn-dark">Delete a user</button>
    </div>
    <div class="form-group">
    </div>
</div>
<script src="../js/adminuser.js"></script>
</body>

</html>