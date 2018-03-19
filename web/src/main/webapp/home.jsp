<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Viewer</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" media="screen"/>
</head>
<body>

<!-- Head -->

<div class="container">
    <div class="row">
        <h1 class="mx-4">Products</h1>
    </div>
</div>

<!-- Filter -->

<div class="container border">
    <div class="row">
        <div class="btn-toolbar mb-3 m-2" role="toolbar" aria-label="Toolbar with button groups">
            <h4 class="mx-4 m-1">Filter</h4>
            <div class="btn-group mr-2" role="group" aria-label="First group">
                <button id="nameFilter" type="button" class="btn btn-secondary">Name</button>
                <button id="categoryFilter" type="button" class="btn btn-secondary">Category</button>
                <button id="ratingFilter" type="button" class="btn btn-secondary">Rating</button>
                <button id="priceFilter" type="button" class="btn btn-secondary">Price</button>
            </div>
        </div>
    </div>
</div>

<!-- Create button -->

<div class="container p-1">
    <div class="row justify-content-end">
        <div class="col-auto mt-1">
            <button type="button" class="btn btn-primary">+ create</button>
        </div>
    </div>
</div>

<!-- Table -->

<div class="container p-1">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Rating</th>
            <th scope="col">Price</th>
        </tr>
        </thead>
        <tbody id="tableBody">

        </tbody>
    </table>
</div>

<!-- Pagination -->

<div class="container p-1">
    <div class="row justify-content-center">
        <nav aria-label="...">
            <ul class="pagination">
                <li class="page-item disabled">
                    <span class="page-link">Previous</span>
                </li>
                <li class="page-item active">
                    <span class="page-link">1<span class="sr-only">(current)</span></span>
                </li>
                <% int count = (int) request.getAttribute("count");
                   int pages = (count % 10 > 0) ? (1 + count / 10) : (count / 10);
                    for (int i = 0; i < pages - 1; i++){ %>
                <li class="page-item"><button id="page<%=i + 2%>" class="page-link" value=<%=i + 2%> ></button></li>
                <% } %>
                <li class="page-item">
                    <button id="nextPage" class="page-link">#">Next</button>
                </li>
            </ul>
        </nav>
    </div>
</div>

<script src="js/jquery-3.3.1.slim.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/myScript.js"></script>
</body>
</html>