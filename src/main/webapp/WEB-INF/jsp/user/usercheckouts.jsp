<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />
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
                    <a href="/admin/adminsearch"><i class="fa-solid fa-user-lock" title="Switch to your administrator account"></i></a>
                </sec:authorize>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/login/logout" title="Logout"><i class="fa-solid fa-arrow-right-from-bracket"></i></a>
            </sec:authorize>
        </div>
    </nav>
    <ul class="nav nav-tabs nav-fill">
        <li class="nav-item">
            <a class="nav-link" href="usersearch">Search</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="#">My Checkouts</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="../user/edit">Account Information</a>
        </li>
    </ul>
    <div>
        <br />
        <c:if test="${not empty message}">
            <div id="libmsg">
                <br/>
                    ${message}
                <br/>
                <br/>
            </div>
            <br />
        </c:if>
        <c:if test="${not empty borrowed}">
        <table class="table" id="myTable" style="margin: auto; background: white;">
            <thead class="table-dark">
            <tr>
                <th></th>
                <th>Title</th>
                <th>Due On</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${borrowed}" var="borrowedbook">
                <tr>
                    <td style="width:10%"><img src="${borrowedbook.book.img}" class="bookimg"/></td>
                    <td class="titlecol">${borrowedbook.book.title}</td>
                    <td>${borrowedbook.dueDate}</td>
                    <td>
                        <input type="button" class="returnbook" value="Return">
                    </td>
                    <td>
                        <input type="button" class="renewbook" value="Renew">
                    </td>
                </tr>
                </c:forEach>
            </tbody>
            <tfoot class="table-dark">
            <tr>
                <td></td>
                <td class="foot"></td>
                <td class="foot"></td>
                <td class="foot"></td>
                <td class="foot"></td>
            </tr>
            </tfoot>
        </table>
        </c:if>
        <br/>
    </div>
</div>
<script src="../js/usercheckouts.js"></script>
<jsp:include page="../include/footer.jsp" />