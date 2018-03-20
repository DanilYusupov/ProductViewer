//Show table
function showTable(json, offset){
    var table = document.getElementById('tableBody');
    while (table.firstChild) {
        table.removeChild(table.firstChild);
    }
    $.each(json, function (index, item) {
        var $tr = $('<tr>').appendTo(table);
        $("<td>").text(offset + index + 1).appendTo($tr);
        $("<td>").text(item['name']).appendTo($tr);
        $("<td>").text(item['category']).appendTo($tr);
        $("<td>").text(item['rating']).appendTo($tr);
        $("<td>").text(item['price']).appendTo($tr);
    });
}

//Creating table by page number "n"
function getPage(n){
    var offset = (n - 1) * 10;
    var url = "/get_table?offset=" + offset;
    $.get(url, function (json) {
       showTable(json, offset);
    });
}

//Creating pagination bar
// function getPaginationBar(){
//
// }

//Initialization home
$(document).ready(function () {
    getPage(1);
    $.get("/get_total", function (json) {
        var count = json['count'] / 10;
        if (json['count'] % 10 > 0){
            count += 1;
        }
        var $ul = document.getElementById('pagesBar');
        for (var i = 1; i < count + 1; i++){
            var $li = $("<li>").attr('class', 'page-item').appendTo($ul);
            $("<button>").attr('class', 'page-link').attr('onclick', 'getPage(' + i + ')').text(i).appendTo($li);
        }
    });
});

//Price filter
$(document).on("click", "#priceFilterDesc", function () {
    $.get("/get_by_price?priceSort=-1&offset=0", function (json) {
        showTable(json, 0);
    });
});

$(document).on("click", "#priceFilterAsc", function () {
    $.get("/get_by_price?priceSort=1&offset=0", function (json) {
        showTable(json, 0);
    });
});