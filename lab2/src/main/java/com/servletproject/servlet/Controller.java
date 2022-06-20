package com.servletproject.servlet;

import com.servletproject.commands.ICommand;
import com.servletproject.manager.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import javax.servlet.ServletException;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
     ControllerHelper controllerHelper = ControllerHelper.getInstance();

     protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
          String page = null;
          try {
               ICommand command = controllerHelper.getCommand(request);
               page = command.execute(request,response);
          } catch (ServletException s){
               s.printStackTrace();
               request.setAttribute("messageError", Message.getInstance().getProperty("SERVLET_EXCEPTION"));
          } catch (IOException i){
               i.printStackTrace();
               request.setAttribute("messageError", Message.getInstance().getProperty("IO_EXCEPTION"));
          }

          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
          dispatcher.forward(request,response);
     }

     @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          processRequest(req, resp);
     }

     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          processRequest(req, resp);
     }

     @Override
     public String getServletInfo() {
          return super.getServletInfo();
     }
}
