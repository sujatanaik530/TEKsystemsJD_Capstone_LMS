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
                    <a href="/user/usersearch"><i class="fa-solid fa-user fa-border" title="Switch to your personal account"></i></a>
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
            <a class="nav-link" href="../admin/adminuser">Users</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="../admin/adminbook">Books</a>
        </li>
    </ul>
    <br/>
    <br/>
    <div class="form-group">
        <form method="get" id="searchform">
            <div class="row">
                <div class="col-md-6">
                    <input type="text" class="form-control border border-2 border-dark" id="search"
                           placeholder="Enter search term" name="search" value="${search}" />
                </div>
                <div class="col-md-4">
                    <select id="searchBy" class="form-select border border-2 border-dark" name="searchBy">
                        <option value="Checked Out Titles">Checked Out Titles</option>
                        <option value="Available Titles">Available Titles</option>
                        <option value="Checked Out Authors">Checked Out Authors</option>
                        <option value="Available Authors">Available Authors</option>
                        <option value="Checked Out By User">Checked Out By User</option>
                    </select>
                </div>
                <div class="col-md-2">
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
            <c:if test="${not empty checkedoutuserbooks}">
                <tr>
                    <th></th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Due Date</th>
                </tr>
                </thead>
                <tbody id="books">
                <c:forEach items="${checkedoutuserbooks}" var="checkedoutuserbook">
                    <tr>
                        <td><img src="${checkedoutuserbook.book.img}" alt="Book Cover Thumbnail" class="bookimg"/></td>
                        <td>${checkedoutuserbook.book.title}</td>
                        <td>${checkedoutuserbook.book.author}</td>
                        <td>${checkedoutuserbook.dueDate}</td>
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
            <c:if test="${not empty availablebooks}">
                <tr>
                    <th></th>
                    <th>Title</th>
                    <th>Author</th>
                </tr>
                </thead>
                <tbody id="books">
                <c:forEach items="${availablebooks}" var="availablebook">
                    <tr>
                        <td><img src="${availablebook.img}" alt="Book Cover Thumbnail" class="bookimg"/></td>
                        <td>${availablebook.title}</td>
                        <td>${availablebook.author}</td>
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
            </c:if>
            <c:if test="${not empty checkedoutauthors}">
                <tr>
                    <th></th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Due Date</th>
                </tr>
                </thead>
                <tbody id="books">
                <c:forEach items="${checkedoutauthors}" var="checkedoutauthor">
                    <tr>
                        <td><img src="${checkedoutauthor.book.img}" alt="Book Cover Thumbnail" class="bookimg"/></td>
                        <td>${checkedoutauthor.book.title}</td>
                        <td>${checkedoutauthor.book.author}</td>
                        <td>${checkedoutauthor.dueDate}</td>
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
            <c:if test="${not empty availableauthors}">
                <tr>
                    <th></th>
                    <th>Title</th>
                    <th>Author</th>
                </tr>
                </thead>
                <tbody id="books">
                <c:forEach items="${availableauthors}" var="availableauthor">
                    <tr>
                        <td><img src="${availableauthor.img}" alt="Book Cover Thumbnail" class="bookimg"/></td>
                        <td>${availableauthor.title}</td>
                        <td>${availableauthor.author}</td>
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
            </c:if>
            <c:if test="${not empty userbooks}">
                <tr>
                    <th></th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Due Date</th>
                </tr>
                </thead>
                <tbody id="books">
                <c:forEach items="${userbooks}" var="userbook">
                    <tr>
                        <td><img src="${userbook.book.img}" alt="Book Cover Thumbnail" class="bookimg"/></td>
                        <td>${userbook.book.title}</td>
                        <td>${userbook.book.author}</td>
                        <td>${userbook.dueDate}</td>
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
<script src="../js/adminsearch.js"></script>
<jsp:include page="../include/footer.jsp" />