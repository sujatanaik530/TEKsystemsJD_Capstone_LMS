<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <a class="index-header" href="index">
                <i class="fa-solid fa-book"></i>&nbsp;&nbsp;The Local Library
            </a>
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
    </div>
</div>
<br>
<br>
</body>
<!--
<script src="js/GoodReads.js"></script>
-->
</html>