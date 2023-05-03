package com.harsh.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			studentDbUtil = new StudentDbUtil();
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
        @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                
                String theCommand=request.getParameter("command");
                
                if(theCommand==null){
                    theCommand="LIST";
                }
                
                switch(theCommand){
                    case "LIST" -> listStudents(request, response);
                    case "ADD" -> addStudent(request,response);
                    case "LOAD" -> loadStudent(request,response);
                    case "UPDATE" -> UpdateStudent(request,response);
                    case "DELETE" -> DeleteStudent(request,response);
                    default -> listStudents(request, response);
                        
                }
                // list the students ... in mvc fashion
              
            } catch (Exception ex) {
                Logger.getLogger(StudentControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}
        private void addStudent(HttpServletRequest request,HttpServletResponse response) throws Exception {
            String fname=request.getParameter("firstname");
            String lname=request.getParameter("lastname");
            String email=request.getParameter("email");
            Student st=new Student(fname,lname,email);
            studentDbUtil.addStudent(st);
            listStudents(request, response);
            
        }
	private void listStudents(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
            PrintWriter out=response.getWriter();
		// get students from db util
		List<Student> students =(List<Student>) studentDbUtil.getStudents();
		
		// add students to the request
		request.setAttribute("STUDENT_LIST", students);
                //out.println(students);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}

    private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String theStudentId =request.getParameter("studentId");
        
        Student st=studentDbUtil.getStudent(theStudentId);
        request.setAttribute("THE_STUDENT",st);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
        
    }
    private void UpdateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id=Integer.parseInt(request.getParameter("studentId"));
        String FirstName=request.getParameter("firstname");
        String LastName=request.getParameter("lastname");
        String Email=request.getParameter("email");
        Student st=new Student(id,FirstName,LastName,Email);
        studentDbUtil.updateStudent(st);
        listStudents(request, response);
    }

    private void DeleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int sid=Integer.parseInt(request.getParameter("studentId"));
        String FirstName=request.getParameter("firstname");
        String LastName=request.getParameter("lastname");
        String Email=request.getParameter("email");
        Student st=new Student(sid,FirstName,LastName,Email);
        studentDbUtil.deleteStudent(st);
        listStudents(request, response);
    }

}













