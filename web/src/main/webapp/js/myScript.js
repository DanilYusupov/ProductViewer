//Initialization home
$(document).ready(function () {
    getPage(1);
});

//Show table
function showTable(json, offset) {
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
        var td = $("<td>").attr('style', 'width: 150px').appendTo($tr);
        $("<button>").attr('class', 'btn btn-dark btn-sm').attr('onclick', 'updateItem(' + item['id'] + ')').text('update').appendTo(td);
        $("<button>").attr('class', 'btn btn-danger btn-sm').attr('onclick', 'deleteItem(' + item['id'] + ')').text('delete').appendTo(td);
    });
}

//Creating table by page number "page" & items per page size
function getPage(page) {
    var size = 10;
    var offset = (page - 1) * size;
    $.get("/get_total", function (result) {
        var totalCount = result.count;
        if ($('#checkNameDesc').is(':checked')) {
            $.get('/get_sorted?page=' + page + '&size=' + size + '&sortBy=name&sortDir=DESC', function (json) {
                showTable(json, offset);
                pageBar(totalCount, size);
            });
        } else if ($('#checkNameAsc').is(':checked')) {
            $.get('/get_sorted?page=' + page + '&size=' + size + '&sortBy=name&sortDir=ASC', function (json) {
                showTable(json, offset);
                pageBar(totalCount, size);
            });
        } else if ($('#checkCategoryDesc').is(':checked')) {
            $.get('/get_sorted?page=' + page + '&size=' + size + '&sortBy=category&sortDir=DESC', function (json) {
                showTable(json, offset);
                pageBar(totalCount, size);
            });
        } else if ($('#checkCategoryAsc').is(':checked')) {
            $.get('/get_sorted?page=' + page + '&size=' + size + '&sortBy=category&sortDir=ASC', function (json) {
                showTable(json, offset);
                pageBar(totalCount, size);
            });
        } else if ($('#checkRatingDesc').is(':checked')) {
            $.get('/get_sorted?page=' + page + '&size=' + size + '&sortBy=rating&sortDir=DESC', function (json) {
                showTable(json, offset);
                pageBar(totalCount, size);
            });
        } else if ($('#checkRatingAsc').is(':checked')) {
            $.get('/get_sorted?page=' + page + '&size=' + size + '&sortBy=rating&sortDir=ASC', function (json) {
                showTable(json, offset);
                pageBar(totalCount, size);
            });
        } else if ($('#checkPriceDesc').is(':checked')) {
            $.get('/get_sorted?page=' + page + '&size=' + size + '&sortBy=price&sortDir=DESC', function (json) {
                showTable(json, offset);
                pageBar(totalCount, size);
            });
        } else if ($('#checkPriceAsc').is(':checked')) {
            $.get('/get_sorted?page=' + page + '&size=' + size + '&sortBy=price&sortDir=ASC', function (json) {
                showTable(json, offset);
                pageBar(totalCount, size);
            });
        } else {
            $.get('/get_table?size=' + size + '&offset=' + offset, function (json) {
                showTable(json, offset);
                pageBar(totalCount, size);
            });
        }
    });
}

//Creating pagination bar
function pageBar(n, size) {
    var pages = n / size;
    var $ul = document.getElementById('pagesBar');
    while ($ul.firstChild) {
        $ul.removeChild($ul.firstChild);
    }
    for (var i = 1; i < pages + 1; i++) {
        var $li = $("<li>").attr('class', 'page-item').appendTo($ul);
        $("<button>").attr('class', 'page-link').attr('onclick', 'getPage(' + i + ')').text(i).appendTo($li);
    }
}

function pageBarSearch(count) {
    var size = conut / 10;
    var $ul = document.getElementById('pagesBar');
    while ($ul.firstChild) {
        $ul.removeChild($ul.firstChild);
    }
    for (var i = 1; i < count + 1; i++) {
        var $li = $("<li>").attr('class', 'page-item').appendTo($ul);
        $("<button>").attr('class', 'page-link').attr('onclick', 'getPage(' + i + ')').text(i).appendTo($li);
    }
}

//Create modal
$(document).on("click", "#create", function () {
    $('#createModal').modal('show');
});

//Create item
$(document).on("click", "#createItem", function () {
    var name = document.getElementById('addName').value;
    var category = document.getElementById('addCategory').value;
    var price = document.getElementById('addPrice').value;
    var url = "/create?name=" + name + "&category=" + category + "&price=" + price;
    $.post(url, function () {
        clearCreate();
        document.location.reload();
    });
});

//Clear create button
$(document).on('click', '#createClear', function () {
    clearCreate();
});

function clearCreate() {
    document.getElementById('addName').value = "";
    document.getElementById('addCategory').value = "";
    document.getElementById('addPrice').value = "";
}

//Open updating modal
function updateItem(id) {
    $('#updateModal').modal('show');
    $('#updateId').attr('value', id);
}

//Update item
$(document).on('click', '#updateItem', function () {
    var id = document.getElementById('updateId').value;
    var name = document.getElementById('newName').value;
    var category = document.getElementById('newCategory').value;
    var price = document.getElementById('newPrice').value;
    var rating = document.getElementById('newRating').value;
    var url = "/update?id=" + id + "&name=" + name + "&category=" + category + "&price=" + price + "&rating=" + rating;
    $.post(url, function () {
        $('#updateId').attr('value', '');
        clearUpdate();
        document.location.reload();
    });
});

//Clear update button
$(document).on('click', '#updateClear', function () {
    clearUpdate();
});

function clearUpdate() {
    document.getElementById('newName').value = "";
    document.getElementById('newCategory').value = "";
    document.getElementById('newPrice').value = "";
    document.getElementById('newRating').value = "";
}

//Deleting item
function deleteItem(id) {
    var url = '/delete?id=' + id;
    $.post(url, function () {
        document.location.reload();
    });
}

//Searching...
$(document).on('click', '#search', function () {
    var name = document.getElementById('searchName').value;
    var url = '/search?name=' + name + "&offset=0";
    $.get(url, function (json) {
        $('#searchName').val("");
        showTable(json, 0);
        $.get("/get_search_count?name=" + name, function (json) {
            searchPageBar(json['count'], name);
        });
    });
});

function getSearchPage(n, name) {
    var offset = (n - 1) * 10;
    $.get('/search?name=' + name + "&offset=" + offset, function (json) {
        showTable(json, offset);
    });
}

function searchPageBar(n, name) {
    var $ul = document.getElementById('pagesBar');
    var count = n / 10;
    while ($ul.firstChild) {
        $ul.removeChild($ul.firstChild);
    }
    for (var i = 1; i < count + 1; i++) {
        var $li = $("<li>").attr('class', 'page-item').appendTo($ul);
        $("<button>").attr('class', 'page-link').attr('onclick', 'getSearchPage(' + i + ', \'' + name + '\')').text(i).appendTo($li);
    }
}