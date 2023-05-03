<%-- 
    Document   : list-student
    Created on : 29-Mar-2023, 12:48:35 am
    Author     : Acer
--%>


<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <body>
       <div id="wrapper">
		<div id="header">
			<h2>User Management Application</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
                    <input type="button" value="Add Student"
                           onclick="window.location.href='add-student-form.jsp'; return false;" 
                           class="add-student-button"
                       
                        />
			<table>
			
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
                                        <th>Action</th>
				</tr>
				
                                <c:forEach var="tempStudent" items="${STUDENT_LIST}">
                                    <c:url var="tempLink" value="StudentControllerServlet">
                                        <c:param name="command" value="LOAD"/>
                                        <c:param name="studentId" value="${tempStudent.id}"/>                                       
                                    </c:url>
                                    <c:url var="deleteLink" value="StudentControllerServlet">
                                        <c:param name="command" value="DELETE"/>
                                        <c:param name="studentId" value="${tempStudent.id}"/>                                       
                                    </c:url>
					<tr>
						<td> ${tempStudent.firstName} </td>
						<td> ${tempStudent.lastName}  </td>
						<td> ${tempStudent.email}  </td>
                                                <td> 
                                                    <a href="${tempLink}">Update</a>
                                                    |
                                                    <a href="${deleteLink}"
                                                       onclick="if(  !(confirm('Are you sure you want to delte this student?'))) retrun false"
                                                    >Delete</a>
                                                </td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
    </body>
</html>
