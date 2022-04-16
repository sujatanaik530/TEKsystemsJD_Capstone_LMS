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
    else if (searchBy == "Available Authors") {
        let fullURL = "../catalog/availableauthors?search=" + searchText.value;
        location.assign(fullURL);
    }
    else if (searchBy == "Checked Out Authors") {
        let fullURL = "../catalog/checkedoutauthors?search=" + searchText.value;
        location.assign(fullURL);
    }
}
