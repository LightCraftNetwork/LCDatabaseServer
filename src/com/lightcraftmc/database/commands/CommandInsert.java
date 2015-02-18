package com.lightcraftmc.database.commands;

import com.lightcraftmc.database.command.Command;
import com.lightcraftmc.database.util.UtilFile;

public class CommandInsert extends Command {

	public CommandInsert() {
		super("insert");
		setDescription("Set a specific data value into the database!");
	}

	@Override
	public String runCommand(String ip, boolean isLocal, String[] args) {
		if (args.length == 0) {
			return ("Usage: insert category key value (Please use _ in place of spaces.)");
		}
		try {
			String category = args[0].toLowerCase();
			String key = args[1];
			String value = args[2];
			UtilFile.save(category, key, value);
			return ("SUCCESS: Inserting " + value + " into " + category
					+ " with key " + key);
		} catch (Exception ex) {
			return "FAILED: An error occured, please try again. || Usage: insert category key value (Please use _ in place of spaces.)";
		}
	}

}
