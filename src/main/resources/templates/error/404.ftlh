<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Not Found</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #f0f2f5; }
        .error-code { font-size: 8rem; font-weight: 900; background: linear-gradient(135deg, #7c83fd, #5c6bc0);
                      -webkit-background-clip: text; -webkit-text-fill-color: transparent; line-height: 1; }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">
<#include "parts/navbar.ftlh">
<main class="flex-grow-1 d-flex align-items-center justify-content-center">
<div class="container text-center">
    <div class="error-code">404</div>
    <h2 class="fw-bold mt-3" style="color:#1a1a2e;">Student Not Found</h2>
    <p class="text-muted fs-5 mt-2">${message!"The requested resource could not be found."}</p>
    <a href="/students-view" class="btn btn-primary px-5 mt-3">Go Back to List</a>
</div>
</main>
<#include "parts/footer.ftlh">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
