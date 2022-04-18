// let addUserButton = document.getElementById("addUser");
// addUserButton.addEventListener("click", goAddUserPage);

function goAddUserPage() {
    location.assign("../user/adduser");
}

let searchText = document.getElementById("search");

let searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", goSearchResults);

function goSearchResults() {
    let searchBy = document.getElementById("searchBy").value;
    if (searchBy == "First Name") {
        let fullURL = "../user/searchuserbyfirstname?searchFN=" + searchText.value;
        location.assign(fullURL);
    }
    else if (searchBy == "Last Name") {
        let fullURL = "../user/searchuserbylastname?searchLN=" + searchText.value;
        location.assign(fullURL);
    }
    else if (searchBy == "Email") {
        let fullURL = "../user/searchuserbyemail?searchEmail=" + searchText.value;
        location.assign(fullURL);
    }
}

// This is with button class = editUser, table id = myTable, title column class = titlecol
$("#myTable").on('click', '.editUser', function() {
    var self = $(this).closest("tr");
    var emailvalue = self.find(".emailcol").text();
    location.assign("../user/edit?email=" + emailvalue);
});

$("#myTable").on('click', '.deleteUser', function() {
    var self = $(this).closest("tr");
    var emailvalue = self.find(".emailcol").text();
    location.assign("../user/delete?email=" + emailvalue);
});
