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
   <h1 class ="navbar-brand">Welcome back ${user.userName}</h1>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="btn btn-outline-primary" aria-current="page" href="/new/workout">+ Add a Workout</a>
        </li>
        <li class="nav-item">
          <a class="btn btn-outline-primary" aria-current="page" href="/new/meal">+ Add a Meal</a>
        </li>
        <li class="nav-item">
          <a class="btn btn-outline-danger" href="/logout">Logout</a>
        </li>
        </ul>
    </div>
  </div>
</nav>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Date</th>
			<th scope="col">
			Duration
			</th >
			<th scope="col">
			Split
			</th>
			<th scope="col">
			Calories
			</th>
			<th>
			Actions
			</th>
		</tr>
	</thead>
	<tbody>
			<c:forEach var="workout" items="${workouts}" varStatus="loop">
				

		<tr>
			
				<td><fmt:formatDate value="${workout.createdAt}" pattern="yyyy-MM-dd" /></td>
				<td><c:out value="${workout.totalTime}"></c:out> Minutes</td>
				<td><c:out value="${workout.excercises[0].excerciseType}"></c:out></td>
				<c:set var="total" value="${0}"/>
				<c:forEach var="meals" items="${workout.meals}">
    				<c:set var="total" value="${total + meals.calories}" />
				</c:forEach>
				<td><c:out value="${total}"></c:out></td>
				<td><a href= "/view/day/${workout.id}"><button class= "btn btn-outline-primary">Details</button></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a href="/logout" class="btn btn-outline-danger">Logout</a>
</body>
</html>