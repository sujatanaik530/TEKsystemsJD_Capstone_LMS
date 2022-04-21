let searchText = document.getElementById("search");

let searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", goSearchResults);

function goSearchResults() {
    let searchBy = document.getElementById("searchBy").value;
    if (searchBy == "First Name") {
        let fullURL = "../user/searchuserbyfirstname?searchFN=" + encodeURIComponent(searchText.value);
        location.assign(fullURL);
    }
    else if (searchBy == "Last Name") {
        let fullURL = "../user/searchuserbylastname?searchLN=" + encodeURIComponent(searchText.value);
        location.assign(fullURL);
    }
    else if (searchBy == "Email") {
        let fullURL = "../user/searchuserbyemail?searchEmail=" + encodeURIComponent(searchText.value);
        location.assign(fullURL);
    }
}

// This is with button class = editUser, table id = myTable, title column class = emailvalue
$("#myTable").on('click', '.editUser', function() {
    var self = $(this).closest("tr");
    var emailvalue = self.find(".emailcol").text();
    location.assign("../user/edit?email=" + encodeURIComponent(emailvalue));
});

// This is with button class = deleteUser, table id = myTable, title column class = emailvalue
$("#myTable").on('click', '.deleteUser', function() {
    var self = $(this).closest("tr");
    var emailvalue = self.find(".emailcol").text();
    location.assign("../user/delete?email=" + encodeURIComponent(emailvalue));
});
