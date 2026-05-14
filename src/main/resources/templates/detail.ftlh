<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #f0f2f5; }
        .card { border: none; border-radius: 16px; box-shadow: 0 4px 24px rgba(0,0,0,0.10); }
        .detail-label { color: #888; font-size: .85em; text-transform: uppercase; letter-spacing: .05em; }
        .avatar { width: 80px; height: 80px; border-radius: 50%; background: linear-gradient(135deg, #7c83fd, #5c6bc0);
                  display: flex; align-items: center; justify-content: center; font-size: 2rem; color: #fff; }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">
<#include "parts/navbar.ftlh">
<main class="flex-grow-1">
<div class="container my-5" style="max-width:600px;">
    <div class="card p-4">
        <div class="d-flex align-items-center mb-4 gap-3">
            <div class="avatar">${student.name?substring(0,1)?upper_case}</div>
            <div>
                <h1 class="fw-bold mb-0" style="color:#1a1a2e;">${student.name}</h1>
                <span class="text-muted">ID: ${student.id}</span>
            </div>
        </div>
        <div class="mb-3">
            <div class="detail-label">Age</div>
            <div class="fs-5 fw-semibold">${student.age}</div>
        </div>
        <div class="d-flex gap-2 mt-4">
            <a href="/students-view/${student.id}/edit" class="btn btn-primary px-4">Edit</a>
            <a href="/students-view" class="btn btn-outline-secondary px-4">Back</a>
            <a href="/students-view/${student.id}/delete" class="btn btn-outline-danger ms-auto">Delete</a>
        </div>
    </div>
</div>
</main>
<#include "parts/footer.ftlh">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
