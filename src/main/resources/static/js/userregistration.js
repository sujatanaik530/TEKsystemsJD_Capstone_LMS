/*let fnameElement = document.getElementById("fname");
let lnameElement = document.getElementById("lname");
let inputAddressElement = document.getElementById("inputAddress");
let inputAddress2Element = document.getElementById("inputAddress2");
let inputCityElement = document.getElementById("inputCity");
let inputStateElement = document.getElementById("inputState");
let inputZipElement = document.getElementById("inputZip");
let emailElement = document.getElementById("email");
let phoneElement = document.getElementById("phone");
let passwordElement = document.getElementById("password");
let cpasswordElement = document.getElementById("cpassword");
let createElement = document.getElementById("create");

let hiddenFNElement = document.querySelector("#fname+.hiddenMsg");
let hiddenLNElement = document.querySelector("#lname+.hiddenMsg");
let hiddenIAElement = document.querySelector("#inputAddress+.hiddenMsg");
let hiddenICElement = document.querySelector("#inputCity+.hiddenMsg");
let hiddenISElement = document.querySelector("#inputState+.hiddenMsg");
let hiddenIZElement = document.querySelector("#inputZip+.hiddenMsg");
let hiddenEElement = document.querySelector("#email+.hiddenMsg");
let hiddenPhElement = document.querySelector("#phone+.hiddenMsg");
let hiddenPElement = document.querySelector("#password+.hiddenMsg");
let hiddencPElement = document.querySelector("#cpassword+.hiddenMsg");

createElement.addEventListener("click", validate);

fnameElement.addEventListener("input", function () {
    hiddenFNElement.style.display = "none";
});

lnameElement.addEventListener("input", function () {
    hiddenLNElement.style.display = "none";
});

inputAddressElement.addEventListener("input", function () {
    hiddenIAElement.style.display = "none";
});

inputCityElement.addEventListener("input", function () {
    hiddenICElement.style.display = "none";
});
inputStateElement.addEventListener("input", function () {
    hiddenISElement.style.display = "none";
});

inputZipElement.addEventListener("input", function () {
    hiddenIZElement.style.display = "none";
});

emailElement.addEventListener("input", function () {
    hiddenEElement.style.display = "none";
});

phoneElement.addEventListener("input", function () {
    hiddenPhElement.style.display = "none";
});

passwordElement.addEventListener("input", function () {
    hiddenPElement.style.display = "none";
});

cpasswordElement.addEventListener("input", function () {
    hiddencPElement.style.display = "none";
});

function validate() {
    let validFirstName = validateNotEmpty(fnameElement.value);
    let validLastName = validateNotEmpty(lnameElement.value);
    let validAddressLine = validateNotEmpty(inputAddressElement.value);
    let validCity = validateNotEmpty(inputCityElement.value);
    let validState = validateState(inputStateElement.value);
    let validZipCode = validateZipCode(inputZipElement.value);
    let validEmail = validateEmail(emailElement.value);
    let validPhone = validatePhone(phoneElement.value);
    let validPassword = validatePassword(passwordElement.value, cpasswordElement.value);
    if (validFirstName && validLastName && validAddressLine && validCity && validState && validZipCode && validEmail && validPhone && validPassword) {
        alert("Welcome, " + fnameElement.value + " " + lnameElement.value + "! \nYour account has been created!");
    }
    else if (!validFirstName && !validLastName && !validAddressLine && !validCity && !validState && !validZipCode && !validEmail && !validPhone && !validPassword) {
        repeatForm();
    }
    else if (!validFirstName) {
        repeatFirstName();
    }
    else if (!validLastName) {
        repeatLastName();
    }
    else if (!validAddressLine) {
        repeatAddressLine();
    }
    else if (!validCity) {
        repeatCity();
    }
    else if (!validState) {
        repeatState();
    }
    else if (!validZipCode) {
        repeatZipCode();
    }
    else if (!validEmail) {
        repeatEmail();
    }
    else if (!validPhone) {
        repeatPhone();
    }
    else if (!validPassword) {
        repeatPassword();
    }
    else {
        console.log("Now what?");
    }
}

function validateNotEmpty(userinput) {
    if (/[\w\d]/.test(userinput)) {
        return true;
    }
    else {
        return false;
    }
}

function validateState(userinput) {
    if (userinput == "Select your state") {
        return false;
    }
    else {
        return true;
    }
}

function validateZipCode(userinput) {
    if (/\d{5}/.test(userinput)) {
        return true;
    }
    else {
        return false;
    }
}

function validatePhone(userinput) {
    if (/\d{3}-\d{3}-\d{4}/.test(userinput)) {
        return true;
    }
    else {
        return false;
    }
}

function repeatForm() {
    hiddenFNElement.style.display = "block";
    hiddenFNElement.textContent = "Please enter your first name here.";
    hiddenLNElement.style.display = "block";
    hiddenLNElement.textContent = "Please enter your last name here.";
    hiddenIAElement.style.display = "block";
    hiddenIAElement.textContent = "Please enter your address here.";
    hiddenICElement.style.display = "block";
    hiddenICElement.textContent = "Please enter your city here.";
    hiddenISElement.style.display = "block";
    hiddenISElement.textContent = "Please select your state.";
    hiddenIZElement.style.display = "block";
    hiddenIZElement.textContent = "Please enter your zip code here.";
    hiddenPhElement.style.display = "block";
    hiddenPhElement.textContent = "Please enter your phone number here.";
    hiddenEElement.style.display = "block";
    hiddenEElement.textContent = "Please enter your email here.";
    hiddenPElement.style.display = "block";
    fnameElement.focus();
}

function repeatFirstName() {
    hiddenFNElement.style.display = "block";
    hiddenFNElement.textContent = "Please enter your first name here.";
    fnameElement.focus();
}

function repeatLastName() {
    hiddenLNElement.style.display = "block";
    hiddenLNElement.textContent = "Please enter your last name here.";
    lnameElement.focus();
}

function repeatAddressLine() {
    hiddenIAElement.style.display = "block";
    hiddenIAElement.textContent = "Please enter your address here.";
    inputAddressElement.focus();
}

function repeatCity() {
    hiddenICElement.style.display = "block";
    hiddenICElement.textContent = "Please enter your city here.";
    inputCityElement.focus();
}

function repeatState() {
    hiddenISElement.style.display = "block";
    hiddenISElement.textContent = "Please select your state.";
    inputStateElement.focus();
}

function repeatZipCode() {
    hiddenIZElement.style.display = "block";
    hiddenIZElement.textContent = "Please enter your zip code here.";
    inputZipElement.focus();
}

function repeatPhone() {
    hiddenPhElement.style.display = "block";
    hiddenPhElement.textContent = "Please enter your phone number here.";
    phoneElement.value = "";
    phoneElement.placeholder = "xxx-xxx-xxxx";
    phoneElement.focus();
}

function repeatEmail() {
    hiddenEElement.style.display = "block";
    hiddenEElement.textContent = "Please enter your email here.";
    emailElement.focus();
}

function repeatPassword() {
    hiddenPElement.style.display = "block";
    passwordElement.focus();
}

function validateEmail(emailstr) {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(emailstr)) {
        return true;
    }
    else {
        return false;
    }
}

function validatePassword(fPassword, fcPassword) {
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
    else if (fPassword != fcPassword) {
        hiddencPElement.textContent = "Passwords must match.";
        hiddenPElement.textContent = "Passwords must match.";
        return false;
    }
    return true;
}*/