<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c"
		   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Edit</title>
<%@ include file="resources/html/head.html"%>
<link rel="stylesheet" href="resources/css/header-menu.css">
<link rel="stylesheet" href="resources/css/footer.css">
</head>
<body>
<%@ include file="resources/html/header.html"%>
<div class="container">
	<h1>Edit</h1>
	<form action="event" method="post">
		<input type="hidden" value="edit" name="action">
		<input type="hidden" value="${event.id}" name="id">
		<input type="hidden" value="${event.userId}" name="userId">
		<div class="form-group">
			<label for="id-calorie">CEP</label>
			<input type="text" name="cep" id="id-cep" class="form-control" value="${event.cep}" >
		</div>
		<div class="form-group">
			<label for="id-date">Date</label>
			<input type="text" name="date" id="id-date" class="form-control" 
				value='<fmt:formatDate value="${event.date }" pattern="dd/MM/yy hh:mm"/>'>
		</div>
		<div class="form-group">
			<label for="id-crime">Crime</label>
			<select name="crime" id="id-crime" class="form-control">
				<option value="0">Selecione</option>
				<c:forEach items="${crime}" var="c">
					<c:if test="${c.crimeId == event.crime.crimeId}">
						<option value="${c.crimeId}" selected>${c.crimeName}</option>
					</c:if>
					<c:if test="${c.crimeId != event.crime.crimeId}">
						<option value="${c.crimeId}">${c.crimeName }</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		<input type="submit" value="Save" class="btn btn-primary">
		<a href="food?action=list" class="btn btn-danger">Cancel</a>
	</form>
</div>
</body>
</html>