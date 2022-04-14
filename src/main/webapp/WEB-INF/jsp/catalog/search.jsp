<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>The Local Library</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
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
                        <td class="ratio1"><img src="${book.img}"/></td>
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
</body>

</html>