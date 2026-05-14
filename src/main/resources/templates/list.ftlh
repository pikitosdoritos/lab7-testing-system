<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Students List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #f0f2f5; }
        .card { border: none; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.08); }
        .table thead { background: linear-gradient(135deg, #1a1a2e, #16213e); color: #fff; }
        .btn-primary { background: linear-gradient(135deg, #7c83fd, #5c6bc0); border: none; }
        .btn-primary:hover { opacity: 0.9; }
        .badge-age { background: #7c83fd; color: #fff; padding: 3px 10px; border-radius: 20px; font-size: .85em; }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">
<#include "parts/navbar.ftlh">
<main class="flex-grow-1">
<div class="container my-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="fw-bold" style="color:#1a1a2e;">All Students</h1>
        <a href="/students-view/new" class="btn btn-primary px-4">+ Add Student</a>
    </div>
    <div class="card">
        <div class="card-body p-0">
            <#if students?has_content>
            <table class="table table-hover mb-0">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th class="text-end">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <#list students as s>
                    <tr>
                        <td class="align-middle text-muted">${s.id}</td>
                        <td class="align-middle fw-semibold">${s.name}</td>
                        <td class="align-middle"><span class="badge-age">${s.age}</span></td>
                        <td class="align-middle text-end">
                            <a href="/students-view/${s.id}" class="btn btn-sm btn-outline-secondary me-1">View</a>
                            <a href="/students-view/${s.id}/edit" class="btn btn-sm btn-outline-primary me-1">Edit</a>
                            <form action="/students-view/${s.id}/delete" method="post" class="d-inline">
                                <button type="submit" class="btn btn-sm btn-outline-danger"
                                        onclick="return confirm('Delete student ${s.name}?')">Delete</button>
                            </form>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
            <#else>
            <div class="text-center py-5 text-muted">
                <p class="fs-5">No students found.</p>
                <a href="/students-view/new" class="btn btn-primary">Add First Student</a>
            </div>
            </#if>
        </div>
    </div>
</div>
</main>
<#include "parts/footer.ftlh">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
