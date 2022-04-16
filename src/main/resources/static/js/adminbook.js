let addBookButton = document.getElementById("addBook");
addBookButton.addEventListener("click", goAddBookPage);

function goAddBookPage() {
    location.assign("../book/addbook");
}

let searchText = document.getElementById("search");

let searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", goSearchResults);

function goSearchResults() {
    let searchBy = document.getElementById("searchBy").value;
    if (searchBy == "Author") {
        let fullURL = "../admin/searchauthor?searchA=" + searchText.value;
        location.assign(fullURL);
    }
    else if (searchBy == "Title") {
        let fullURL = "../admin/searchtitle?searchT=" + searchText.value;
        location.assign(fullURL);
    }
    else if (searchBy == "Category") {
        let fullURL = "../admin/searchcategory?searchC=" + searchText.value;
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
