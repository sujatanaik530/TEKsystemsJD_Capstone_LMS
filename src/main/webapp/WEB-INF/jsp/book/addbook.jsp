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
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <h1 class="text-center m-5"><a href="../index" title="Back to home page"><i class="fa-solid fa-house-chimney" style="color:black"></i></a>&nbsp&nbspThe Local Library</h1>
        </div>
    </div>
    <div id="patronform">
        <c:if test="${empty form.id}">
            <h2 class="text-center m-5">Add New Book</h2>
        </c:if>
        <c:if test="${not empty form.id}">
            <h2 class="text-center m-5">Edit Book Information</h2>
        </c:if>
        <div class="form-group">
            <form action="addbooksubmit" method="post">
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
                        <option value="Good">Good</option>
                        <option value="Damaged">Damaged</option>
                    </select>
                    <p class="hiddenMsg form-text"></p>
                    <c:forEach items="${bindingResult.getFieldErrors('condition')}" var="error">
                        <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </div>
                <div class="row m-3 p-2">
                    <label for="status" class="form-label">Book Status:</label>
                    <select id="status" class="form-select border border-2 border-dark" name="status" value="${form.status}">
                        <option value="Available">Available</option>
                        <option value="Checked Out">Checked Out</option>
                        <option value="Lost">Lost</option>
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
                    <button type="submit" class="btn btn-dark" id="addbook">Add Book</button>
                </div>
            </form>
        </div>
    </div>
</div>
<br />
<br />
</body>
</html>