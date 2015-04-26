package com.arrayprolc.lunadb;

import com.arrayprolc.lunadb.command.Command;
import com.arrayprolc.lunadb.command.CommandManager;
import com.arrayprolc.lunadb.webserver.ServerHandler;

public class CommandInterpreter {

    public ServerHandler f;

    public CommandInterpreter() {
    }

    public synchronized void listen() {
        System.out.print("");
        String s = LunaDB._SCANNER.nextLine();
        interpret(s);
        listen(); // Forces a command to be interpreted before trying to read
           // the next one due to the synchronized modifier
       }

    public void interpret(String s) {
        System.out.println(interpretString(s));
    }
    
    public String interpretString(String s) {
        String commandName;
        String[] args;
        if (s.contains(" ")) {
            commandName = s.split(" ")[0];
            args = getArgs(s).split(" ");
        } else {
            commandName = s;
            args = new String[] {};
        }
        for (Command command : CommandManager.getInstance().commands) {
            if (command.getName().equalsIgnoreCase(commandName)) {
                try {
                    return (command.runCommand("127.0.0.1", true, args, true));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        for (Command command : CommandManager.getInstance().commands) {
            for (String subName : command.getSubNames()) {
                try {
                    if (subName.equalsIgnoreCase(commandName)) {
                        return (command.runCommand("127.0.0.1", true, args, true));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
        return ("[Luna] [ERROR] Unknown command. Use \"help\" command to get a list of commands.");
    }


    public String getArgs(String s) {
        String remove = s.split(" ")[0];
        s = s.replaceFirst(remove + " ", "");
        return s;
    }

}
