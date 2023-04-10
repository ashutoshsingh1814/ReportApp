<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>


<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Report Application</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">


</head>
<body>
	<div class="container">
		<h1 class="pb-3 pt-3 " style="text-align: center;">Insurance Report Application</h1>
		<form:form action="search" modelAttribute="searchReq" method="POST">

			<table>
				<tr>
					<td>Plan Name:</td>
					<td><form:select path="planName">
							<form:option value="">-Select-</form:option>
							<form:options items="${plansName}" />
						</form:select></td>

					<td>Plan Status:</td>
					<td><form:select path="planStatus">
							<form:option value="">-Select-</form:option>
							<form:options items="${status}" />
						</form:select></td>



					<td>Gender:</td>
					<td><form:select path="gender">
							<form:option value="">-Select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Female">Fe-male</form:option>

						</form:select></td>
				</tr>
				<tr>
					<td>Start Date</td>
					<td><form:input path="planStartDate" type="date" /></td>

					<td>End Date</td>
					<td><form:input path="planEndDate" type="date" /></td>
				</tr>
				<tr>
					<td><input type="submit" class="btn btn-primary"></td>
				</tr>




			</table>


		</form:form>

		<hr />
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Client Id</th>
					<th>Client Name</th>
					<th>Plan Name</th>
					<th>Plan Status</th>
					<th>Gender</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Benefit Amount</th>


				</tr>
			</thead>
			<tbody>
				<c:forEach items="${plans}" var="plan">
					<tr>

						<td>${plan.clientId}</td>
						<td>${plan.clientName}</td>
						<td>${plan.planName}</td>
						<td>${plan.planStatus}</td>
						<td>${plan.gender}</td>
						<td>${plan.planStartDate}</td>
						<td>${plan.planEndDate}</td>
						<td>${plan.benifitAmt}</td>


					</tr>
				</c:forEach>
				<c:if test="${empty plans}">
				<tr>
				<td colspan="8" style="text-align: center;">No Records found -- Please Try Different Combination</td>
				</tr>
				</c:if>
			</tbody>

		</table>

		<hr />

		Export: <a href="">Excel</a> <a href="">Pdf</a>

	</div>
</body>
</html>



