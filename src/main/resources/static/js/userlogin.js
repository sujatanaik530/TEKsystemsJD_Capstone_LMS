let emailElement = document.getElementById("email");
let passwordElement = document.getElementById("password");
let loginElement = document.getElementById("login");

let hiddenEElement = document.querySelector("#email+.hiddenMsg");
let hiddenPElement = document.querySelector("#password+.hiddenMsg");

loginElement.addEventListener("click", confirmLogin);

emailElement.addEventListener("input", function () {
    hiddenEElement.style.display = "none";
});

passwordElement.addEventListener("input", function () {
    hiddenPElement.style.display = "none";
});


function confirmLogin() {
    let validEmail = validateEmail(emailElement.value);
    let validPassword = validatePassword(passwordElement.value);
    if (validEmail && validPassword) {
        alert("Welcome, " + emailElement.value);
    }
    else if (!validEmail && !validPassword) {
        repeatForm();
    }
    else if (!validEmail) {
        repeatEmail();
    }
    else if (!validPassword) {
        repeatPassword();
    }
}

function repeatForm() {
    hiddenEElement.style.display = "block";
    hiddenEElement.textContent = "Please enter your email here.";
    hiddenPElement.style.display = "block";
    emailElement.focus();
}

function repeatEmail() {
    hiddenEElement.style.display = "block";
    hiddenEElement.textContent = "Please enter your email here.";
}

function repeatPassword() {
    hiddenPElement.style.display = "block";
}

function validateEmail(emailstr) {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(emailstr)) {
        return true;
    }
    else {
        return false;
    }
}

function validatePassword(fPassword) {
    if (fPassword.length < 8 || fPassword.length > 20) {
        hiddenPElement.textContent = "Password length must be between 8 and 20 characters.";
        return false;
    } else if (!/[a-zA-Z]/.test(fPassword)) {
        // regex to check for atleast one letter
        hiddenPElement.textContent = "Password must contain a letter.";
        return false;
    } else if (!(/\d/.test(fPassword))) {
        // regex to check for atleast one digit
        hiddenPElement.textContent = "Password must contain a number.";
        return false;
    }
    return true;
}