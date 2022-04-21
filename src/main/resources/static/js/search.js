let searchText = document.getElementById("search");
let searchButton = document.getElementById("searchButton");

searchButton.addEventListener("click", goSearchResults);

function goSearchResults() {
    let searchBy = document.getElementById("searchBy").value;
    if (searchBy == "Author") {
        let fullURL = "../catalog/searchauthor?searchA=" + encodeURIComponent(searchText.value);
        location.assign(fullURL);
    }
    else if (searchBy == "Title") {
        let fullURL = "../catalog/searchtitle?searchT=" + encodeURIComponent(searchText.value);
        location.assign(fullURL);
    }
    else if (searchBy == "Category") {
        let fullURL = "../catalog/searchcategory?searchC=" + encodeURIComponent(searchText.value);
        location.assign(fullURL);
    }
}