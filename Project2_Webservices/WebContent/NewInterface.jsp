<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style='background-color:#C0C0C0'>
<h1 align="center">alert first state should be service1, other services cannot be invoked</h1>
<div align="center" >

<form id="myform" action="rest/coordinator/initiate" method="POST" onsubmit="return get_action()">
<label>Enter your name:</label><br>
<input id="user" name="user" type="text" ><br><br>

<label id="service_id">Pick a Service to invoke</label>
<select id="serviceName" name="serviceName"  style="width: 150px">
<option value=" ">choice
<option value="service1"> service1
<option value="service2">service2
<option value="service3">service3
<option value="service4">service4
<option value="service5">service5
<option value="service6">service6
<option value="service7">service7
<option value="service8">service8
<option value="service9">service9
<option value="service10">service10
</select><br><br>

<input type="submit" value="submit">

</form>
</div>

</body>
</html>