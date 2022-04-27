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
    <div class="form-group">
        <form method="get" id="searchform" style="width:50%">
            <div class="row">
                <div class="col-md-6">
                    <input type="text" class="form-control border border-2 border-dark" id="search"
                           placeholder="Enter search term" name="search" value="${search}" />
                </div>
                <div class="col-md-4">
                    <select id="searchBy" class="form-select border border-2 border-dark" name="searchBy">
                        <option value="First Name">First Name</option>
                        <option value="Last Name">Last Name</option>
                        <option value="Email">Email</option>
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
            <c:if test="${not empty libuser}">
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th></th>
            </tr>
            </thead>
            <tbody id="libusers">
            <tr>
                <td>${libuser.firstName}</td>
                <td>${libuser.lastName}</td>
                <td class="emailcol">${libuser.email}</td>
                <td>
                    <input type="button" class="editUser" value="Edit">
                </td>
            </tr>
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
            <c:if test="${not empty libusers}">
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th></th>
            </tr>
            </thead>
            <tbody id="libusers">
            <c:forEach items="${libusers}" var="libuser">
                <tr>
                    <td>${libuser.firstName}</td>
                    <td>${libuser.lastName}</td>
                    <td class="emailcol">${libuser.email}</td>
                    <td>
                        <input type="button" class="editUser" value="Edit">
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
                    <form action="../admin/usereditsubmit" method="post">
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
                                <label for="ustatus" class="form-label">State</label>
                                <select id="ustatus" class="form-select border border-2 border-dark" name="ustatus" value="${userform.ustatus}">
                                    <option value="ACTIVE" <c:if test="${userform.ustatus == \"ACTIVE\"}">
                                        selected
                                    </c:if>>ACTIVE</option>
                                    <option value="INACTIVE" <c:if test="${userform.ustatus == \"INACTIVE\"}">
                                        selected
                                    </c:if>>INACTIVE</option>
                                </select>
                                <p class="hiddenMsg form-text"></p>
                                <c:forEach items="${bindingResult.getFieldErrors('fname')}" var="error">
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
                            <div class="col-md-5">
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
                                    <option value="Minnesota" <c:if test="${userform.state == \"Minnesota\"}">
                                        selected
                                    </c:if>>Minnesota</option>
                                    <option value="Wisconsin" <c:if test="${userform.state == \"Wisconsin\"}">
                                        selected
                                    </c:if>>Wisconsin</option>
                                    <option value="North Dakota" <c:if test="${userform.state == \"North Dakota\"}">
                                        selected
                                    </c:if>>North Dakota</option>
                                    <option value="South Dakota" <c:if test="${userform.state == \"South Dakota\"}">
                                        selected
                                    </c:if>>South Dakota</option>
                                    <option value="Iowa" <c:if test="${userform.state == \"Iowa\"}">
                                        selected
                                    </c:if>>Iowa</option>
                                </select>
                                <p class="hiddenMsg form-text"></p>
                                <c:forEach items="${bindingResult.getFieldErrors('state')}" var="error">
                                    <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                                </c:forEach>
                            </div>
                            <div class="col-md-3">
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
                                       placeholder="Enter email" name="email" value="${userform.email}"/>
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
                                       placeholder="xxxxxxxxxx" name="phone" value="${userform.phone}"/>
                                <p class="hiddenMsg"></p>
                                <c:forEach items="${bindingResult.getFieldErrors('phone')}" var="error">
                                    <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="row m-2 p-2">
                            <div class="col-md-6">
                                <input type="radio" id="male" name="gender" value="MALE" <c:if test="${userform.gender == \"MALE\"}">
                                       checked
                                </c:if>>
                                <label for="male">Male</label><br>
                            </div>
                            <div class="col-md-6">
                                <input type="radio" id="female" name="gender" value="FEMALE" <c:if test="${userform.gender == \"FEMALE\"}">
                                       checked
                                </c:if>>
                                <label for="female">Female</label><br>
                            </div>
                            <p class="hiddenMsg"></p>
                            <c:forEach items="${bindingResult.getFieldErrors('phone')}" var="error">
                                <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </div>
                        <sec:authorize access="!hasAuthority('ADMIN')">
                        <div class="row m-2 p-2">
                            <div class="col-md-6">
                                <label for="password" class="form-label">Password:</label>
                                <input type="password" class="form-control border border-2 border-dark" id="password"
                                       placeholder="Enter password" name="password" value="${userform.password}"/>
                                <p class="hiddenMsg form-text"></p>
                                <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                                    <div style="color: red;font-size: smaller;">${error.getDefaultMessage()}</div>
                                </c:forEach>
                            </div>
                            <div class="col-md-6">
                                <label for="cpassword" class="form-label">Confirm Password:</label>
                                <input type="password" class="form-control border border-2 border-dark" id="cpassword"
                                       placeholder="Confirm password" name="cpassword" value="${userform.cpassword}"/>
                                <p class="hiddenMsg form-text"></p>
                            </div>
                        </div>
                        </sec:authorize>
                        <div class="row m-2 p-2">
                            <div>
                                <input type="checkbox" id="news" name="news" value="YES"<c:if test="${userform.news == \"YES\"}">
                                       checked
                                </c:if>>
                                <label for="news">  I would like to receive a monthly newsletter.</label><br>
                            </div>
                        </div>
                        <div class="m-4 p-2">
                            <button type="submit" class="btn btn-dark" id="create">Update user information</button>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        <br/>
    </div>
</div>
<script src="../js/adminuser.js"></script>
<jsp:include page="../include/footer.jsp" />