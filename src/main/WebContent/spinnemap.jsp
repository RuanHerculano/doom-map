<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"
		   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
		   uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <title>Spinne Map</title>
    <meta name="description" content="violence map">
    <meta name="keywords" content="violence, steal, rape, murder, city, São Paulo, map, dangerous, street, neighborhood">
    <%@ include file="resources/html/cursive-font.html"%>
	<%@ include file="resources/html/head.html"%>
    <link rel="stylesheet" href="resources/css/header-menu.css">
    <link rel="stylesheet" href="resources/css/register.css">
    <link rel="stylesheet" href="resources/css/select-data.css">
    <link rel="stylesheet" href="resources/css/main-info.css">
    <link rel="stylesheet" href="resources/css/add-search.css">
    <link rel="stylesheet" href="resources/css/table.css">
    <link rel="stylesheet" href="resources/css/main.css">
    <link rel="stylesheet" href="resources/css/screens-add.css">
    <link rel="stylesheet" href="resources/css/spinnemap.css">
  </head>
  
  <body>
  
  <!-- Header & menu -->
  <%@ include file="resources/html/header.html"%>
  
  <div class="main">
    <div class="select-info">
    
      <!-- Select -->
      <select>
        <option value="dawn">Dawn</option>
        <option value="morning">Morning</option>
        <option value="afternoon">Afternoon</option>
        <option selected value="night">Night</option>
      </select>
      
      <!-- Info -->
      <div class="row">
      
      	<!-- Street -->
        <div class="col-12 text-center">
          <p id="main-info">Most dangerous street: Av. Mateo Bei</p>          
        </div>
        
        <!-- Remaining calories -->
        <div class="col-12 text-center">
          <p id="main-info">Most dangerous neighborhood: Itaquera</p>
        </div>
      
      </div>
    </div>
  
  <!-- Insert button & search -->
  <%@ include file="resources/html/add-search.html"%>
  
  <!-- Insert screen -->
  <form class="form" action="register" id="register" method="post">
 <input type="hidden" value="insert" name="action"> 
  <div id="screen">
          <p class="text-center" id="instruction">Select the crime.</p>
            <select id="add-select" name="meal">
            <c:forEach items="${crime}" var="c"> 
                <option value="${c.crimeId}">${c.crimeName}</option>
            </c:forEach>
            </select>
            <div class="row">
                <div class="col-6">
          <button type="button" class="btn btn-light float-right" id="send">Next</button></div>
          <div class="col-6"><button type="button" class="btn btn-light" id="cancel">Cancel</button></div></div>
      </div>
      
      <div id="screen2">
          <p class="text-center" id="instruction">Date/time:</p>
          <input id="datetime" type="text" name="date">
            <div class="row">
                <div class="col-6">
          <button type="button" class="btn btn-light float-right" id="send2">Next</button></div>
          <div class="col-6"><button type="button" class="btn btn-light" id="cancel2">Cancel</button></div></div>
      </div>
      
      <div id="screen3">
          <p class="text-center" id="instruction">CEP:</p>
          <input id="number" name="calorie" type="text">
            <div class="row">
                <div class="col-6">
          <button type="button" class="btn btn-light float-right" id="send3">Next</button></div>
          <div class="col-6"><button type="button" class="btn btn-light" id="cancel3">Cancel</button></div></div>
      </div>
                </form>
                
  <!-- Table -->
  <table class="table table-striped">
        <thead>
          <tr>
            <th></th>
            <th>When</th>
            <th>What</th>
            <th>Where</th>
            <th>Registered by</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${event}" var="e">
          <tr>
            <td>
                    <c:url value="event" var="link">
							<c:param name="action" value="openEdit"/>
							<c:param name="code" value="${e.code}"/>
						</c:url>
						<a href="${link}"><i class="fas fa-pen"></i></a><i class="fas fa-trash-alt" data-toggle="modal" data-target="#deleteModal" onclick="id-delete.value = ${e.code}"></i></td>
            <td>
            <fmt:formatDate value="${e.date}" pattern="dd/MM/yy hh:mm"/>
            </td>
            <td>${e.crime.crimeName}</td>
            <td>${e.cep}</td>
            <td>${e.user}</td>
          </tr>
          </c:forEach>
          <tr>
        </tbody>
      </table>
    </div>
    
     <!-- Delete Confirmation -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        		Do you want to delete it?
      </div>
      <div class="modal-footer">
      	<form action="pressure" method="post">
      		<input type="hidden" name="action" value="delete">
      		<input type="hidden" name="code" id="id-delete">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
	        <button type="submit" class="btn btn-danger">Delete</button>
        </form>
      </div>
    </div>
  </div>
</div>

	<!-- JS links -->
    <%@ include file="resources/html/js-links.html"%>
    <script type="text/javascript" src="resources/js/insert.js"></script>
    <script type="text/javascript" src="resources/js/add-cancel.js"></script>
</body>
</html>