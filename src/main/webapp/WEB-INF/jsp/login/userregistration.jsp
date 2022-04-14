<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>The Local Library - User Registration</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="../css/styles.css" rel="stylesheet" type="text/css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/80ef1619da.js" crossorigin="anonymous"></script>
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
    <div id="patronform">
        <c:if test="${empty form.id}">
            <h2 class="text-center m-4">User Registration</h2>
        </c:if>

        <c:if test="${not empty form.id}">
            <h2 class="text-center m-4">Edit Account Information</h2>
        </c:if>

        <div class="form-group">
            <form action="/login/userregistrationsubmit" method="post">
                <div class="row m-2 p-2">
                    <div class="col-md-6">
                        <label for="fname" class="form-label">First Name:</label>
                        <input type="text" class="form-control border border-2 border-dark" id="fname"
                               placeholder="Enter first name" name="fname" value="${form.fname}" />
                        <p class="hiddenMsg form-text"></p>
                        <c:forEach items="${bindingResult.getFieldErrors('fname')}" var="error">
                            <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </div>
                    <div class="col-md-6">
                        <label for="lname" class="form-label">Last Name:</label>
                        <input type="text" class="form-control border border-2 border-dark" id="lname"
                               placeholder="Enter last name" name="lname" value="${form.lname}" />
                        <p class="hiddenMsg form-text"></p>
                        <c:forEach items="${bindingResult.getFieldErrors('lname')}" var="error">
                            <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </div>
                </div>
                <div class="row m-2 p-2">
                    <div>
                        <label for="inputAddress" class="form-label">Address Line 1</label>
                        <input type="text" class="form-control border border-2 border-dark" id="inputAddress"
                               placeholder="1234 Main St" name="aline1" value="${form.aline1}" />
                        <p class="hiddenMsg form-text"></p>
                        <c:forEach items="${bindingResult.getFieldErrors('aline1')}" var="error">
                            <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </div>
                </div>
                <div class="row m-2 p-2">
                    <div>
                        <label for="inputAddress2" class="form-label">Address Line 2</label>
                        <input type="text" class="form-control border border-2 border-dark" id="inputAddress2"
                               placeholder="Apartment, studio, or floor" name="aline2"/>
                        <p class="hiddenMsg form-text"></p>
                    </div>
                </div>
                <div class="row m-2 p-2">
                    <div class="col-md-6">
                        <label for="inputCity" class="form-label">City</label>
                        <input type="text" class="form-control border border-2 border-dark" id="inputCity"
                               name="city" value="${form.city}" />
                        <p class="hiddenMsg form-text"></p>
                        <c:forEach items="${bindingResult.getFieldErrors('city')}" var="error">
                            <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </div>
                    <div class="col-md-4">
                        <label for="inputState" class="form-label">State</label>
                        <select id="inputState" class="form-select border border-2 border-dark" name="state" value="${form.state}">
                            <option></option>
                            <option value="MN">Minnesota</option>
                            <option value="WI">Wisconsin</option>
                            <option value="ND">North Dakota</option>
                            <option value="SD">South Dakota</option>
                            <option value="IA">Iowa</option>
                        </select>
                        <p class="hiddenMsg form-text"></p>
                        <c:forEach items="${bindingResult.getFieldErrors('state')}" var="error">
                            <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </div>
                    <div class="col-md-2">
                        <label for="inputZip" class="form-label">Zip</label>
                        <input type="text" class="form-control border border-2 border-dark" id="inputZip"
                               name="zip" value="${form.zip}" />
                        <p class="hiddenMsg form-text"></p>
                        <c:forEach items="${bindingResult.getFieldErrors('zip')}" var="error">
                            <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </div>
                </div>
                <div class="row m-2 p-2">
                    <div>
                        <label for="email" class="form-label">Email:</label>
                        <input type="email" class="form-control border border-2 border-dark" id="email"
                               placeholder="Enter email" name="email" value="${form.email}" />
                        <p class="hiddenMsg form-text"></p>
                        <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                            <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </div>
                </div>
                <div class="row m-2 p-2">
                    <div>
                        <label for="phone" class="form-label">Telephone:</label>
                        <input type="text" class="form-control border border-2 border-dark" id="phone"
                               placeholder="xxx-xxx-xxxx" name="phone" value="${form.phone}"/>
                        <p class="hiddenMsg"></p>
                        <c:forEach items="${bindingResult.getFieldErrors('phone')}" var="error">
                            <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </div>
                </div>
                <div class="row m-2 p-2">
                    <div class="col-md-6">
                        <label for="password" class="form-label">Password:</label>
                        <input type="password" class="form-control border border-2 border-dark" id="password"
                               placeholder="Enter password" name="password" value="${form.password}" />
                        <p class="hiddenMsg form-text"></p>
                        <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                            <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </div>
                    <div class="col-md-6">
                        <label for="cpassword" class="form-label">Confirm Password:</label>
                        <input type="password" class="form-control border border-2 border-dark" id="cpassword"
                               placeholder="Confirm password" name="cpassword" value="${form.cpassword}" />
                        <p class="hiddenMsg form-text"></p>
                    </div>
                </div>
                <div class="m-4 p-2">
                    <button type="submit" class="btn btn-dark" id="create">Create account</button>
                </div>
            </form>
        </div>
    </div>
</div>
<br />
<br />
<!--<script src="../js/userregistration.js"></script>-->
</body>

</html>