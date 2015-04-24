package com.lightcraftmc.database.commands;

import com.lightcraftmc.database.command.Command;
import com.lightcraftmc.database.util.UtilFile;

public class CommandInsertScript extends Command {

    public CommandInsertScript() {
        super("insertscript");
        setDescription("Set a specific script value into the database!");
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
        if (args.length == 0) {
            return ("Usage: insert category key value (Please use _ in place of spaces.)");
        }
        try {
            String category = args[0].toLowerCase();
            String key = args[1];
            String value = args[2];
            UtilFile.saveScript(category, key, value);
            return ("SUCCESS: Inserting " + value.length() + "-character-long script into " + category + " with key " + key);
        } catch (Exception ex) {
            return "FAILED: An error occured, please try again. || Usage: insert category key value (Please use _ in place of spaces.)";
        }
    }

}
