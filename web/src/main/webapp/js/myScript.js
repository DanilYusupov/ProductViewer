//Initialization table
$(document).ready(function () {
    $.get("/get_table?offest=0", function (json) {
        //TODO: get stuck...
        var table = document.getElementById('tableBody');
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }
    });
});