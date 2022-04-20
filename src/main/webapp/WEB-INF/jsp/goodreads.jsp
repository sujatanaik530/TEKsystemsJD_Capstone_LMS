<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="./include/header.jsp" />
<body>
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
    <div class="row">
        <div class="col">
            <h2 class="text-center m-4">Good reads</h2>
        </div>
    </div>
    <div class="row container">
        <table class="table" style="width: 70%; margin: auto; background: white;">
            <thead class="table-dark">
            <tr>
                <th></th>
                <th>Title</th>
                <th>Author</th>
            </tr>
            </thead>
            <tbody id="books">
            <c:forEach items="${books}" var="book">
                <tr>
                    <td class="ratio1"><img src="${book.img}" class="bookimg"/></td>
                    <td class="ratio5">${book.title}</td>
                    <td class="ratio4">${book.author}</td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot class="table-dark">
            <tr>
                <td class="foot"></td>
                <td class="foot"></td>
                <td class="foot"></td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
<br>
<br>
<jsp:include page="./include/footer.jsp" />