package com.lightcraftmc.database.commands;

import com.lightcraftmc.database.command.Command;
import com.lightcraftmc.database.util.RawCategory;
import com.lightcraftmc.database.util.UtilDelete;
import com.lightcraftmc.database.util.UtilGenerateCategories;

public class CommandDelete extends Command {

    public CommandDelete() {
        super("deletecategory");
        this.setDescription("Delete a Category");
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
        if (args.length == 0) {
            return "Usage: deletecategory category";
        }
        try {
            String category = args[0].toLowerCase();
            RawCategory r = getCategory(category);
            UtilDelete.deleteDirectory(r.getFile());
            return "SUCCESS: Deleted!";
        } catch (Exception ex) {
            return "FAILED: Something failed. Please try again. || Usage: deletecategory category";
        }
    }
    
    private static RawCategory getCategory(String category) {
        for (RawCategory r : UtilGenerateCategories.getCategories()) {
            if (r.getName().equals(category.replace("/", "\\"))) {
                return r;
            }
        }
        return null;
    }

}
