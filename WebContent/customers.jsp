<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customers</title>
</head>
<body>
	<header> <%@ include file="/WEB-INF/header.jsp"%>
	</header>

	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>surname</th>
				<th>orders</th>
				<th>vehicles</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${customers}" var="customer">
				<tr>
					<td>${customer.id}</td>
					<td>${customer.name}</td>
					<td>${customer.surname}</td>

					<td><a
						href='<c:url value = "/customerOrders?id=${customer.id}"/>'>orders</a></td>
					<td><a
						href='<c:url value = "/customerVehicles?id=${customer.id}"/>'>vehicles</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<h2>add customer:</h2>

	<form action="<c:url value = "/customers"/>" method="post">
		<c:forEach var="i" begin="1" end="5">
			<input type="text" name="name" placeholder="name">
			<input type="text" name="surname" placeholder="surname">

			<br>
		</c:forEach>
		<input type="submit">
	</form>
	<br>

	<h2>edit customer</h2>
	<form action="<c:url value = "/customers"/>" method="post">
		<c:forEach var="i" begin="1" end="5">
			<input type="number" name="editId" placeholder="id">
			<input type="text" name="editName" placeholder="name">
			<input type="text" name="editSurname" placeholder="surname">

			<br>
		</c:forEach>
		<input type="submit">
	</form>

	<h2>delete customer</h2>
	<form action="<c:url value = "/customers"/>" method="post">
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