<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Vehicles</title>
</head>
<body>

	<header> <%@ include file="/WEB-INF/header.jsp"%>
	</header>

	<h1>Vehicles for customer_id = ${customer_id}</h1>

	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>brand</th>
				<th>model</th>
				<th>productionYear</th>
				<th>serialNum</th>
				<th>nextCheckDate</th>
				<th>customer_id</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${vehicles}" var="vehicle">
				<tr>
					<td>${vehicle.id}</td>
					<td>${vehicle.brand}</td>
					<td>${vehicle.model}</td>
					<td>${vehicle.productionYear}</td>
					<td>${vehicle.serialNum}</td>
					<td>${vehicle.nextCheckDate}</td>
					<td>${vehicle.customer_id}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<h2>add vehicle:</h2>

	<form action="<c:url value = "/customerVehicles"/>" method="post">
		<c:forEach var="i" begin="1" end="5">
			<input type="text" name="brand" placeholder="brand">
			<input type="text" name="model" placeholder="model">
			<input type="number" name="productionYear"
				placeholder="productionYear">
			<input type="number" name="serialNum" placeholder="serialNum">
			<input type="text" name="nextCheckDate" placeholder="nextCheckDate">
			<input type="number" name="customer_id" placeholder="customer_id">
			<br>
			<br>
		</c:forEach>
		<input type="submit">
	</form>
	<br>

	<h2>edit vehicle</h2>
	<form action="<c:url value = "/customerVehicles"/>" method="post">
		<c:forEach var="i" begin="1" end="5">
			<input type="number" name="editId" placeholder="id">
			<input type="text" name="editBrand" placeholder="brand">
			<input type="text" name="editModel" placeholder="model">
			<input type="number" name="editProductionYear"
				placeholder="productionYear">
			<input type="number" name="editSerialNum" placeholder="serialNum">
			<input type="text" name="editNextCheckDate"
				placeholder="nextCheckDate">
			<input type="number" name="editCustomer_id" placeholder="customer_id">
			<br>
			<br>
		</c:forEach>
		<input type="submit">
	</form>

	<h2>delete vehicle</h2>
	<form action="<c:url value = "/customerVehicles"/>" method="post">
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