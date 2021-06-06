<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style='background-color:#A9A9A9'>
<h1></h1>
<div align="center" >
<form id="myform" action="rest/" method="POST" onsubmit="return get_action()">
<label id="service_id">userService</label>
<select id="serviceName" name="serviceName">
<option value="pick a service">pick a service
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

</select>
<input id="input" name="user" type="text" >
<input type="submit">
</form>
</div>
<script>
function get_action(){
	var servicename=document.getElementById("serviceName").value;
	document.getElementById("myform").action="rest/"+servicename+"/newrequest";
}
</script>
</body>
</html>