package com.arrayprolc.lunadb.commands;

import java.util.Base64;

import com.arrayprolc.lunadb.command.Command;
import com.arrayprolc.lunadb.util.UtilFile;

public class CommandInsertScript extends Command {

    public CommandInsertScript() {
        super("insertscript");
        setDescription("Set a specific script value into the database!");
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
        if (args.length == 0) {
            return ("Usage: insert category key value (Please use Base64.)");
        }
        try {
            String category = args[0].toLowerCase();
            String key = args[1];
            String value = Base64.getDecoder().decode(args[2]).toString();
            UtilFile.saveScript(category, key, value);
            return ("SUCCESS: Inserting " + value.length() + "-character-long script into " + category + " with key " + key);
        } catch (Exception ex) {
            return "FAILED: An error occured, please try again. || Usage: insert category key value (Please use _ in place of spaces.)";
        }
    }

}
