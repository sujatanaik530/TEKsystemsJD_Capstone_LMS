let searchText = document.getElementById("search");

let searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", goSearchResults);

function goSearchResults() {
    let searchBy = document.getElementById("searchBy").value;
    if (searchBy == "Checked Out By User") {
        let fullURL = "../catalog/checkedoutbyuser?search=" + encodeURIComponent(searchText.value);
        location.assign(fullURL);
    }
    else if (searchBy == "Available By Title") {
        let fullURL = "../catalog/availabletitles?search=" + encodeURIComponent(searchText.value);
        location.assign(fullURL);
    }
    else if (searchBy == "Checked Out By Title") {
        let fullURL = "../catalog/checkedouttitles?search=" + encodeURIComponent(searchText.value);
        location.assign(fullURL);
    }
    else if (searchBy == "Available By Author") {
        let fullURL = "../catalog/availableauthors?search=" + encodeURIComponent(searchText.value);
        location.assign(fullURL);
    }
    else if (searchBy == "Checked Out By Author") {
        let fullURL = "../catalog/checkedoutauthors?search=" + encodeURIComponent(searchText.value);
        location.assign(fullURL);
    }
}
