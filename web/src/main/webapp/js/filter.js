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
    var page = 1;
    getPage(page);
});