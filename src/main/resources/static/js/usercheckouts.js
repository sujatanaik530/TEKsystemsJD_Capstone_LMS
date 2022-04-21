// This is with button class = returnbook, table id = myTable, title column class = titlecol
$("#myTable").on('click', '.returnbook', function() {
    var self = $(this).closest("tr");
    var titlevalue = self.find(".titlecol").text();
    console.log("\nReturn Title = " + titlevalue);
    location.assign("../user/return?title=" + encodeURIComponent(titlevalue));
});

// This is with button class = renewbook, table id = myTable, title column class = titlecol
$("#myTable").on('click', '.renewbook', function() {
    var self = $(this).closest("tr");
    var titlevalue = self.find(".titlecol").text();
    console.log("\nRenew Title = " + titlevalue);
    location.assign("../user/renew?title=" + encodeURIComponent(titlevalue));
});