let addBookButton = document.getElementById("addUser");
addBookButton.addEventListener("click", goAddUserPage);

function goAddUserPage() {
    location.assign("../user/adduser");
}

let searchText = document.getElementById("search");

let searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", goSearchResults);

function goSearchResults() {
    let searchBy = document.getElementById("searchBy").value;
    if (searchBy == "First Name") {
        let fullURL = "../admin/searchuserbyfirstname?searchFN=" + searchText.value;
        location.assign(fullURL);
    }
    else if (searchBy == "Last Name") {
        let fullURL = "../admin/searchuserbylastname?searchLN=" + searchText.value;
        location.assign(fullURL);
    }
    else if (searchBy == "Email") {
        let fullURL = "../admin/searchuserbyemail?searchEmail=" + searchText.value;
        location.assign(fullURL);
    }
}

// This is with button class = editBook, table id = myTable, title column class = titlecol
$("#myTable").on('click', '.editBook', function() {
    var self = $(this).closest("tr");
    var titlevalue = self.find(".titlecol").text();
    console.log("\nSearch Title = " + titlevalue);
    location.assign("../book/editbook?title=" + titlevalue);
});

$("#myTable").on('click', '.deleteBook', function() {
    var self = $(this).closest("tr");
    var titlevalue = self.find(".titlecol").text();
    console.log("\nSearch Title = " + titlevalue);
    location.assign("../book/deletebook?title=" + titlevalue);
});
