//Filter checkbox implementation
$('#checkNameDesc').click(function () {
    $('#checkNameAsc, #checkCategoryDesc, #checkCategoryAsc, #checkRatingDesc, #checkRatingAsc, #checkPriceDesc, #checkPriceAsc').prop('checked', false);
});
$('#checkNameAsc').click(function () {
    $('#checkNameDesc, #checkCategoryDesc, #checkCategoryAsc, #checkRatingDesc, #checkRatingAsc, #checkPriceDesc, #checkPriceAsc').prop('checked', false);
});
$('#checkCategoryDesc').click(function () {
    $('#checkNameDesc, #checkNameAsc, #checkCategoryAsc, #checkRatingDesc, #checkRatingAsc, #checkPriceDesc, #checkPriceAsc').prop('checked', false);
});
$('#checkCategoryAsc').click(function () {
    $('#checkNameDesc, #checkNameAsc, #checkCategoryDesc, #checkRatingDesc, #checkRatingAsc, #checkPriceDesc, #checkPriceAsc').prop('checked', false);
});
$('#checkRatingDesc').click(function () {
    $('#checkNameDesc, #checkNameAsc, #checkCategoryDesc, #checkCategoryAsc, #checkRatingAsc, #checkPriceDesc, #checkPriceAsc').prop('checked', false);
});
$('#checkRatingAsc').click(function () {
    $('#checkNameDesc, #checkNameAsc, #checkCategoryDesc, #checkCategoryAsc, #checkRatingDesc, #checkPriceDesc, #checkPriceAsc').prop('checked', false);
});
$('#checkPriceDesc').click(function () {
    $('#checkNameDesc, #checkNameAsc, #checkCategoryDesc, #checkCategoryAsc, #checkRatingDesc, #checkRatingAsc, #checkPriceAsc').prop('checked', false);
});
$('#checkPriceAsc').click(function () {
    $('#checkNameDesc, #checkNameAsc, #checkCategoryDesc, #checkCategoryAsc, #checkRatingDesc, #checkRatingAsc, #checkPriceDesc').prop('checked', false);
});
$('#clear').click(function () {
    $('#checkNameDesc, #checkNameAsc, #checkCategoryDesc, #checkCategoryAsc, #checkRatingDesc, #checkRatingAsc, #checkPriceDesc, #checkPriceAsc').prop('checked', false);
});

//View button realization
$(document).on("click", "#view", function () {
    if ($('#checkNameDesc').is(':checked')){
        $.get('/get_by_name?nameSort=-1&offset=0', function (json) {
            showTable(json, 0);
            pageBar();
        });
    } else if ($('#checkNameAsc').is(':checked')){
        $.get('/get_by_name?nameSort=1&offset=0', function (json) {
            showTable(json, 0);
            pageBar();
        });
    } else if ($('#checkCategoryDesc').is(':checked')){
        $.get('/get_by_category?categorySort=-1&offset=0', function (json) {
            showTable(json, 0);
            pageBar();
        });
    } else if ($('#checkCategoryAsc').is(':checked')){
        $.get('/get_by_category?categorySort=1&offset=0', function (json) {
            showTable(json, 0);
            pageBar();
        });
    } else if ($('#checkRatingDesc').is(':checked')){
        $.get('/get_by_rating?ratingSort=-1&offset=0', function (json) {
            showTable(json, 0);
            pageBar();
        });
    } else if ($('#checkRatingAsc').is(':checked')){
        $.get('/get_by_rating?ratingSort=1&offset=0', function (json) {
            showTable(json, 0);
            pageBar();
        });
    } else if ($('#checkPriceDesc').is(':checked')){
        $.get('/get_by_price?priceSort=-1&offset=0', function (json) {
            showTable(json, 0);
            pageBar();
        });
    } else if ($('#checkPriceAsc').is(':checked')){
        $.get('/get_by_price?priceSort=1&offset=0', function (json) {
            showTable(json, 0);
            pageBar();
        });
    } else {
        $.get('/get_table?offset=0', function (json) {
            showTable(json, 0);
            pageBar();
        });
    }
});