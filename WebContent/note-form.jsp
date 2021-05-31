<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    
     <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Todo Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand"> Todo App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/listnote" class="nav-link">NOTES</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${user != null}">
                            <form action="updatenote" method="post">
                        </c:if>
                        <c:if test="${user == null}">
                            <form action="insertnote" method="post">
                            
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${user != null}">
                                    Edit User
                                </c:if>
                                <c:if test="${user == null}">
                                    Add New User
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${user != null}">
                            <input type="hidden" name="id" value="<c:out value='${user.userid}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Start Date</label> <input type="text" value="<c:out value='${user.startdate}' />" class="form-control" name="startdate" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>End Date</label> <input type="text" value="<c:out value='${user.enddate}' />" class="form-control" name="enddate" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Reminder Date</label> <input type="text" value="<c:out value='${user.reminderdate}' />" class="form-control" name="reminderdate" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Status</label> <input type="text" value="<c:out value='${user.status}' />" class="form-control" name="status" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Tag</label> <input type="text" value="<c:out value='${user.tag}' />" class="form-control" name="tag" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Description</label> <input type="text" value="<c:out value='${user.description}' />" class="form-control" name="Descp" required="required">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>