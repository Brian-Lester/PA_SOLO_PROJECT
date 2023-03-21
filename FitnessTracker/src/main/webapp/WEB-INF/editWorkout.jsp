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
          <a class="btn btn-primary" aria-current="page" href="/home">+ Home</a>
        </li>
        <li class="nav-item">
          <a class="btn btn-primary" aria-current="page" href="/new/meal">+ Add a Meal</a>
        </li>
        <li class="nav-item">
          <a class="btn btn-danger" href="/logout">Logout</a>
        </li>
        </ul>
    </div>
  </div>
</nav>
<form:form action="/edit/excercise" method="post" class ="form-group" modelAttribute="newExcercise">
<form:label path="excerciseType"></form:label>
 <form:errors path="excerciseType" class="text-danger"/><br>
 <form:select path ="excerciseType">
 <form:option value ="push">Push</form:option>
  <form:option value = "pull">Pull</form:option>
   <form:option value= "legs">Legs</form:option>
    <form:option value="cardio">Cardio</form:option>
 </form:select>
		<div class="mb-3">
        	<form:label path="excerciseName" class="form-label">Excercise Name:</form:label>
		    <form:errors path="excerciseName" class="text-danger"/><br>
             <form:input type="text" class="form-control" placeholder="Flat Bench" path="excerciseName"/>
    	</div>
    	<div class="mb-3">
        	<form:label path="rep" class="form-label">Reps</form:label>
		    <form:errors path="rep" class="text-danger"/><br>
             <form:input type="number" class="form-control" placeholder="8" path="rep"/>
    	</div>
    	<div class="mb-3">
        	<form:label path="weight" class="form-label">Weight:</form:label>
		    <form:errors path="weight" class="text-danger"/><br>
             <form:input type="number" class="form-control" placeholder="8" path="weight"/>
    	</div>
    	<div class="mb-3">
        	<form:label path="sets" class="form-label">Sets:</form:label>
		    <form:errors path="sets" class="text-danger"/><br>
             <form:input type="number" class="form-control" placeholder="3" path="sets"/>
    	</div>
    	<form:input type = "hidden" path = "workout" value="${ newWorkout.id}"/>
    	<button>Add Excercise</button>
	</form:form>
	<h2>You're Doing Great! Heres what you did!</h2>
	<div class="overflow-scroll w-100 p-3" style ="height:300px">
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
			<c:forEach var="workout" items="${newWorkout.excercises}" varStatus="loop">
				

		<tr>
				<td><c:out value="${workout.excerciseName}"></c:out></td>
				<td><c:out value="${workout.rep}"></c:out></td>
				<td><c:out value="${workout.weight}"></c:out></td>
				<td><c:out value="${workout.sets}"></c:out></td>
				<td><c:out value="${workout.excerciseType}"></c:out></td>
				<td><a href="/delete/excercise/${workout.id}" class="btn btn-outline-danger">Delete Excercise</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	</div>
</body>
</html>