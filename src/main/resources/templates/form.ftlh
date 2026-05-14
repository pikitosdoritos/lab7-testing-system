<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><#if student.id??>Edit Student<#else>New Student</#if></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #f0f2f5; }
        .card { border: none; border-radius: 16px; box-shadow: 0 4px 24px rgba(0,0,0,0.10); }
        .btn-primary { background: linear-gradient(135deg, #7c83fd, #5c6bc0); border: none; }
        .form-control:focus { border-color: #7c83fd; box-shadow: 0 0 0 .2rem rgba(124,131,253,.25); }
        .form-label { font-weight: 600; color: #1a1a2e; }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">
<#include "parts/navbar.ftlh">
<main class="flex-grow-1">
<div class="container my-5" style="max-width:520px;">
    <div class="card p-4">
        <h1 class="fw-bold mb-4" style="color:#1a1a2e;">
            <#if student.id??> Edit Student<#else>New Student</#if>
        </h1>
        <#if student.id??>
        <form action="/students-view/${student.id}" method="post">
        <#else>
        <form action="/students-view" method="post">
        </#if>
            <div class="mb-3">
                <label for="name" class="form-label">Full Name</label>
                <input type="text" id="name" name="name" class="form-control"
                       value="<#if student.name??>${student.name}</#if>"
                       placeholder="Enter student name" required>
            </div>
            <div class="mb-4">
                <label for="age" class="form-label">Age</label>
                <input type="number" id="age" name="age" class="form-control"
                       value="<#if student.age != 0>${student.age}</#if>"
                       placeholder="Enter age" min="1" required>
            </div>
            <div class="d-flex gap-2">
                <button type="submit" class="btn btn-primary px-4">
                    <#if student.id??>Save Changes<#else>Create Student</#if>
                </button>
                <a href="/students-view" class="btn btn-outline-secondary px-4">Cancel</a>
            </div>
        </form>
    </div>
</div>
</main>
<#include "parts/footer.ftlh">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
