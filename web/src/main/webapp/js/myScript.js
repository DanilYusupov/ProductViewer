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
    pageBar();
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
function pageBar(){
    $.get("/get_total", function (json) {
        var count = json['count'] / 10;
        var $ul = document.getElementById('pagesBar');
        while ($ul.firstChild){
            $ul.removeChild($ul.firstChild);
        }
        for (var i = 1; i < count + 1; i++){
            var $li = $("<li>").attr('class', 'page-item').appendTo($ul);
            $("<button>").attr('class', 'page-link').attr('onclick', 'getPage(' + i + ')').text(i).appendTo($li);
        }
    });
}

//Initialization home
$(document).ready(function () {
    getPage(1);
    pageBar();
});


//Create modal
$(document).on("click", "#create", function(){
    $('#modal').modal('show');
});

//Create item
$(document).on("click", "#createItem", function(){
    var name = document.getElementById('addName').value;
    var category = document.getElementById('addCategory').value;
    var price = document.getElementById('addPrice').value;
    var url = "/create?name=" + name + "&category=" + category + "&price=" + price;
    $.post(url, function(message){
        alert(message);
    });
});