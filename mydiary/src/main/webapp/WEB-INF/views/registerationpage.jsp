<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyDiary App Home Page</title>
<link href="<c:url value='/resources/styles/style.css' />" rel="stylesheet">
</head>
<body>
	<div class="header">
		<div class="first">
			<img alt="" src="<c:url value="/resources/images/diary.jpg"/>" width="40" height="40" />
		</div>
		<div class="second">My Diary App</div>
	</div>
	<br>
	<br>
	<hr>
	
	<div class="body-part">
		<div class="body-part-1">
			<img alt="" src="<c:url value="/resources/images/diary.jpg"/>" />
		</div>
		<div class="body-part-2">
			<h1>REGISTER FORM</h1>
			<br>
			<br>
			<form action="./saveuser" method="POST">
			<label class="label">username:</label> 
			<input type="text" name="username" class="formControls" />
			<br>
			<br>
			 <label class="label">password:</label>  
			 <input type="password" name="password" class="formControls">
			<br>
			<br>
			<button type="submit" class="button">REGISTER</button>
			<br>
			<br>
			</form>
			<br>
			<br>
			Existing User? <a href="./home">Login</a> here
		</div>
		
	</div>
	
	
	
</body>
</html>