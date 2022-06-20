package com.servletproject.servlet;

import com.servletproject.commands.CommandMissing;
import com.servletproject.commands.ICommand;
import com.servletproject.commands.CommandsEnum;

import javax.servlet.http.HttpServletRequest;

public class ControllerHelper {
    private static ControllerHelper instance = null;

    public ICommand getCommand(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        ICommand command;
        try {
            command = CommandsEnum.valueOf(commandName).getCommand();
        }
        catch (Exception e) {
            command = new CommandMissing();
            commandName = "missing";
        }

        System.out.println("Executing command " + commandName);
        return command;
    }

    public static ControllerHelper getInstance(){
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }
}
