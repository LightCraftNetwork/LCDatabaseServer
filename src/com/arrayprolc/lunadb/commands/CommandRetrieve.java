package com.arrayprolc.lunadb.commands;

import com.arrayprolc.lunadb.command.Command;
import com.arrayprolc.lunadb.util.UtilFile;

public class CommandRetrieve extends Command {

    public CommandRetrieve() {
        super("retrieve");
        this.setDescription("Retrieve a set value from the database!");
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
        long time = System.currentTimeMillis();
        if (args.length == 0) {
            return "Usage: retrieve category key (Please use _ in place of spaces.)";
        }
        try {
            String category = args[0].toLowerCase();
            String key = args[1];
            if (isLocal) {
                System.out.println("[INFO] Retrieving value from " + category + " with key " + key + " (in " + (System.currentTimeMillis() - time) + " ms)");
            }
            return UtilFile.load(category, key);
        } catch (Exception ex) {
            return "FAILED: Something failed. Please try again. || Usage: retrieve category key (Please use _ in place of spaces.)";
        }
    }

}
