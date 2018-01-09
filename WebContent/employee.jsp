<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employees</title>
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
				<th>adress</th>
				<th>tel</th>
				<th>note</th>
				<th>hourPrice</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${employees}" var="employee">
				<tr>
					<td>${employee.id}</td>
					<td>${employee.name}</td>
					<td>${employee.surname}</td>
					<td>${employee.adress}</td>
					<td>${employee.tel}</td>
					<td>${employee.note}</td>
					<td>${employee.hourPrice}</td>
					<td><a
						href='<c:url value = "/employOrders?id=${employee.id}"/>'>orders</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<h2>add employee:</h2>

	<form action="<c:url value = "/employees"/>" method="post">
		<c:forEach var="i" begin="1" end="5">
			<input type="text" name="name" placeholder="name">
			<input type="text" name="surname" placeholder="surname">
			<input type="text" name="adress" placeholder="adress">
			<input type="text" name="tel" placeholder="tel">
			<input type="text" name="note" placeholder="note">
			<input type="number" name="hourPrice" placeholder="hourPrice">
			<br>
		</c:forEach>
		<input type="submit">
	</form>
	<br>

	<h2>edit employee</h2>
	<form action="<c:url value = "/employees"/>" method="post">
		<c:forEach var="i" begin="1" end="5">
			<input type="number" name="editId" placeholder="id">
			<input type="text" name="editName" placeholder="name">
			<input type="text" name="editSurname" placeholder="surname">
			<input type="text" name="editAdress" placeholder="adress">
			<input type="text" name="editTel" placeholder="tel">
			<input type="text" name="editNote" placeholder="note">
			<input type="number" name="editHourPrice" placeholder="hourPrice">
			<br>
		</c:forEach>
		<input type="submit">
	</form>

	<h2>delete employee</h2>
	<form action="<c:url value = "/employees"/>" method="post">
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