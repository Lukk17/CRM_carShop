<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Orders</title>
</head>
<body>
	<header> <%@ include file="/WEB-INF/header.jsp"%>
	</header>

	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>takeDate</th>
				<th>startDate</th>
				<th>employee_id</th>
				<th>problemDesc</th>
				<th>repairDesc</th>
				<th>status</th>
				<th>vehicle_id</th>
				<th>price</th>
				<th>partsPrice</th>
				<th>hoursPrice</th>
				<th>hours</th>
				<th>customer_id</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orders}" var="order">
				<tr>
					<td>${order.id}</td>
					<td>${order.takeDate}</td>
					<td>${order.startDate}</td>
					<td>${order.employee_id}</td>
					<td>${order.problemDesc}</td>
					<td>${order.repairDesc}</td>
					<td>${order.status}</td>
					<td>${order.vehicle_id}</td>
					<td>${order.price}</td>
					<td>${order.partsPrice}</td>
					<td>${order.hoursPrice}</td>
					<td>${order.hours}</td>
					<td>${order.customer_id}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<h2>add order:</h2>

	<form action="<c:url value = "/orders"/>" method="post">
		<c:forEach var="i" begin="1" end="5">
			<input type="text" name="takeDate" placeholder="takeDate">
			<input type="text" name="startDate" placeholder="startDate">
			<input type="number" name="employee_id" placeholder="employee_id">
			<input type="text" name="problemDesc" placeholder="problemDesc">
			<input type="text" name="repairDesc" placeholder="repairDesc">
			<input type="text" name="status" placeholder="status">
			<input type="number" name="vehicle_id" placeholder="vehicle_id">
			<input type="number" name="price" placeholder="price">
			<input type="number" name="partsPrice" placeholder="partsPrice">
			<input type="number" name="hoursPrice" placeholder="hoursPrice">
			<input type="number" name="hours" placeholder="hours">
			<input type="number" name="customer_id" placeholder="customer_id">
			<br>
			<br>
		</c:forEach>
		<input type="submit">
	</form>
	<br>

	<h2>edit order</h2>
	<form action="<c:url value = "/orders"/>" method="post">
		<c:forEach var="i" begin="1" end="5">
			<input type="number" name="editId" placeholder="id">
			<input type="text" name="editTakeDate" placeholder="takeDate">
			<input type="text" name="editStartDate" placeholder="startDate">
			<input type="number" name="editEmployee_id" placeholder="employee_id">
			<input type="text" name="editProblemDesc" placeholder="problemDesc">
			<input type="text" name="editRepairDesc" placeholder="repairDesc">
			<input type="text" name="editStatus" placeholder="status">
			<input type="number" name="editVehicle_id" placeholder="vehicle_id">
			<input type="number" name="editPrice" placeholder="price">
			<input type="number" name="editPartsPrice" placeholder="partsPrice">
			<input type="number" name="editHoursPrice" placeholder="hoursPrice">
			<input type="number" name="editHours" placeholder="hours">
			<input type="number" name="editCustomer_id" placeholder="customer_id">
			<br>
			<br>
		</c:forEach>
		<input type="submit">
	</form>

	<h2>delete order</h2>
	<form action="<c:url value = "/orders"/>" method="post">
		<c:forEach var="i" begin="1" end="5">
			<input type="number" name="deleteId" placeholder="id">
			<br>
		</c:forEach>
		<input type="submit">
	</form>

	<footer> <%@ include file="/WEB-INF/footer.jsp"%>
	</footer>
	
</body>
</html>