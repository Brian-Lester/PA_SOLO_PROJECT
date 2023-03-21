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
   <h1 class ="navbar-brand"><fmt:formatDate value="${workout.createdAt}" pattern="yyyy-MM-dd" /> Details</h1>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <div class = "btn-group">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="btn btn-outline-primary" aria-current="page" href="/new/workout">+ Add a Workout</a>
        </li>
        <li class="nav-item">
          <a class="btn btn-outline-primary" aria-current="page" href="/new/meal">+ Add a Meal</a>
        </li>
        <li class="nav-item">
          <a class="btn btn-outline-primary" href="/home">Home</a>
        </li>
        </ul>
        </div>
    </div>
  </div>
</nav>
<table class="table table-striped">
	<thead>
		<tr>
			<th scope="col">Excercise:</th>
			<th scope="col">Reps</th>
			<th scope="col">
			Weight
			</th >
			<th scope="col">
			Sets
			</th>
			<th scope="col">
			Excercise Type
			</th>
			<th>
			Actions
			</th>
		</tr>
	</thead>
	<tbody>
			<c:forEach var="workout" items="${workout.excercises}" varStatus="loop">
				

		<tr>
				<td><c:out value="${workout.excerciseName}"></c:out></td>
				<td><c:out value="${workout.rep}"></c:out></td>
				<td><c:out value="${workout.weight}"></c:out></td>
				<td><c:out value="${workout.sets}"></c:out></td>
				<td><c:out value="${workout.excerciseType}"></c:out></td>
				<td><div class="btn-group"><a href="/edit/wokrout/${workout.workout.id}" class="btn btn-outline-danger">Edit Workout</a>
<a href="/delete/wokrout/${workout.workout.id}" class="btn btn-outline-danger">Delete Workout</a></div></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<table class="table table-striped">
	<thead>
		<tr>
			<th>Recipe Name</th>
			<th scope="col">
			Ingredients
			</th >
			<th scope="col">
			Calories
			</th>
			<th scope="col">
			Actions:
			</th>
		</tr>
	</thead>
	<tbody>
			<c:forEach var="meal" items="${workout.meals}" varStatus="loop">
				

		<tr>
				<td><c:out value="${meal.recipeName}"></c:out></td>
				<td>
				<ul>
				<c:forEach var="i" items = "${meal.ingredients}">
				<li><c:out value="${i.ingredientName}"></c:out></li>
				</c:forEach>
				</ul>
				</td>
				<td><c:out value="${meal.calories}"></c:out></td>
				<td><div class = "btn-group"><a href="/edit/meal/${meal.id}" class="btn btn-outline-danger">Edit Meal</a>
<a href="/delete/meal/${meal.id}" class="btn btn-outline-danger">Delete Meal</a></div></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a href="/logout" class="btn btn-outline-danger">Logout</a>

</body>
</html>