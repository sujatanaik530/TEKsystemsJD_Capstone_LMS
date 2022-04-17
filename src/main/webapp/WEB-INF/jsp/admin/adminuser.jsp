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
            <a class="nav-link active" href="#">Users</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="../admin/adminbook">Books</a>
        </li>
    </ul>
    <br/>
    <br/>
    <div style="margin:auto;width:50%;">
        <button type="button" class="btn btn-dark" id="addUser">Add a new user</button>
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
                        <option value="First Name">First Name</option>
                        <option value="Last Name">Last Name</option>
                        <option value="Email">Email</option>
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
            <c:if test="${not empty libusers}">
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody id="libusers">
            <c:forEach items="${libusers}" var="libuser">
                <tr>
                    <td>${libuser.firstName}</td>
                    <td>${libuser.lastName}</td>
                    <td>${libuser.email}</td>
                    <td>
                        <input type="button" class="editUser" value="Edit">
                    </td>
                    <td>
                        <input type="button" class="deleteUser" value="Delete">
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
        <c:if test="${not empty userform}">
            <div id="patronform">
                <c:if test="${empty userform.id}">
                    <h2 class="text-center m-4">User Registration</h2>
                </c:if>

                <c:if test="${not empty userform.id}">
                    <h2 class="text-center m-4">Edit Account Information</h2>
                </c:if>

                <div class="form-group">
                    <form action="../login/userregistrationsubmit" method="post">
                        <div class="row m-2 p-2">
                            <div class="col-md-6">
                                <label for="fname" class="form-label">First Name:</label>
                                <input type="text" class="form-control border border-2 border-dark" id="fname"
                                       placeholder="Enter first name" name="fname" value="${userform.fname}" />
                                <p class="hiddenMsg form-text"></p>
                                <c:forEach items="${bindingResult.getFieldErrors('fname')}" var="error">
                                    <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                                </c:forEach>
                            </div>
                            <div class="col-md-6">
                                <label for="lname" class="form-label">Last Name:</label>
                                <input type="text" class="form-control border border-2 border-dark" id="lname"
                                       placeholder="Enter last name" name="lname" value="${userform.lname}" />
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
                                       placeholder="1234 Main St" name="aline1" value="${userform.aline1}" />
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
                                       placeholder="Apartment, studio, or floor" name="aline2"  value="${userform.aline2}"/>
                                <p class="hiddenMsg form-text"></p>
                            </div>
                        </div>
                        <div class="row m-2 p-2">
                            <div class="col-md-6">
                                <label for="inputCity" class="form-label">City</label>
                                <input type="text" class="form-control border border-2 border-dark" id="inputCity"
                                       name="city" value="${userform.city}" />
                                <p class="hiddenMsg form-text"></p>
                                <c:forEach items="${bindingResult.getFieldErrors('city')}" var="error">
                                    <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                                </c:forEach>
                            </div>
                            <div class="col-md-4">
                                <label for="inputState" class="form-label">State</label>
                                <select id="inputState" class="form-select border border-2 border-dark" name="state" value="${userform.state}">
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
                                       name="zip" value="${userform.zip}" />
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
                                       placeholder="Enter email" name="email" value="${userform.email}" />
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
                                       placeholder="xxx-xxx-xxxx" name="phone" value="${userform.phone}"/>
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
                                       placeholder="Enter password" name="password" value="${userform.password}" />
                                <p class="hiddenMsg form-text"></p>
                                <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                                    <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                                </c:forEach>
                            </div>
                            <div class="col-md-6">
                                <label for="cpassword" class="form-label">Confirm Password:</label>
                                <input type="password" class="form-control border border-2 border-dark" id="cpassword"
                                       placeholder="Confirm password" name="cpassword" value="${userform.cpassword}" />
                                <p class="hiddenMsg form-text"></p>
                            </div>
                        </div>
                        <div class="m-4 p-2">
                            <button type="submit" class="btn btn-dark" id="create">Create account</button>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        <br/>
    </div>
</div>
<script src="../js/adminuser.js"></script>
</body>

</html>