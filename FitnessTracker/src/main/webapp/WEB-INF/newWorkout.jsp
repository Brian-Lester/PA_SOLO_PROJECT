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
          <a class="nav-link active" aria-current="page" href="/new/meal">+ Add a Meal</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/logout">Logout</a>
        </li>
        </ul>
    </div>
  </div>
</nav>
<form:form action="/new/excercise" method="post" class ="form-group" modelAttribute="newExcercise">
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
<c:if test="${newWorkout.start == null }">
<a href = "/start/time/${newWorkout.id}"><button>Start Workout</button></a>
</c:if>
<c:if test="${newWorkout.start != null }">
<a href = "/end/time/${newWorkout.id}"><button>End Workout</button></a>
</c:if>
	<h2>You're Doing Great! Heres what youve done so far</h2>
		<ol>
			<c:forEach var ="excercise" items = "${newWorkout.excercises}">
				<ul>
					<li>
					<c:out value= "${excercise.excerciseName}"></c:out>
					</li>
					<li>
					<c:out value= "${excercise.rep}"></c:out> Reps
					</li>
					<li>
					<c:out value= "${excercise.weight}"></c:out> Lbs
					</li>
					<li>
					<c:out value= "${excercise.sets}"></c:out> Sets
					</li>
					<p>
					__________________
					</p>
				</ul>
			</c:forEach>
		</ol>
	

</body>
</html>