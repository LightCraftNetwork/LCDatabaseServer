package com.lightcraftmc.database;

import com.lightcraftmc.database.command.Command;
import com.lightcraftmc.database.command.CommandManager;

public class WebCommandInterpreter {

	private static WebCommandInterpreter instance;

	public static WebCommandInterpreter getInstance() {
		if (instance == null) {
			instance = new WebCommandInterpreter();
		}
		return instance;
	}

	public String interpret(String s, String ip) {
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
					return command.runCommand(false, args);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		for (Command command : CommandManager.getInstance().commands) {
			for (String subName : command.getSubNames()) {
				try {
					if (subName.equalsIgnoreCase(commandName)) {
						return command.runCommand(false, args);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}
		return "[ERROR] Unknown command. Use \"help\" command to get a list of commands.";
	}

	public String getArgs(String s) {
		String remove = s.split(" ")[0];
		s = s.replaceFirst(remove + " ", "");
		return s;
	}

}
