package com.arrayprolc.lunadb.command;

public abstract class Command {

    private String name;
    private String[] subNames;
    private String description = "undefined";
    private boolean requiresLogin = true;
    private static Command instance;

    public Command(String name) {
        this.name = name;
        this.subNames = new String[] {};
        instance = this;
    }

    public static Command getInstance() {
        return instance;
    }

    /**
     * For use inside of
     */
    @Deprecated
    public String prepareCommandForRun(String ipAddress, boolean isLocal, String[] args, boolean isAdmin) {
        if (isRequiresLogin() && !isAdmin) {
            return "FAILED: You must be logged in to use that command!";
        }
        return runCommand(ipAddress, isLocal, args, isAdmin);
    }

    public abstract String runCommand(String ipAddress, boolean isLocal, String[] args, boolean isAdmin);

    public String runCommandFromScript(String runner, String[] args) {
        return runCommand("127.0.0.1", true, args, true);
    }

    public String throwError(String error) {
        System.out.println("[Luna] [COMMAND ERROR] " + error);
        return "[COMMAND ERROR] " + error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSubNames() {
        return subNames;
    }

    public void setSubNames(String[] subNames) {
        this.subNames = subNames;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRequiresLogin() {
        return requiresLogin;
    }

    public void setRequiresLogin(boolean requiresLogin) {
        this.requiresLogin = requiresLogin;
    }

}
