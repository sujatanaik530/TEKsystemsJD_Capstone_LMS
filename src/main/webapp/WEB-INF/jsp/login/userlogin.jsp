<!DOCTYPE html>
<html lang="en">
<head>
    <title>The Local Library</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/80ef1619da.js" crossorigin="anonymous"></script>
    <link href="../css/styles.css" rel="stylesheet">
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
        <h2 class="text-center m-4">User Login</h2>
        <div class="form-group">
            <form action="/login/loginSubmit" method="POST">
                <div class="m-3 p-2">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" class="form-control border border-2 border-dark" id="email"
                           placeholder="Enter email" name="username">
                    <p class="hiddenMsg form-text"></p>
                </div>
                <div class="m-3 p-2">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" class="form-control border border-2 border-dark" id="password"
                           placeholder="Enter password" name="password">
                    <p class="hiddenMsg form-text"></p>
                </div>
                <div class="m-3 p-2">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input border border-2 border-dark" type="checkbox"
                                   name="remember"> Remember me
                        </label>
                    </div>
                </div>
                <button type="submit" class="btn btn-dark m-4 p-2" id="login">Login</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>