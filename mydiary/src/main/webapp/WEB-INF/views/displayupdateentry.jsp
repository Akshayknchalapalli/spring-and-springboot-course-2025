<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyDiary App Homepage</title>
<link rel="stylesheet" href="<c:url value="/resources/styles/style.css"/>">
</head>
<body>

<div class="header">

<div class="first">
<img src="<c:url value="/resources/images/diary.jpg"/>" width="40" height="40">
</div>
<div class="second">MyDiary App</div>

</div>

<br/><br/>

<hr/>
<br/><br/>
<h1>welcome,  <span style="color:SteelBlue">${user.username}!</span></h1> 

<a href="./signout" style="color:red;float:right">Signout</a>

<div class="body-part">

<div class="body-part-1">
<img src="<c:url value="/resources/images/diary.jpg"/>">
</div>

<div class="body-part-2">
<h1>UPDATE ENTRY</h1><br/><br/><br/>
<form action="./processentryupdate" method="POST">
<label>Date</label> 
<input type="text" name="entrydate" class="formControls" value="<fmt:formatDate value="${entry.entrydate}" 
	pattern="yyyy-MM-dd"/>" readonly="readonly"><br/><br/>
<label>Description</label> 
<textarea rows="10" cols="30" name="description">${entry.description}</textarea>
<input type="hidden" name="userid" value="${user.id}">
<input type="hidden" name="id" value="${entry.id}">

<br/><br/><br/>
<button type="submit" class="button">UPDATE ENTRY </button>

</form>

</div>




</div>

</body>
</html>