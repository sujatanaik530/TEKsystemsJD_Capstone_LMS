<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />
<body>
    <div class="container">
        <nav class="navbar m-3 justify-content-center">
            <div class="container-fluid">
                <a class="index-header" href="../index">
                    <i class="fa-solid fa-book"></i>&nbsp;&nbsp;The Local Library
                </a>
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
                <a class="nav-link" href="../user/useraccountinfo">Account Information</a>
            </li>
        </ul>
        <br/>
        <br/>
        <div class="form-group">
            <form method="get" id="patronform" style="border:none;">
                <div class="row">
                    <div class="col-md-6">
                        <input type="text" class="form-control border border-2 border-dark" id="search"
                               placeholder="Enter search term" name="search" value="${form.search}" />
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
            <table class="table" style="margin: auto; background: white;">
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
            <br/>
        </div>
    </div>
    <script src="../js/search.js"></script>
<jsp:include page="../include/footer.jsp" />
<%-- This file is not used any longer. --%>