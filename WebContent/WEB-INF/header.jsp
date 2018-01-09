<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
      body{
        background-color: red;
        table, th, td 
        {
   			border: 1px solid black;
		}
		input[type=text] 
		{
		background-color: #3CBC8D;
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
}
      }
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
</head>
<body>
	<a href='<c:url value = "/index"/>'>main</a>
	<a href='<c:url value = "/orders"/>'>orders</a>
	<a href='<c:url value = "/customers"/>'>customer</a>
	<a href='<c:url value = "/employees"/>'>employee</a>
	<a href='<c:url value = "/raports"/>'>raports</a>
</body>
</html>