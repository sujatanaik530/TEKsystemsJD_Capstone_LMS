let searchText = document.getElementById("search");

let searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", goSearchResults);

function goSearchResults() {
    let searchBy = document.getElementById("searchBy").value;
    if (searchBy == "Author") {
        let fullURL = "../catalog/searchauthor?searchA=" + searchText.value;
        location.assign(fullURL);
    }
    else if (searchBy == "Title") {
        let fullURL = "../catalog/searchtitle?searchT=" + searchText.value;
        location.assign(fullURL);
    }
    else if (searchBy == "Category") {
        let fullURL = "../catalog/searchcategory?searchC=" + searchText.value;
        location.assign(fullURL);
    }
}

// This is with button class = checkoutbook, table id = myTable, title column class = titlecol
$("#myTable").on('click', '.checkoutbook', function() {
    var self = $(this).closest("tr");
    var titlevalue = self.find(".titlecol").text();
    console.log("\nSearch Title = " + titlevalue);
    location.assign("../user/checkout/" + titlevalue);
});

