/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.harsh.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class StudentDbUtil {
    public void addStudent(Student st) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/web";
        String uname="root";
        String pass="Harsh2404@";
        Connection con=DriverManager.getConnection(url,uname,pass);
        String query="insert into record(fname,lname,email) values(?,?,?)";
        PreparedStatement pre = con.prepareStatement(query);
        pre.setString(1, st.getFirstName());
        pre.setString(2, st.getLastName());
        pre.setString(3, st.getEmail());
        pre.execute();
        con.close();
        pre.close();
    }
    
    public List<Student> getStudents() throws ClassNotFoundException, SQLException{
        List<Student> students=new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/web";
        String uname="root";
        String pass="Harsh2404@";
        Connection con=DriverManager.getConnection(url,uname,pass);
        String query="Select * from Record";
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        while(rs.next()){
            int id=rs.getInt("id");
            String fname=rs.getString("fname");
            String lname=rs.getString("lname");
            String email=rs.getString("email");
            System.out.println(fname);
            Student s=new Student(id,fname,lname,email);
            students.add(s);
        }
        st.close();
        con.close();
        return students;
    }

    Student getStudent(String theStudentId) throws ClassNotFoundException, SQLException, Exception {
        Student st=null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/web";
        String uname="root";
        String pass="Harsh2404@";
        Connection con=DriverManager.getConnection(url,uname,pass);
        String query="select * from record where id=?";
        PreparedStatement pre = con.prepareStatement(query);
        int sid=Integer.parseInt(theStudentId);
        pre.setInt(1, sid);
        ResultSet rs=pre.executeQuery();
        if(rs.next()){
        String fname=rs.getString("fname");
        String lname=rs.getString("lname");
        String email=rs.getString("email");
         st=new Student(fname,lname,email);
        }
        else{
            throw new Exception("Could not Find Student id:"+theStudentId);
        }
        pre.execute();
        con.close();
        pre.close();
        return st;
    }

    public void updateStudent(Student st) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/web";
        String uname="root";
        String pass="Harsh2404@";
        Connection con=DriverManager.getConnection(url,uname,pass);
        String query="update record set fname=?,lname=?,email=? where id=?";
        PreparedStatement pre = con.prepareStatement(query);
        pre.setString(1, st.getFirstName());
        pre.setString(2, st.getLastName());
        pre.setString(3, st.getEmail());
        pre.setInt(4,st.getId());
        pre.execute();
        con.close();
        pre.close();
    }

    void deleteStudent(Student st) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/web";
        String uname="root";
        String pass="Harsh2404@";
        Connection con=DriverManager.getConnection(url,uname,pass);
        String query="DELETE FROM record WHERE id=?";
        PreparedStatement pre = con.prepareStatement(query);
        pre.setInt(1,st.getId());
        pre.execute();
        con.close();
        pre.close();
    }
}
