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

//Creating table by page number "n" & sorting available
function getPage(n){
    var offset = (n - 1) * 10;
        if ($('#checkNameDesc').is(':checked')){
        $.get('/get_by_name?nameSort=-1&offset=' + offset, function (json) {
            showTable(json, 0);
        });
    } else if ($('#checkNameAsc').is(':checked')){
        $.get('/get_by_name?nameSort=1&offset=' + offset, function (json) {
            showTable(json, 0);
        });
    } else if ($('#checkCategoryDesc').is(':checked')){
        $.get('/get_by_category?categorySort=-1&offset=' + offset, function (json) {
            showTable(json, 0);
        });
    } else if ($('#checkCategoryAsc').is(':checked')){
        $.get('/get_by_category?categorySort=1&offset=' + offset, function (json) {
            showTable(json, 0);
        });
    } else if ($('#checkRatingDesc').is(':checked')){
        $.get('/get_by_rating?ratingSort=-1&offset=' + offset, function (json) {
            showTable(json, 0);
        });
    } else if ($('#checkRatingAsc').is(':checked')){
        $.get('/get_by_rating?ratingSort=1&offset=' + offset, function (json) {
            showTable(json, 0);
        });
    } else if ($('#checkPriceDesc').is(':checked')){
        $.get('/get_by_price?priceSort=-1&offset=' + offset, function (json) {
            showTable(json, 0);
        });
    } else if ($('#checkPriceAsc').is(':checked')){
        $.get('/get_by_price?priceSort=1&offset=' + offset, function (json) {
            showTable(json, 0);
        });
    } else {
        $.get("/get_table?offset=" + offset, function (json) {
            showTable(json, offset);
        });
    }
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