<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="./include/header.jsp" />
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
<jsp:include page="./include/footer.jsp" />

<!-- TODO Redesign landing page with a picture and two buttons: sign up and login. -->