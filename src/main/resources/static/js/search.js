let searchText = document.getElementById("search");
/*
let searchTButton = document.getElementById("searchT");
searchTButton.addEventListener("click", goSearchResultsByTitlePage);

let searchAButton = document.getElementById("searchA");
searchAButton.addEventListener("click", goSearchResultsByAuthorPage);

let searchCButton = document.getElementById("searchC");
searchCButton.addEventListener("click", goSearchResultsByCategoryPage);

function goSearchResultsByTitlePage() {
    let fullURL = "../catalog/searchtitle?searchT=" + searchText.value;
    location.assign(fullURL);
}

function goSearchResultsByAuthorPage() {
    let fullURL = "../catalog/searchauthor?searchA=" + searchText.value;
    location.assign(fullURL);
}

function goSearchResultsByCategoryPage() {
    let fullURL = "../catalog/searchcategory?searchC=" + searchText.value;
    location.assign(fullURL);
}
*/
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