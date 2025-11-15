<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile - Cinema</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background: #f0f0f0;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        h2 {
            color: #007bff;
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
            margin-top: 30px;
        }
        .info {
            margin: 20px 0;
        }
        .info p {
            font-size: 18px;
            margin: 10px 0;
        }
        .info strong {
            color: #007bff;
        }
        .history-list {
            list-style: none;
            padding: 0;
        }
        .history-list li {
            padding: 10px;
            margin: 5px 0;
            background: #f8f9fa;
            border-left: 4px solid #007bff;
        }
        .image-list {
            list-style: none;
            padding: 0;
        }
        .image-list li {
            padding: 10px;
            margin: 5px 0;
            background: #f8f9fa;
        }
        .image-list a {
            color: #007bff;
            text-decoration: none;
        }
        .image-list a:hover {
            text-decoration: underline;
        }
        .upload-form {
            margin: 20px 0;
            padding: 20px;
            background: #f8f9fa;
            border-radius: 4px;
        }
        .upload-form input[type="file"] {
            margin: 10px 0;
        }
        .upload-form button {
            padding: 10px 20px;
            background: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .upload-form button:hover {
            background: #218838;
        }
        .logout {
            text-align: center;
            margin-top: 30px;
        }
        .logout a {
            display: inline-block;
            padding: 10px 20px;
            background: #dc3545;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .logout a:hover {
            background: #c82333;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🎬 Profile</h1>
        
        <!-- User Info -->
        <div class="info">
            <p><strong>First Name:</strong> ${user.firstName}</p>
            <p><strong>Last Name:</strong> ${user.lastName}</p>
            <p><strong>Email:</strong> ${user.email}</p>
        </div>


    <!-- Login History -->
    <h2>Login History</h2>
    <c:choose>
        <c:when test="${not empty loginHistory}">
            <ul class="history-list">
                <c:forEach items="${loginHistory}" var="login">
                    <li>
                        ${login.formattedLoginTime} - IP: ${login.ipAddress}
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>No login history yet.</p>
        </c:otherwise>
    </c:choose>
    
        <!-- Image Upload -->
        <h2>Upload Avatar</h2>
        <div class="upload-form">
            <form method="POST" action="${pageContext.request.contextPath}/images" enctype="multipart/form-data">
                <input type="file" name="avatar" accept="image/*" required>
                <button type="submit">Upload Image</button>
            </form>
        </div>

        <!-- Uploaded Images -->
        <h2>My Images</h2>
        <c:choose>
            <c:when test="${not empty userImages}">
                <ul class="image-list">
                    <c:forEach items="${userImages}" var="image">
                        <li>
                            <a href="${pageContext.request.contextPath}/images/${image.fileName}" target="_blank">
                                ${image.originalName}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p>No images uploaded yet.</p>
            </c:otherwise>
        </c:choose>

        <!-- Logout Button -->
        <div class="logout">
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </div>
</body>
</html>