package com.tdtechweb;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@MultipartConfig
@WebServlet("/register")
public class ResertrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
  

	
	//protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	//}
	  public boolean uploadFile(InputStream is, String path){
	        boolean test = false;
	        try{
	            byte[] byt = new byte[is.available()];
	            is.read();
	            
	            FileOutputStream fops = new FileOutputStream(path);
	            fops.write(byt);
	            fops.flush();
	            fops.close();
	            
	            test = true;
	            
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        return test;
	    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	PrintWriter out =response.getWriter();
		//doPost(request, response);
		String uname =request.getParameter("name");
		String uemail =request.getParameter("email");
		String upwd =request.getParameter("pass");
		String umobile =request.getParameter("contact");
		String npic;
		
		try (PrintWriter out = response.getWriter()) {
		Part part =request.getPart("fpic");
		 npic =part.getSubmittedFileName();
		
		String path =getServletContext().getRealPath("/"+"images"+File.separator+npic);
		
		  InputStream is = part.getInputStream();
          boolean test = uploadFile(is,path);
          if(test){
              out.println("uploaded to :.."+path);
          }else{
              out.println("something wrong");
          }
       
		
		}
		
		
		RequestDispatcher dispatcher= null;
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/techwebdb","root","");
			
			PreparedStatement pst =con.prepareStatement("INSERT INTO users ( uname, upass, uemail, umobile, grade, statuss,picture) VALUES (?,?,?,?,'member','online',?);");
			pst.setString(1, uname); 
			pst.setString(2, upwd);
			pst.setString(3, uemail);
			pst.setString(4, umobile);
			pst.setString(5, npic);
			
			
			int rowcount=pst.executeUpdate();
			dispatcher =request.getRequestDispatcher("registration.jsp");
			if(rowcount >0) {
				request.setAttribute("status", "success");
				
			}else {
				request.setAttribute("status", "failed");
			}
		dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
