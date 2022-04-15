let searchText = document.getElementById("search");

let searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", goSearchResults);

function goSearchResults() {
    let searchBy = document.getElementById("searchBy").value;
    if (searchBy == "Checked Out By User") {
        let fullURL = "../catalog/checkedoutbyuser?search=" + searchText.value;
        location.assign(fullURL);
    }
    else if (searchBy == "Available Titles") {
        let fullURL = "../catalog/availabletitles?search=" + searchText.value;
        location.assign(fullURL);
    }
    else if (searchBy == "Checked Out Titles") {
        let fullURL = "../catalog/checkedouttitles?search=" + searchText.value;
        location.assign(fullURL);
    }
}

//
// // This is with button class = checkoutbook, table id = myTable, title column class = titlecol
// $("#myTable").on('click', '.checkoutbook', function() {
//     var self = $(this).closest("tr");
//     var titlevalue = self.find(".titlecol").text();
//     console.log("\nSearch Title = " + titlevalue);
//     location.assign("../user/checkout?title=" + titlevalue);
// });
//
