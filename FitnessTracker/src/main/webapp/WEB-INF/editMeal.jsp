<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fitness Tracker</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
   <h1 class ="navbar-brand">Make it a Great day ${user.userName}</h1>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/home">+ Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/new/meal">+ Add a Workout</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/logout">Logout</a>
        </li>
        </ul>
    </div>
  </div>
</nav>
<form:form action="/edit/meal/${newMeal.id}" method="post" class ="form-group" modelAttribute="newMeal">
		<div class="mb-3">
		<form:label path = "workout" class= "form-label">Select Date</form:label>
		<form:errors path="workout" class="text-danger"/><br>
		<form:select path="workout" class= "form-select">
		<c:forEach var="day" items="${days}">
		<c:if test="${user.id == day.user.id }">
		<form:option value="${day.id}"><fmt:formatDate value="${day.createdAt}" pattern="yyyy-MM-dd"/></</form:option>
		</c:if>
		</c:forEach>
		</form:select>
		</div>
		<div class="mb-3">
        	<form:label path="RecipeName" class="form-label">Recipe Name:</form:label>
		    <form:errors path="RecipeName" class="text-danger"/><br>
             <form:input type="text" class="form-control" placeholder="Grilled Cheese" path="RecipeName"/>
    	</div>
    	<div class="mb-3">
        	<form:label path="calories" class="form-label">Calories</form:label>
		    <form:errors path="calories" class="text-danger"/><br>
             <form:input type="number" class="form-control" placeholder="400" path="calories"/>
    	</div>
    	<div class="mb-3">
        	<label for="ingredient" class="form-label">Ingredients:</label>
			<input type = "text" class="form-control" placeholder="Bread" name="ingredient"/>
    	</div>
    	<div class="mb-3">
        	<form:label path="description" class="form-label">Description:</form:label>
		    <form:errors path="description" class="text-danger"/><br>
             <form:textarea  class="form-control" placeholder="A truly Time-less classic" path="description"/>
    	</div>
    	<form:input type = "hidden" path = "user" value="${ user.id}"/>
    	<button class = "btn  btn-outline-primary"> Update Meal</button>
	</form:form>
	
	<h5>Ingredients:</h5>
        	<ul>
        	<c:forEach var = "i"  items = "${newMeal.ingredients }"> <li>${i.ingredientName}</li> </c:forEach>
        	</ul>
</body>
</html>