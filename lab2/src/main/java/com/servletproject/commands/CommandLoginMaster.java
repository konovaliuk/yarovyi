package com.servletproject.commands;

import com.servletproject.entities.Master;
import com.servletproject.entities.User;
import com.servletproject.manager.Config;
import com.servletproject.services.MasterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandLoginMaster implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        MasterService user = new MasterService();
        if (user.checkMaster(username,password)){
            Master currUser = user.find(username);
            System.out.println(currUser);
            request.getSession().setAttribute("userId",currUser.getId());
            page = Config.getInstance().getProperty("main");
        }
        else{
            request.getSession().setAttribute("error", "Login error!");
            page = Config.getInstance().getProperty("ma_login");
        }
        return page;
    }
}
