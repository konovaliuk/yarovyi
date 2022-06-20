package com.servletproject.commands;

import com.servletproject.entities.Manager;
import com.servletproject.manager.Config;
import com.servletproject.services.ManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandLoginManager implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        ManagerService user = new ManagerService();
        if (user.checkManager(username,password)){
            Manager currUser = user.find(username);
            System.out.println(currUser);
            request.getSession().setAttribute("userId",currUser.getId());
            page = Config.getInstance().getProperty("main");
        }
        else{
            request.getSession().setAttribute("error", "Login error!");
            page = Config.getInstance().getProperty("mg_login");
        }
        return page;
    }
}
