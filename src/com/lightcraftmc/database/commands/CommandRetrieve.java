package com.lightcraftmc.database.commands;

import com.lightcraftmc.database.command.Command;
import com.lightcraftmc.database.util.UtilFile;

public class CommandRetrieve extends Command {

	public CommandRetrieve() {
		super("retrieve");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String runCommand(boolean isLocal, String[] args) {
		if(args.length == 0){
			return "Usage: retrieve category key (Please use _ in place of spaces.)";
		}
		String category = args[0].toLowerCase();
		String key = args[1];
		if(isLocal){
			System.out.println("[INFO] Retrieving value from " + category + " with key " + key);
		}
		return UtilFile.load(category, key);
	}
	
	

}
