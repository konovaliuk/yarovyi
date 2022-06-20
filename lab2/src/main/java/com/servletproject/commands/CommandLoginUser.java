package com.servletproject.commands;

import com.servletproject.entities.User;
import com.servletproject.manager.Config;
import com.servletproject.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandLoginUser implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String page = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService user = new UserService();
        if (user.checkUser(username,password)){
            User currUser = user.find(username);
            System.out.println(currUser);
            request.getSession().setAttribute("userId",currUser.getId());
            page = Config.getInstance().getProperty("main");
        }
        else{
            request.getSession().setAttribute("error", "Login error!");
            page = Config.getInstance().getProperty("u_login");
        }
        return page;
    }

}
