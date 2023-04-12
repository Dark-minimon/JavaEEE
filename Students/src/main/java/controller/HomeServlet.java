package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
//import java.io.PrintWriter;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

private static final long serialVersionUID = 1L;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

//String css = "/webapp/css/style.css";	
String path = "/WEB-INF/index.jsp";
ServletContext servletContext = getServletContext();
RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);

requestDispatcher.forward(request, response);
}
}