package com.arrayprolc.lunadb.commands;

import com.arrayprolc.lunadb.LunaDB;
import com.arrayprolc.lunadb.command.Command;
import com.arrayprolc.lunadb.login.LoginManager;

public class CommandLogin extends Command {

    public CommandLogin() {
        super("login");
        this.setDescription("Log into a user account.");
        setRequiresLogin(false);
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
        if (isLocal) {
            return "FAILED: Sign-ins are not needed locally.";
        }

        if (args.length == 0) {
            return "SUCCESS: logged in: " + LoginManager.getInstance().isLoggedIn(ip);
        }
        if (args[0].equalsIgnoreCase("help")) {
            return "SUCCESS: To sign in, use your access key given by your system administrator on the sign-in page. Some commands can be used without a sign-in, such as 'login'. " + "To use those, simply type in 'publicKey' into the login box. "
                    + "<br> <h5><b>Keep in mind that this is NOT the login page. The login page can be found <a href=\"/\">here</a>.</b></h5>";
        }
        if (args[0].equalsIgnoreCase("signout")) {
            LoginManager.getInstance().logOut(ip);
            return "SUCCESS: You have been signed out.";
        }
        // TODO add more access keys
        if (args[0].equalsIgnoreCase("publicKey")) {
            return "SUCCESS: You have been signed in with the public account. Many queries will not be functional with this account!";
        }
        if (!args[0].equals(LunaDB.getManager().getAccessKey())) {
            return "FAILED: That is an incorrect login!";
        }
        if (LoginManager.getInstance().isLoggedIn(ip)) {
            return "FAILED: You are already logged in!";
        }
        LoginManager.getInstance().addLogin(ip);
        return "SUCCESS: You have been logged in!";

    }
}
