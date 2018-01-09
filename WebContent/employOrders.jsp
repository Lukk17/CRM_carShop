<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Orders</title>
</head>
<body>

	<header> <%@ include file="/WEB-INF/header.jsp"%>
	</header>

	<h1>for employee with id: ${employee_id}</h1>
	<br>

	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>takeDate</th>
				<th>startDate</th>
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

	<footer> <%@ include file="/WEB-INF/footer.jsp"%>
	</footer>

</body>
</html>