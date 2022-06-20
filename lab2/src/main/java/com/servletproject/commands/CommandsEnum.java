package com.servletproject.commands;

public enum CommandsEnum {
    loginUser(new CommandLoginUser()),
    loginManager(new CommandLoginManager()),
    loginMaster(new CommandLoginMaster()),;

    private final ICommand command;
    CommandsEnum(ICommand command) {
        this.command=command;
    }

    public ICommand getCommand() {return command;}

}
