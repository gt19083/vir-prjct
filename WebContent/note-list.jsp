<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <html>

        <head>
            <title>Todo Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
             integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
                        <a href="Welcome.jsp" class="navbar-brand"> Todo App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/listnotes" class="nav-link">Notes</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Notes</h3>
                    <hr>
                    <div class="container text-left">

                        
     
                    </div>
                    <br>
                    
                    
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>NOTE ID</th>
                                <th>Start Date</th>
                                 <th>End Date</th>
                                <th>Reminder Date</th>
                                 <th>Status</th>
                                <th>Tag</th>
                                 <th>Description</th>
                                <th>NoteBookID</th>
                                <th>NoteBook Name</th>
                            </tr>
                        </thead>
                        <tbody>
                        
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="user" items="${list}">

                                <tr>
                                    <td>
                                      <c:out value="${user.userid}" />
                                      
                                    </td>
                                    <td>
                                        <c:out value="${user.startdate}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.enddate}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.reminderdate}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.status}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.tag}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.description}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.notebookid}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.notebookname}" />
                                    </td>
                                  	<td><a href="editnote?id=<c:out value='${user.userid}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
                                  	 <a href="deletenote?id=<c:out value='${user.userid}' />">Delete</a></td>
                                    
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>
						<a href="Welcome.jsp">Home Page</a>
                    </table>
                </div>
            </div>
        </body>

        </html>