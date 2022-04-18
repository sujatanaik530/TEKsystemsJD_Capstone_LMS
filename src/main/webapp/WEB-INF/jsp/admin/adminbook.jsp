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
            <a class="nav-link" href="../admin/adminuser">Users</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="#">Books</a>
        </li>
    </ul>
    <br/>
    <br/>
    <div style="margin:auto;width:50%;">
        <button type="button" class="btn btn-dark" id="addBook">Add a new book</button>
    </div>
    <br/><br/>
    <div class="form-group">
        <form method="get" id="searchform" style="width:50%">
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
                <th></th>
            </tr>
            </thead>
            <tbody id="books">
            <c:forEach items="${books}" var="book">
                <tr>
                    <td><img src="${book.img}"/></td>
                    <td class="titlecol">${book.title}</td>
                    <td>${book.author}</td>
                    <td>
                        <input type="button" class="editBook" value="Edit">
                    </td>
                    <td>
                        <input type="button" class="deleteBook" value="Delete">
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
                <td></td>
            </tr>
            </tfoot>
            </c:if>
        </table>
        <br/><br/>
        <c:if test="${not empty form}">
            <div id="patronform">
                <c:if test="${empty form.id}">
                    <h2 class="text-center m-5">Add New Book</h2>
                </c:if>

                <c:if test="${not empty form.id}">
                    <h2 class="text-center m-5">Edit Book Information</h2>
                </c:if>

                <div class="form-group">
                    <form action="addbooksubmit" method="post">
                        <input type="hidden" name="id" value="${form.id}">
                        <div class="row m-3 p-2">
                            <label for="title" class="form-label">Book Title:</label>
                            <input type="text" class="form-control border border-2 border-dark" id="title"
                                   placeholder="Enter book title" name="title" value="${form.title}" />
                            <p class="hiddenMsg form-text"></p>
                            <c:forEach items="${bindingResult.getFieldErrors('title')}" var="error">
                                <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </div>
                        <div class="row m-3 p-2">
                            <label for="author" class="form-label">Book Author:</label>
                            <input type="text" class="form-control border border-2 border-dark" id="author"
                                   placeholder="Enter author name" name="author" value="${form.author}" />
                            <p class="hiddenMsg form-text"></p>
                            <c:forEach items="${bindingResult.getFieldErrors('author')}" var="error">
                                <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </div>
                        <div class="row m-3 p-2">
                            <label for="category" class="form-label">Book Category:</label>
                            <input type="text" class="form-control border border-2 border-dark" id="category"
                                   placeholder="Enter book category" name="category" value="${form.category}" />
                            <p class="hiddenMsg form-text"></p>
                            <c:forEach items="${bindingResult.getFieldErrors('category')}" var="error">
                                <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </div>
                        <div class="row m-3 p-2">
                            <label for="price" class="form-label">Book Price:</label>
                            <input type="number" step="0.01" class="form-control border border-2 border-dark" id="price"
                                   placeholder="Enter book price" name="price" value="${form.price}" />
                            <p class="hiddenMsg form-text"></p>
                            <c:forEach items="${bindingResult.getFieldErrors('price')}" var="error">
                                <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </div>
                        <div class="row m-3 p-2">
                            <label for="condition" class="form-label">Book Condition:</label>
                            <select id="condition" class="form-select border border-2 border-dark" name="condition" value="${form.condition}">
                                <option value="GOOD" <c:if test="${form.condition == \"GOOD\"}">
                                    selected
                                </c:if>>Good</option>
                                <option value="DAMAGED" <c:if test="${form.condition == \"DAMAGED\"}">
                                    selected
                                </c:if>>Damaged</option>
                            </select>
                            <p class="hiddenMsg form-text"></p>
                            <c:forEach items="${bindingResult.getFieldErrors('condition')}" var="error">
                                <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </div>
                        <div class="row m-3 p-2">
                            <label for="status" class="form-label">Book Status:</label>
                            <select id="status" class="form-select border border-2 border-dark" name="status" value="${form.status}">
                                <option value="AVAILABLE" <c:if test="${form.status == \"AVAILABLE\"}">
                                    selected
                                </c:if>>Available</option>
                                <option value="CHECKEDOUT" <c:if test="${form.status == \"CHECKEDOUT\"}">
                                    selected
                                </c:if>>Checked Out</option>
                                <option value="LOST" <c:if test="${form.status == \"LOST\"}">
                                    selected
                                </c:if>>Lost</option>
                            </select>
                            <p class="hiddenMsg form-text"></p>
                            <c:forEach items="${bindingResult.getFieldErrors('status')}" var="error">
                                <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </div>
                        <div class="row m-3 p-2">
                            <label for="imageURL" class="form-label">Thumbnail URL:</label>
                            <input type="text" class="form-control border border-2 border-dark" id="imageURL"
                                   placeholder="Enter thumbnail URL" name="imageURL" value="${form.imageURL}" />
                            <p class="hiddenMsg form-text"></p>
                            <c:forEach items="${bindingResult.getFieldErrors('imageURL')}" var="error">
                                <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </div>
                        <div class="m-4 p-2">
                            <button type="submit" class="btn btn-dark" id="addbookSubmit">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        <br/>
    </div>
</div>
<script src="../js/adminbook.js"></script>
</body>

</html>