package com.lightcraftmc.database.commands;

import com.lightcraftmc.database.command.Command;
import com.lightcraftmc.login.LoginManager;

public class CommandLogin extends Command {

    public CommandLogin() {
        super("login");
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args) {
        if (args.length == 0) {
            if (LoginManager.getInstance().isLoggedIn(ip)) {
                return "FAILED: You are already logged in!";
            }
            LoginManager.getInstance().addLogin(ip);
            return "SUCCESS: You have been logged in!";
        }
        return "SUCCESS: logged in: " + LoginManager.getInstance().isLoggedIn(ip);
    }

}
