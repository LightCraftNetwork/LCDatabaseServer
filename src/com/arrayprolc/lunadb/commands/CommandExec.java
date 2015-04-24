package com.arrayprolc.lunadb.commands;

import java.io.File;

import com.arrayprolc.lunadb.command.Command;

public class CommandExec extends Command {

    public CommandExec() {
        super("exec");
        this.setDescription("Run a script!");
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
        return "";
    }

}
