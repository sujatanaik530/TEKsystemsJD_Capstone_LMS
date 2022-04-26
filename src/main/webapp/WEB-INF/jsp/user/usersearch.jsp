<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <a class="nav-link active" href="#">Search</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="../user/usercheckouts">My Checkouts</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="../user/edit">Account Information</a>
        </li>
    </ul>
    <br/>
    <br/>
    <div class="form-group">
        <form method="get" id="searchform" style="border:none;">
           <div class="row">
                <div class="col-md-6">
                    <input type="text" class="form-control border border-2 border-dark" id="search"
                           placeholder="Enter search term" name="search" value="${search}" />
                </div>
                <div class="col-md-3">
                    <select id="searchBy" class="form-select border border-2 border-dark" name="searchBy">
                        <option value="Title">Title</option>
                        <option value="Author">Author</option>
                        <option value="Category">Category</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <button type="button" id="searchButton" class="btn btn-dark">Search</button>
                </div>
            </div>
        </form>
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
        <table class="table" style="margin: auto; background: white;" id="myTable">
            <thead class="table-dark">
            <c:if test="${not empty books}">
                <tr>
                    <th></th>
                    <th>Title</th>
                    <th>Author</th>
                    <th></th>
                </tr>
            </thead>
            <tbody id="books">
            <c:forEach items="${books}" var="book">
                <tr>
                    <td class="ratio1"><img src="${book.img}" class="bookimg"/></td>
                    <td class="ratio4 titlecol">${book.title}</td>
                    <td class="ratio3">${book.author}</td>
                    <td class="ratio2">
                        <c:if test="${book.status == 'AVAILABLE'}">
                        <input type="button" class="checkoutbook" value="Check Out">
                        </c:if>
                        <c:if test="${book.status == 'CHECKEDOUT'}">
                            Currently checked out
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot class="table-dark">
            <tr>
                <td class="foot"></td>
                <td class="foot"></td>
                <td class="foot"></td>
                <td class="foot"></td>
            </tr>
            </tfoot>
            </c:if>
        </table>
        <br/>
    </div>
</div>
<script src="../js/usersearch.js"></script>
<jsp:include page="../include/footer.jsp" />