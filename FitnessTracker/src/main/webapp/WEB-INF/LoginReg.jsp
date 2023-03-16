<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css">

</head>
<body>
<h1>Welcome to FitnessTracker! Start Your journey today!</h1>
    <div class="container">
        <div class="reg">
            <h2>Register</h2>
            <form:form action="/register" method="post" class="form-group"  modelAttribute="newUser">
                <div class="mb-3">
                    <form:label path="userName" class="form-label">User Name:</form:label>
		            <form:errors path="userName" class="text-danger"/><br>
                    <form:input type="text" class="form-control" placeholder="Jon" path="userName"/>
                </div>
                <div class="mb-3">
                    <form:label path="email" class="form-label">Email</form:label>
					<form:errors path="email" class="text-danger"/><br>
                    <form:input type="email" class="form-control" placeholder="jonSnow@winterfell.com" path="email"/>
                </div>
                <div class="mb-3">
                    <form:label path="password" class="form-label">Password</form:label>
					<form:errors path="password" class="text-danger"/><br>
                    <form:input type="password" class="form-control" path="password"/>
                </div>
                <div class="mb-3">
                    <form:label path="confirm" class="form-label">Confirm Password</form:label>
					<form:errors path="confirm" class="text-danger"/><br>
                    <form:input type="password" class="form-control" path="confirm"/>
                </div>
                <button>Submit</button>
            </form:form>
        </div>
        <div class="login">
            <h2>Login</h2>

            <form:form action="/login" method="post" class="form-group" modelAttribute="newLogin">
                <div class="mb-3">
                    <form:label path="email" class="form-label login">Email</form:label>
					<form:errors path="email" class="text-danger"/><br>
                    <form:input type="email" class="form-control" placeholder="jonSnow@winterfell.com" path="email"/>
                </div>
                <div class="mb-3">
                    <form:label path="password" class="form-label login">Password</form:label>
           	 		<form:errors path="password" class="text-danger"/><br>
                    <form:input type="password" class="form-control" path="password"/>
                </div>
                <button>Submit</button>
            </form:form>
        </div>
    </div>
</body>
</html>