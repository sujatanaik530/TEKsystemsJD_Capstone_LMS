document.getElementById("goodreads").addEventListener("click", goGoodReadsPage);
document.getElementById("plogin").addEventListener("click", goPatronLoginPage);
document.getElementById("pregister").addEventListener("click", goPatronRegistrationPage);

function goGoodReadsPage() {
    location.assign("./goodreads");
}

function goPatronLoginPage() {
    location.assign("login/login/Welcome!");
}

function goPatronRegistrationPage() {
    location.assign("login/userregistration");
}