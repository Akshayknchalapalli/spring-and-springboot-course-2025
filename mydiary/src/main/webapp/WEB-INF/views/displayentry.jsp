<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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
<h1>VIEW ENTRY</h1><br/><br/><br/>

<table>
<tr><td>Date: </td><td>${entry.entrydate}</td></tr>
<tr><td>Descritpion</td><td>${entry.description}</td></tr>
</table>

<br/><br/><br/>
<a href="./userhome"><button type="submit" class="button">BACK TO HOME </button></a>


</form>

</div>




</div>

</body>
</html>