//Initialization table
$(document).ready(function () {
    $.get("/get_table?offset=0", function (json) {
        var table = document.getElementById('tableBody');
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }
        $.each(json, function (index, item) {
            var $tr = $('<tr>').appendTo(table);
            $("<td>").text(index + 1).appendTo($tr);
            $("<td>").text(item['name']).appendTo($tr);
            $("<td>").text(item['category']).appendTo($tr);
            $("<td>").text(item['rating']).appendTo($tr);
            $("<td>").text(item['price']).appendTo($tr);
        });
    });
    $.get("/get_total", function (json) {
        var count = json['count'] / 10;
        if (json['count'] % 10 > 0){
            count += 1;
        }
        var $ul = document.getElementById('pagesBar');
        for (var i = 1; i < count + 1; i++){
            var $li = $("<li>").attr('class', 'page-item').appendTo($ul);
            $("<button>").attr('class', 'page-link').attr('id', 'page' + i).text(i).appendTo($li);
        }
    });
});