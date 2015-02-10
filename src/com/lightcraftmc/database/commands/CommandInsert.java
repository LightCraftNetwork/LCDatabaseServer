package com.lightcraftmc.database.commands;

import com.lightcraftmc.database.command.Command;
import com.lightcraftmc.database.util.UtilFile;

public class CommandInsert extends Command {

	public CommandInsert() {
		super("insert");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String runCommand(boolean isLocal, String[] args) {
		if(args.length == 0){
			return("Usage: insert category key value (Please use _ in place of spaces.)");
		}
		String category = args[0].toLowerCase();
		String key = args[1];
		String value = args[2];
		UtilFile.save(category, key, value);
		return ("SUCCESS: Inserting " + value + " into " + category + " with key " + key);
	}
	
	

}
