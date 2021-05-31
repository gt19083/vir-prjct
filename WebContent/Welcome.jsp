<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<%
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		response.setHeader("Pragma","no-cache");
		response.setHeader("Expires","0");
		if(session.getAttribute("username")==null)
		{
			response.sendRedirect("Login1.jsp");
		}
	%>
	Welcome... ${username}
	<form action="LogoutServlet">
		<input type="submit" value="Logout">
	</form>
	<form action="list">
		<input type="submit" value="All NoteBooks">
	</form>
	<form action="listnotes">
		<input type="submit" value="All Notes">
	</form>
	<a href="listing?id=${username}">NOTEBOOKS</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="listingnotes?id=${username}">NOTES</a> &nbsp;&nbsp;&nbsp;&nbsp;
	
	<a href="Editprofile.jsp">Edit Your Profile</a>
	<a href="user-list.jsp">All NoteBook</a>
	<a href="noteshow.jsp">Add Notes</a>
	<a href="EditProfile.jsp">Edit profile</a>
</body>
</html>